package v112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheBrokenPedometer_UVa11205 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int P = sc.nextInt(), N = sc.nextInt(), codes[] = new int[N];
			for(int i = 0; i < N; ++i)
			{
				int code = 0;
				for(int j = 0; j < P; ++j)
					code |= sc.nextInt()<<j;
				codes[i] = code;
			}
			int ans = P;
			for(int msk = 0; msk < 1<<P; ++msk)
			{
				boolean[] used = new boolean[1<<P];
				boolean possible = true;
				for(int i = 0; i < N; ++i)
				{
					int code = codes[i] & msk;
					if(used[code])
					{
						possible = false;
						break;
					}
					used[code] = true;
				}
			
				if(possible)
					ans = Math.min(ans, Integer.bitCount(msk));
			}
			out.println(ans);
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

	}
}