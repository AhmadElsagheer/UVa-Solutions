package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheKissingCircles_UVa10283 {
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int R = sc.nextInt(), n = sc.nextInt();
			if(n == 1)
			{
				out.printf("%.10f %.10f %.10f\n", (double)R, 0.0, 0.0);
				continue;
			}
			double c = Math.cos(Math.PI*(n - 2) / (2 * n));
			double r = R * c / (1 + c);
			double b = areaPolygon(n, 2 * r) - sectorArea(180.0 * (n - 2) / n, r) * n;
			double g = Math.PI * R * R - n * Math.PI * r * r - b;
			out.printf("%.10f %.10f %.10f\n", r, b, g);
		}
		out.flush();

	}
	
	static double areaPolygon(int n, double l)
	{
		double angle = Math.PI * (n - 2) / n / 2.0;
		double apothem = Math.tan(angle) * l / 2;
		double perimeter = l * n;
		return 0.5 * apothem * perimeter;
	}
	
	static double sectorArea(double angle, double r)
	{
		return angle / 360.0 * Math.PI * r * r;
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
