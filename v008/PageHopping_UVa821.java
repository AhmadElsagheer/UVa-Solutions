package v008;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PageHopping_UVa821 {
	
	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
			if(u == -1 && v == -1)
				break;
			int[][] adjMat = new int[100][100];
			for(int i = 0; i < 100; ++i)
				Arrays.fill(adjMat[i], INF);
			
			boolean[] valid = new boolean[100];
			while(u != -1)
			{
				valid[u] = valid[v] = true;
				adjMat[u][v] = 1;
				u = sc.nextInt() - 1;
				v = sc.nextInt() - 1;
			}
			for(int k = 0; k < 100; ++k)
				for(int i = 0; i < 100; ++i)
					for(int j = 0; j < 100; ++j)
						adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
			int pairs = 0, sum = 0;
			for(int i = 0; i < 100; ++i)
				if(valid[i])
					for(int j = 0; j < 100; ++j)
						if(i != j && valid[j])
						{
							++pairs;
							sum += adjMat[i][j];
						}
			out.printf("Case %d: average length between pages = %.3f clicks\n", tc++, sum * 1.0 / pairs);
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