package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Risk_UVa567 {

	static final int INF = (int)1e9;
	
	static void floyd(int V, int[][] adjMat)
	{
		for(int k = 0; k < V; ++k)
			for (int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][k] + adjMat[k][j], adjMat[i][j]);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;

		while(sc.ready())
		{
			int[][] adjMat = new int[20][20];
			for(int i = 0; i < 20; ++i)
				Arrays.fill(adjMat[i], INF);
			for(int i = 0; i < 19; ++i)
			{
				int k = sc.nextInt();
				while(k-->0)
				{
					int j = sc.nextInt() - 1;
					adjMat[i][j] = adjMat[j][i] = 1;
				}
			}
			floyd(20, adjMat);
			int Q = sc.nextInt();
			out.printf("Test Set #%d\n", tc++);
			while(Q-->0)
			{
				int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
				out.printf("%2d to %2d: %d\n", x + 1, y + 1, adjMat[x][y]);
			}
			out.println();
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}