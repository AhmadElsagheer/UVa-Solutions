package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CockroachEscapeNetworks_UVa10805 {

	/*
	 * Minimum Diameter Spanning Tree
	 * ==============================
	 * 1. Find APSP
	 * 2. Brute force on tree center edge/vertex and minimize diameter
	 * 
	 * Best Complexity (implemented with BFS for APSP): O(VE)
	 */
	static final int INF = (int)1e9;

	static void floyd(int[][] adjMat, int V)
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			int[][] adjMat = new int[n][n];
			for(int i = 0; i < n; ++i)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
			}
			Edge[] edgeList = new Edge[m];
			for(int i = 0; i < m; ++i)
			{
				int u = sc.nextInt(), v = sc.nextInt();
				adjMat[u][v] = adjMat[v][u] = 1;
				edgeList[i] = new Edge(u, v);
			}
			// 1. Find APSP
			floyd(adjMat, n);

			int ans = n - 1;
			// 2. Brute force on center vertex (min even diameter)
			for(int i = 0; i < n; ++i)
			{
				int d = 0;
				for(int j = 0; j < n; ++j)
					d = Math.max(d, adjMat[i][j]);
				ans = Math.min(ans, d * 2);
			}

			// 3. Brute force on center edge (min odd diameter)
			for(Edge e: edgeList)
			{
				int d = 0;
				for(int k = 0; k < n; ++k)
					d = Math.max(d, Math.min(adjMat[e.u][k], adjMat[e.v][k]));
				ans = Math.min(ans, d * 2 + 1);
			}

			out.printf("Case #%d:\n%d\n\n", t, ans);
		}
		out.flush();
		out.close();
	}

	static class Edge { int u, v; Edge(int a, int b) { u = a; v = b; }

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