package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PipeFitters_UVa121 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			double a = sc.nextDouble(), b = sc.nextDouble();
			double s = (2.0 - Math.sqrt(3)) / 2.0;
			long grid = (long)a * (long)b;
		
			long skew1, skew2;
			//for skew part 1
			long h = (long)((b - s) / (1 - s));
			skew1 = (long)a * ((h + 1) / 2);
			if(a - (long)a + EPS >= 0.5)
				skew1 += (long)a * (h / 2);
			else
				skew1 += ((long)a - 1) * (h / 2);
			
			//for skew part 2
			h = (long)((a - s) / (1 - s));
			
			skew2 = (long)b * ((h + 1) / 2);
			
			if(b - (long)b + EPS >= 0.5)
				skew2 += (long)b * (h / 2);
			else
				skew2 += ((long)b - 1) * (h / 2);
			
			skew1 = Math.max(skew2, skew1);
			if(skew1 > grid)
				out.printf("%d skew\n", skew1);
			else
				out.printf("%d grid\n", grid);
		
		}
		out.flush();
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
		
		public boolean nextEmpty() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens() == 0;
		}
		
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
