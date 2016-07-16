package regionals.dhaka2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TollManagement {

	static final int INF = (int)1e9;
	static int N;
	static ArrayList<Edge>[] adjList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt(); 
			int M = sc.nextInt(), U[] = new int[N - 1], V[] = new int[N - 1], W[] = new int[N - 1];
			
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Edge>();
			
			for(int i = 0; i < N - 1; ++i)
			{
				int u = U[i] = sc.nextInt() - 1, v = V[i] = sc.nextInt() - 1, w = W[i] = sc.nextInt();
				adjList[u].add(new Edge(v, w));
				adjList[v].add(new Edge(u, w));
			}
			
			build();
			
			long ans = 0, j = N;
			for(int i = N - 1; i < M; ++i, ++j)
			{	
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
				int x = queryAndUpdate(u, v, w);
				ans += j * j * (w - x) - j;
			}
			
			propagate();
			j = 1;
			for(int i = 0; i < N - 1; ++i, ++j)
			{
				int u = U[i], v = V[i];
				if(P[u][0] == v)
				{
					u ^= v; v ^= u; u ^= v;
				}
				if(nonspecial[v][0] == INF)
					ans += -j - j * j;			//occurs if no non-special edges
				else
					ans += j * (nonspecial[v][0] - W[i]) - j * j;
			}
			
			out.printf("Case %d: %d\n", t, ans);
		}
		out.flush();
		out.close();
	}
	
	static class Edge {	int node, cost; Edge(int a, int b) { node = a; cost = b; } }
	
	static void build()
	{
		// Construct Tree and LCA
		weight = new int[N];
		L = new int[N];
		Queue<Integer> q = new LinkedList<Integer>();
		int[] T = new int[N];
		Arrays.fill(T, -1);
		q.add(0);
		L[0] = T[0] = 0;
		
		while(!q.isEmpty())
		{
			int u = q.remove();
			
			for(Edge e: adjList[u])
				if(T[e.node] == -1)
				{
					T[e.node] = u;
					weight[e.node] = e.cost;
					L[e.node] = L[u] + 1;
					q.add(e.node);
				}
		}
		T[0] = -1;
		ancestors(T);
	}
	
	static int[] weight, L;
	static int[][] P, special, nonspecial;
	
	
	static void ancestors(int[] T)
	{
		int k = (int)(Math.floor(Math.log(N)/Math.log(2))) + 1;
		P = new int[N][k];
		special = new int[N][k];
		nonspecial = new int[N][k];
		P[0][0] = -1;

		for(int i = 0; i < N; ++i)
		{	
			P[i][0] = T[i];
			special[i][0] = weight[i];
			nonspecial[i][0] = INF;
		}
		
		for(int i = 0; i < N; i++)
			for(int j = 1; j < k; j++)
			{	
				P[i][j] = -1;
				nonspecial[i][j] = INF;
			}
		
		for(int j = 1; j < k; j++)			
			for(int i = 1; i < N; i++)
				if(P[i][j-1] != -1)
				{	
					P[i][j] = P[P[i][j-1]][j-1];
					special[i][j] = Math.max(special[i][j-1], special[P[i][j-1]][j-1]);
				}
	}
	
	 static int queryAndUpdate(int p, int q, int w)
	 {
		 int tmp, log, i, maxSpecial = 0;
		 
		 if (L[p] < L[q])
		 {
			 tmp = p; p = q; q = tmp;
		 }
		 
		 for (log = 1; 1 << log <= L[p]; log++);
		 log--;

		 for (i = log; i >= 0; i--)
			 if (L[p] - (1 << i) >= L[q])
			 {
				 maxSpecial = Math.max(maxSpecial, special[p][i]);
				 nonspecial[p][i] = Math.min(nonspecial[p][i], w);
				 p = P[p][i];
			 }
		 
		 if (p == q)
			 return maxSpecial;

		 for (i = log; i >= 0; i--)
			 if (P[p][i] != -1 && P[p][i] != P[q][i])
			 {
				 maxSpecial = Math.max(maxSpecial, Math.max(special[p][i], special[q][i]));
				 nonspecial[p][i] = Math.min(nonspecial[p][i], w);
				 nonspecial[q][i] = Math.min(nonspecial[q][i], w);
				 p = P[p][i]; q = P[q][i];
			 }
		 nonspecial[p][0] = Math.min(nonspecial[p][0], w);
		 nonspecial[q][0] = Math.min(nonspecial[q][0], w);
		 return Math.max(maxSpecial, Math.max(special[p][0], special[q][0]));
	 }
	 
	 static void propagate()		//propagate min non-special edge weights according to previous updates
	 {
		 int k = (int)(Math.floor(Math.log(N)/Math.log(2))) + 1;
		 for(int j = k - 1; j > 0; j--)			
				for(int i = 1; i < N; i++)
				{
					nonspecial[i][j-1] = Math.min(nonspecial[i][j-1], nonspecial[i][j]);
					if(P[i][j-1] != -1)
						nonspecial[P[i][j-1]][j-1] = Math.min(nonspecial[P[i][j-1]][j-1], nonspecial[i][j]);
				}			
	 }

	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;

		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }

		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();	
		}

		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
	}
}
