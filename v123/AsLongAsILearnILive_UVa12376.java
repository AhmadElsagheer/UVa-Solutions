package v123;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AsLongAsILearnILive_UVa12376 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int V = sc.nextInt(), E = sc.nextInt();
			int[] learnValue = new int[V];
			for(int i = 0; i < V; ++i)
				learnValue[i] = sc.nextInt();
			boolean[][] adjMat = new boolean[V][V];
			while(E-->0)
				adjMat[sc.nextInt()][sc.nextInt()] = true;
			int u = 0, ans = 0;
			while(true)
			{
				int max = -1, v = -1;
				for(int i = 0; i < V; ++i)
					if(adjMat[u][i] && learnValue[i] > max)
						max = learnValue[v = i];
				if(max == -1)
					break;
				ans += max;
				u = v;
			}
			out.printf("Case %d: %d %d\n", t, ans, u);
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