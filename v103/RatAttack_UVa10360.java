package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RatAttack_UVa10360 {

	static boolean valid(int x, int y)
	{
		return x >= 0 && y >= 0 && x <= 1024 && y <= 1024;
	}

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int d = sc.nextInt(), n = sc.nextInt();
			int[][] f = new int[1025][1025];

			while(n-->0)
			{
				int x = sc.nextInt(), y = sc.nextInt(), p = sc.nextInt();
				for(int i = -d; i <= d; ++i)
					for(int j = -d; j <= d; ++j)
					{
						int xx = i + x, yy = j + y;
						if(valid(xx, yy))
							f[xx][yy] += p;
					}
			}
			int x = -1, y = -1, max = 0;
			for(int i = 0; i <= 1024; ++i)
				for(int j = 0; j <= 1024; ++j)
					if(f[i][j] > max)
						max = f[x = i][y = j];
			out.println(x + " " + y + " " + max);
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