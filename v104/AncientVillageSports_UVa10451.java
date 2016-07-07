package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AncientVillageSports_UVa10451 {
	
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int k = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n < 3)
				break;
			double A = sc.nextDouble();
			double r2 = radiusOuterCircle(A, n);
			double r1 = r2 * Math.cos(degToRad((180.0 - 180.0 * (n - 2) / n) / 2));
			
			out.printf("Case %d: %.5f %.5f\n", k++, area(r2) - A, A - area(r1));
		}
		out.flush();
	
	}
	
	static double area(double r)
	{
		return Math.PI * r * r;
	}
	
	
	static double radiusOuterCircle(double A, int n)
	{
		double theta = 180 - (180.0 * (n - 2)) / n;
		return Math.sqrt(A /(Math.PI *(1 - n * theta / 360.0) + n * Math.sin(degToRad(theta))/ 2.0));
	}
	
	static double degToRad(double d)
	{
		return d * Math.PI / 180.0;
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
					res = Integer.parseInt(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Integer.parseInt(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
