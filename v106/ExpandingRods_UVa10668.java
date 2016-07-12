package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ExpandingRods_UVa10668 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			double L = sc.nextDouble(), n = sc.nextDouble(), C = sc.nextDouble();
			if(L < 0)
				break;
			double L1 = (1 + n * C) * L;
			double lo = 0, hi = Math.PI, angle = 0;
			for(int i = 0; i < 100; ++i)
			{
				angle = (lo + hi) / 2;

				double L2 =  L / ( Math.sin(angle / 2)) * angle / 2;
				if(L2 >= L1)			//if equal sign removed, WA!
					hi = angle;
				else
					lo = angle;
			}
			double r = L / 2 / Math.sin(angle / 2);
			double ans = r - r * Math.cos(angle / 2);

			out.printf("%.3f\n", ans);
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