package v105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class XYZZY_UVa10557 {

	static final int INF = (int)1e9;

	// 1. Bellman-Ford Solution (230 ms)
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt();
			if(N == -1)
				break;
			int[] e = new int[N];
			int[][] adjList = new int[N][];
			boolean[][] adjMat = new boolean[N][N];
			for(int i = 0; i < N; ++i)
			{
				e[i] = sc.nextInt();
				int k = sc.nextInt();
				adjList[i] = new int[k];
				for(int j = 0; j < k; ++j)
					adjMat[i][adjList[i][j] = sc.nextInt() - 1] = true;
			}

			// Connectivity
			for(int k = 0; k < N; ++k)
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						adjMat[i][j] |= adjMat[i][k] && adjMat[k][j];

			// Bellman Ford
			int[] maxEnergy = new int[N];
			Arrays.fill(maxEnergy, (int)-1e6);
			maxEnergy[0] = 100;

			for(int k = 0; k < N - 1; ++k)
				for(int u = 0; u < N; ++u)
				{
					int maxE = maxEnergy[u];
					if(maxE <= 0)
						continue;
					for(int v: adjList[u])
						maxEnergy[v] = Math.max(maxEnergy[v], e[v] + maxE);
				}

			boolean reach = false;
			for(int u = 0; u < N; ++u)
			{
				int maxE = maxEnergy[u];
				if(maxE <= 0)
					continue;
				for(int v: adjList[u])
					reach |= adjMat[v][N-1] && maxEnergy[v] < e[v] + maxE; 
			}
			
			out.println(maxEnergy[N-1] > 0 || reach?"winnable":"hopeless");
		}
		out.flush();
		out.close();
	}


	// 2. BFS Solution (1360 ms)	
	public static void bfs() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt();
			if(N == -1)
				break;
			int[] e = new int[N];
			int[][] adjList = new int[N][];
			for(int i = 0; i < N; ++i)
			{
				e[i] = sc.nextInt();
				int k = sc.nextInt();
				adjList[i] = new int[k];
				for(int j = 0; j < k; ++j)
					adjList[i][j] = sc.nextInt() - 1;
			}

			boolean[][] vis = new boolean[N][N * 100 * 2];
			Queue<Integer> q = new LinkedList<Integer>();

			q.add(100 * N);
			vis[0][100] = true;
			boolean reach = false;
			while(!q.isEmpty())
			{
				int cur = q.remove();
				int u = cur % N, energy = cur / N;
				if(u == N - 1)
				{
					reach = true;
					break;
				}

				for(int v: adjList[u])
				{
					int totalEnergy = energy + e[v];
					if(totalEnergy > 0 && totalEnergy < N * 200 && !vis[v][totalEnergy])
					{
						vis[v][totalEnergy] = true;
						q.add(totalEnergy * N + v);
					}
				}
			}
			out.println(reach?"winnable":"hopeless");
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}