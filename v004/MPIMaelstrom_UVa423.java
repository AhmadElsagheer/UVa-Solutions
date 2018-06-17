package v004;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MPIMaelstrom_UVa423 {

	static final int INF = (int)1e9;

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int V = sc.nextInt();
		int[][] adjMat = new int[V][V];
		for(int i = 0; i < V; ++i)
		{
			Arrays.fill(adjMat[i], INF);
			adjMat[i][i] = 0;
		}

		for(int i = 1; i < V; ++i)
			for(int j = 0; j < i; ++j)
			{
				String x = sc.next();
				if(!x.equals("x"))
					adjMat[i][j] = adjMat[j][i] = Integer.parseInt(x);
			}

		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);

		int ans = 0;
		for(int i = 1; i < V; ++i)
			ans = Math.max(adjMat[0][i], ans);
		out.println(ans);
		out.close();
	}

	static class Scanner
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(String s) throws FileNotFoundException {	br = new BufferedReader(new FileReader(s));}

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