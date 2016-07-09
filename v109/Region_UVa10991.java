package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Region_UVa10991 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			double r1 = sc.nextDouble(), r2 = sc.nextDouble(), r3 = sc.nextDouble();
			double a = r1 + r2, b = r1 + r3, c = r2 + r3;
			
			double t1 = Math.acos((a * a + b * b - c * c) / (2 * a * b));
			double t2 = Math.acos((a * a + c * c - b * b) / (2 * a * c));
			double t3 = Math.PI - (t1 + t2);
			out.printf("%.6f\n", areaTriangle(a, b, c) - (areaSector(r1, t1) + (areaSector(r2, t2)) + (areaSector(r3, t3))));
		}
		out.flush();
					
	}
	
	static double areaSector(double r, double angle)
	{
		return angle * r * r / 2.0;
	}
	
	static double areaTriangle(double a, double b, double c)
	{
		double s = 0.5 * (a + b + c);
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));
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
