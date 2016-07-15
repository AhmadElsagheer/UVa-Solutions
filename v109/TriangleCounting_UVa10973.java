package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TriangleCounting_UVa10973 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			boolean[][] adjMat = new boolean[n][n];
			Edge[] edgeList = new Edge[m];
			for(int i = 0; i < m; ++i)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				if(u > v) { u ^= v; v ^= u; u ^= v; }
				edgeList[i] = new Edge(u, v);
				adjMat[u][v] = adjMat[v][u] = true;
			}
			int count = 0;
			for(Edge e: edgeList)
				for(int w = e.v + 1; w < n; ++w)
					if(adjMat[w][e.v] && adjMat[w][e.u])
						++count;
			out.println(count);
		}
		out.flush();
		out.close();
	}
	
	static class Edge { int u, v; Edge(int a, int b) { u = a; v = b; } }

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

		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


		public boolean ready() throws IOException {return br.ready();}

	}
}