package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PolygonInsideACircle_UVa10342 {
	
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			double r = sc.nextDouble(); int n = sc.nextInt();
			out.printf("%.3f\n", area(r) - segmentArea(180 - polygonAngle(n), r) * n);
		}
		out.flush();
	}
	
	static double degToRad(double d)
	{
		return d * Math.PI / 180.0;
	}
	
	static double polygonAngle(int n)
	{
		return 180.0 * (n - 2) / n;
	}
	
	static double sectorArea(double angle, double r)
	{
		return angle / 360.0 * area(r);
	}
	
	static double segmentArea(double angle, double r)
	{
		return sectorArea(angle, r) - r * r * Math.sin(degToRad(angle)) / 2.0;
	}
	
	static double area(double r)
	{
		return Math.PI * r * r;
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
