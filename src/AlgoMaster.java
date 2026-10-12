import java.io.*;
import java.util.*;

public class AlgoMaster {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n================================================");
            System.out.println("              ALGOMASTER");
            System.out.println("        ADVANCED ALGORITHM TOOLKIT");
            System.out.println("================================================");
            System.out.println("1. Problem Strategy Advisor");
            System.out.println("2. String Matching");
            System.out.println("3. Dynamic Programming");
            System.out.println("4. Network Flow & Matching");
            System.out.println("5. Optimization & Approximation");
            System.out.println("6. Randomized & Parallel Algorithms");
            System.out.println("7. Test With Dataset");
            System.out.println("8. Algorithm Guide");
            System.out.println("0. Exit");
            int c = readInt("Enter your choice: ");
            switch (c) {
                case 1 -> advisor(); case 2 -> stringMenu(); case 3 -> dpMenu();
                case 4 -> flowMenu(); case 5 -> approxMenu(); case 6 -> randomParallelMenu();
                case 7 -> datasetMenu(); case 8 -> guide();
                case 0 -> { System.out.println("Thank you for using AlgoMaster."); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static int readInt(String p) { while (true) try { System.out.print(p); return Integer.parseInt(sc.nextLine().trim()); } catch(Exception e){System.out.println("Enter a valid integer.");} }
    static int[] readArray(String p) { System.out.print(p); String[] s=sc.nextLine().trim().split("\\s+"); int[] a=new int[s.length]; for(int i=0;i<s.length;i++)a[i]=Integer.parseInt(s[i]); return a; }

    // Problem-class strategy selection
    static void advisor() {
        System.out.println("\n--- PROBLEM STRATEGY ADVISOR ---");
        System.out.println("1. Substring search");
        System.out.println("2. Sequence alignment / transformation");
        System.out.println("3. Network flow / capacity");
        System.out.println("4. Assignment / matching");
        System.out.println("5. NP-hard optimization");
        System.out.println("6. Randomized computation");
        System.out.println("7. Parallel reduction / prefix");
        switch(readInt("Choose: ")) {
            case 1 -> System.out.println("Use KMP or Z for deterministic O(n+m) search; Rabin-Karp for rolling-hash search.");
            case 2 -> System.out.println("Use dynamic programming such as Edit Distance; structured variants can use interval/subset DP.");
            case 3 -> System.out.println("Use Edmonds-Karp for clear BFS-based max flow or Dinic for layered-network flow.");
            case 4 -> System.out.println("Model bipartite assignment as a unit-capacity flow network.");
            case 5 -> System.out.println("Use approximation/greedy methods for practical solutions to selected NP-hard problems.");
            case 6 -> System.out.println("Use Las Vegas randomized QuickSort or Monte Carlo estimation depending on correctness requirements.");
            case 7 -> System.out.println("Use prefix sum/reduce primitives; tree-style parallel versions have O(log n) span.");
            default -> System.out.println("Invalid choice.");
        }
    }

    // String matching
    static void stringMenu() {
        System.out.println("\n--- STRING MATCHING ---");
        System.out.println("1. KMP\n2. Z Algorithm\n3. Rabin-Karp\n4. Compare all");
        int c=readInt("Choose: "); System.out.print("Enter text: "); String t=sc.nextLine(); System.out.print("Enter pattern: "); String p=sc.nextLine();
        if(c==1) System.out.println("Matches: "+kmp(t,p));
        else if(c==2) System.out.println("Matches: "+zSearch(t,p));
        else if(c==3) System.out.println("Matches: "+rabinKarp(t,p));
        else if(c==4) {System.out.println("KMP: "+kmp(t,p));System.out.println("Z: "+zSearch(t,p));System.out.println("Rabin-Karp: "+rabinKarp(t,p));}
        else System.out.println("Invalid choice.");
    }
    static int[] lps(String p){int[] a=new int[p.length()];for(int i=1,len=0;i<p.length();)if(p.charAt(i)==p.charAt(len))a[i++]=++len;else if(len>0)len=a[len-1];else a[i++]=0;return a;}
    static List<Integer> kmp(String t,String p){List<Integer> r=new ArrayList<>();if(p.isEmpty())return r;int[] a=lps(p);for(int i=0,j=0;i<t.length();)if(t.charAt(i)==p.charAt(j)){i++;j++;if(j==p.length()){r.add(i-j);j=a[j-1];}}else if(j>0)j=a[j-1];else i++;return r;}
    static List<Integer> zSearch(String t,String p){List<Integer> r=new ArrayList<>();if(p.isEmpty())return r;String s=p+"$"+t;int[] z=new int[s.length()];for(int i=1,l=0,rr=0;i<s.length();i++){if(i<=rr)z[i]=Math.min(rr-i+1,z[i-l]);while(i+z[i]<s.length()&&s.charAt(z[i])==s.charAt(i+z[i]))z[i]++;if(i+z[i]-1>rr){l=i;rr=i+z[i]-1;}if(z[i]==p.length())r.add(i-p.length()-1);}return r;}
    static List<Integer> rabinKarp(String t,String p){List<Integer> r=new ArrayList<>();int m=p.length();if(m==0||m>t.length())return r;long mod=1_000_000_007L,base=911382323L,hp=0,hw=0,pow=1;for(int i=0;i<m;i++){hp=(hp*base+p.charAt(i))%mod;hw=(hw*base+t.charAt(i))%mod;if(i<m-1)pow=pow*base%mod;}for(int i=0;i<=t.length()-m;i++){if(hp==hw&&t.regionMatches(i,p,0,m))r.add(i);if(i<t.length()-m){hw=(hw-t.charAt(i)*pow)%mod;if(hw<0)hw+=mod;hw=(hw*base+t.charAt(i+m))%mod;}}return r;}

    // Dynamic programming
    static void dpMenu(){
        System.out.println("\n--- DYNAMIC PROGRAMMING ---");
        System.out.println("1. String Edit Distance\n2. Matrix Chain Multiplication\n3. Interval DP - Burst Balloons\n4. Bitmask DP - TSP\n5. Subset DP - Equal Partition");
        switch(readInt("Choose: ")){
            case 1->{System.out.print("First string: ");String a=sc.nextLine();System.out.print("Second string: ");String b=sc.nextLine();System.out.println("Edit distance = "+editDistance(a,b));}
            case 2->{int[] d=readArray("Dimensions: ");System.out.println("Minimum multiplication cost = "+matrixChain(d));}
            case 3->{int[] a=readArray("Balloon values: ");System.out.println("Maximum coins = "+burst(a));}
            case 4->{int n=readInt("Number of cities: ");int[][] g=matrix(n);System.out.println("Minimum TSP tour cost = "+tsp(g));}
            case 5->{int[] a=readArray("Positive integers: ");System.out.println("Can partition equally? "+partition(a));}
            default->System.out.println("Invalid choice.");
        }
    }
    static int editDistance(String a,String b){int[][] d=new int[a.length()+1][b.length()+1];for(int i=0;i<=a.length();i++)d[i][0]=i;for(int j=0;j<=b.length();j++)d[0][j]=j;for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++)d[i][j]=a.charAt(i-1)==b.charAt(j-1)?d[i-1][j-1]:1+Math.min(d[i-1][j-1],Math.min(d[i-1][j],d[i][j-1]));return d[a.length()][b.length()];}
    static int matrixChain(int[] d){int n=d.length-1;int[][] dp=new int[n][n];for(int len=2;len<=n;len++)for(int i=0;i+len-1<n;i++){int j=i+len-1;dp[i][j]=Integer.MAX_VALUE;for(int k=i;k<j;k++)dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k+1][j]+d[i]*d[k+1]*d[j+1]);}return dp[0][n-1];}
    static int burst(int[] a){int n=a.length;int[] b=new int[n+2];b[0]=b[n+1]=1;System.arraycopy(a,0,b,1,n);int[][] d=new int[n+2][n+2];for(int len=1;len<=n;len++)for(int l=1;l+len-1<=n;l++){int r=l+len-1;for(int k=l;k<=r;k++)d[l][r]=Math.max(d[l][r],d[l][k-1]+b[l-1]*b[k]*b[r+1]+d[k+1][r]);}return d[1][n];}
    static int[][] matrix(int n){int[][] g=new int[n][n];for(int i=0;i<n;i++){int[] a=readArray("Row "+i+": ");if(a.length!=n){System.out.println("Need "+n+" values.");i--;continue;}g[i]=a;}return g;}
    static int tsp(int[][] g){int n=g.length,N=1<<n,INF=1_000_000_000;int[][] d=new int[N][n];for(int[] x:d)Arrays.fill(x,INF);d[1][0]=0;for(int m=1;m<N;m++)for(int u=0;u<n;u++)if((m&(1<<u))!=0&&d[m][u]<INF)for(int v=0;v<n;v++)if((m&(1<<v))==0)d[m|1<<v][v]=Math.min(d[m|1<<v][v],d[m][u]+g[u][v]);int ans=INF;for(int u=1;u<n;u++)ans=Math.min(ans,d[N-1][u]+g[u][0]);return ans;}
    static boolean partition(int[] a){int sum=0;for(int x:a)sum+=x;if(sum%2!=0)return false;boolean[] d=new boolean[sum/2+1];d[0]=true;for(int x:a)for(int s=sum/2;s>=x;s--)d[s]|=d[s-x];return d[sum/2];}

    // Network flow and matching
    static void flowMenu(){
        System.out.println("\n--- NETWORK FLOW & MATCHING ---");System.out.println("1. Edmonds-Karp\n2. Dinic\n3. Bipartite Matching");int c=readInt("Choose: ");
        if(c==1||c==2){int n=readInt("Vertices: ");int[][] cap=matrix(n);int s=readInt("Source: ");int t=readInt("Sink: ");System.out.println("Maximum flow = "+(c==1?edmondsKarp(cap,s,t):dinic(cap,s,t)));}
        else if(c==3){int l=readInt("Left nodes: "),r=readInt("Right nodes: "),e=readInt("Edges: ");int[][] es=new int[e][2];for(int i=0;i<e;i++){es[i][0]=readInt("Left index: ");es[i][1]=readInt("Right index: ");}System.out.println("Maximum matching = "+matching(l,r,es));}else System.out.println("Invalid choice.");
    }
    static int edmondsKarp(int[][] in,int s,int t){int n=in.length,flow=0;int[][] r=new int[n][n];for(int i=0;i<n;i++)r[i]=in[i].clone();while(true){int[] p=new int[n];Arrays.fill(p,-1);p[s]=s;Queue<Integer>q=new ArrayDeque<>();q.add(s);while(!q.isEmpty()&&p[t]<0){int u=q.poll();for(int v=0;v<n;v++)if(p[v]<0&&r[u][v]>0){p[v]=u;q.add(v);}}if(p[t]<0)break;int add=Integer.MAX_VALUE;for(int v=t;v!=s;v=p[v])add=Math.min(add,r[p[v]][v]);for(int v=t;v!=s;v=p[v]){r[p[v]][v]-=add;r[v][p[v]]+=add;}flow+=add;}return flow;}
    static int dinic(int[][] in,int s,int t){int n=in.length,flow=0;int[][] c=new int[n][n];for(int i=0;i<n;i++)c[i]=in[i].clone();while(true){int[] lev=new int[n];Arrays.fill(lev,-1);lev[s]=0;Queue<Integer>q=new ArrayDeque<>();q.add(s);while(!q.isEmpty()){int u=q.poll();for(int v=0;v<n;v++)if(lev[v]<0&&c[u][v]>0){lev[v]=lev[u]+1;q.add(v);}}if(lev[t]<0)break;int[] it=new int[n];while(true){int f=dfs(t,s,Integer.MAX_VALUE,c,lev,it);if(f==0)break;flow+=f;}}return flow;}
    static int dfs(int t,int u,int f,int[][] c,int[] lev,int[] it){if(u==t)return f;for(int v=it[u];v<c.length;v++,it[u]++){if(lev[v]==lev[u]+1&&c[u][v]>0){int x=dfs(t,v,Math.min(f,c[u][v]),c,lev,it);if(x>0){c[u][v]-=x;c[v][u]+=x;return x;}}}return 0;}
    static int matching(int l,int r,int[][] es){int n=l+r+2,s=l+r,t=s+1;int[][] c=new int[n][n];for(int i=0;i<l;i++)c[s][i]=1;for(int j=0;j<r;j++)c[l+j][t]=1;for(int[]e:es)if(e[0]>=0&&e[0]<l&&e[1]>=0&&e[1]<r)c[e[0]][l+e[1]]=1;return edmondsKarp(c,s,t);}

    // NP-hard approximation
    static void approxMenu(){System.out.println("\n--- OPTIMIZATION & APPROXIMATION ---");System.out.println("1. 2-Approximation Vertex Cover\n2. Greedy Set Cover\n3. Scheduling Guide");int c=readInt("Choose: ");if(c==1){int n=readInt("Vertices: "),e=readInt("Edges: ");int[][] es=new int[e][2];for(int i=0;i<e;i++){es[i][0]=readInt("u: ");es[i][1]=readInt("v: ");}System.out.println("Approximate cover = "+vertexCover(es));System.out.println("Guarantee: size <= 2 * OPT.");}else if(c==2){int u=readInt("Universe size: "),k=readInt("Number of sets: ");List<Set<Integer>>sets=new ArrayList<>();for(int i=0;i<k;i++){int[]a=readArray("Set "+i+": ");Set<Integer>s=new HashSet<>();for(int x:a)if(x>=1&&x<=u)s.add(x);sets.add(s);}System.out.println("Chosen set indices = "+setCover(u,sets));}else if(c==3){System.out.println("Selected NP-hard scheduling variants can be impractical to solve exactly for large instances.");System.out.println("Use greedy/approximation strategies when a provable or practical near-optimal solution is preferred.");}else System.out.println("Invalid choice.");}
    static Set<Integer> vertexCover(int[][] es){Set<Integer> c=new LinkedHashSet<>();boolean[]used=new boolean[es.length];for(int i=0;i<es.length;i++)if(!used[i]){int u=es[i][0],v=es[i][1];c.add(u);c.add(v);for(int j=i;j<es.length;j++)if(es[j][0]==u||es[j][1]==u||es[j][0]==v||es[j][1]==v)used[j]=true;}return c;}
    static List<Integer> setCover(int u,List<Set<Integer>>sets){Set<Integer>un=new HashSet<>();for(int i=1;i<=u;i++)un.add(i);List<Integer>out=new ArrayList<>();while(!un.isEmpty()){int best=-1,gain=0;for(int i=0;i<sets.size();i++){Set<Integer>x=new HashSet<>(sets.get(i));x.retainAll(un);if(x.size()>gain){gain=x.size();best=i;}}if(best<0)break;out.add(best);un.removeAll(sets.get(best));}return out;}

    // Randomized and parallel primitives
    static void randomParallelMenu(){System.out.println("\n--- RANDOMIZED & PARALLEL ---");System.out.println("1. Randomized QuickSort (Las Vegas)\n2. Monte Carlo Pi\n3. Prefix Sum\n4. Reduce");int c=readInt("Choose: ");if(c==1){int[]a=readArray("Numbers: ");quick(a,new Random(),0,a.length-1);System.out.println("Sorted = "+Arrays.toString(a));}else if(c==2){int n=readInt("Random trials: ");System.out.println("Estimated Pi = "+pi(n));}else if(c==3){int[]a=readArray("Numbers: ");System.out.println("Prefix sum = "+Arrays.toString(prefix(a)));System.out.println("Parallel tree-style work O(n), span O(log n).");}else if(c==4){int[]a=readArray("Numbers: ");long s=0;for(int x:a)s+=x;System.out.println("Reduce/sum = "+s);System.out.println("Parallel tree-style work O(n), span O(log n).");}else System.out.println("Invalid choice.");}
    static void quick(int[]a,Random r,int l,int h){if(l>=h)return;int p=l+r.nextInt(h-l+1),v=a[p],i=l,j=h;while(i<=j){while(a[i]<v)i++;while(a[j]>v)j--;if(i<=j){int x=a[i];a[i]=a[j];a[j]=x;i++;j--;}}if(l<j)quick(a,r,l,j);if(i<h)quick(a,r,i,h);}
    static double pi(int n){Random r=new Random();int in=0;for(int i=0;i<n;i++){double x=r.nextDouble(),y=r.nextDouble();if(x*x+y*y<=1)in++;}return n==0?0:4.0*in/n;}
    static int[] prefix(int[]a){int[]b=new int[a.length];int s=0;for(int i=0;i<a.length;i++){s+=a[i];b[i]=s;}return b;}

    static void datasetMenu(){System.out.println("\n--- DATASET LAB ---");String[]f={"string_matching_dataset.csv","dynamic_programming_dataset.csv","network_flow_dataset.csv","optimization_dataset.csv"};for(int i=0;i<f.length;i++)System.out.println((i+1)+". "+f[i]);System.out.println("0. Back");int c=readInt("Choose: ");if(c>=1&&c<=4)readCSV(f[c-1]);}
    static void readCSV(String name){File f=new File("data"+File.separator+name);if(!f.exists()){System.out.println("Dataset not found: "+f.getPath());return;}try(BufferedReader br=new BufferedReader(new FileReader(f))){String line;while((line=br.readLine())!=null)System.out.println(line);}catch(IOException e){System.out.println(e.getMessage());}}

    static void guide(){System.out.println("\n--- ALGORITHM GUIDE ---");System.out.println("KMP: O(n+m), LPS-based string matching");System.out.println("Z: O(n+m), prefix-match array");System.out.println("Rabin-Karp: rolling hash; average O(n+m), worst O(nm)");System.out.println("Edit Distance: O(nm)");System.out.println("Matrix Chain: O(n^3)");System.out.println("TSP Bitmask DP: O(n^2 2^n)");System.out.println("Edmonds-Karp: O(VE^2)");System.out.println("Dinic: layered residual-network max flow");System.out.println("Vertex Cover: 2-approximation");System.out.println("Set Cover: greedy logarithmic approximation");System.out.println("Randomized QuickSort: Las Vegas");System.out.println("Monte Carlo Pi: probabilistic estimate");System.out.println("Prefix/Reduce: parallel primitives; tree span O(log n)");}
}
