package v111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ColourfulFlowers_UVa11152 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
			
			Triangle t = new Triangle(a, b, c);
			double r1 = t.rInCircle(), r2 = t.rCircumCircle(); 
			double red = Math.PI * r1 * r1;
			double violet = t.area() - red;
			double yellow = Math.PI * r2 * r2 - (red + violet);
			out.printf("%.4f %.4f %.4f\n", yellow , violet, red);
		}
		out.flush();
		out.close();
	}
	
	
	static double circleArea(double r)
	{
		return Math.PI * r * r;
	}

	static class Triangle
	{
		double ab, bc, ca;
		
		Triangle(double x, double y, double z)
		{
			ab = x; bc = y; ca = z;
		}
		
		double area()
		{
			double s = (ab + bc + ca) / 2.0;
			return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
		}
		
		double rInCircle()
		{
			return area() / ((ab + bc + ca) / 2.0);
		}
		
		double rCircumCircle()
		{
			return ab * bc * ca / (4.0 * area());
		}
		
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
