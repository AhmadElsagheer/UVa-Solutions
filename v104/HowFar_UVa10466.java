package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HowFar_UVa10466 {

	static final double INF = 1e9, EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int n = sc.nextInt(), T = sc.nextInt();
			int[] r = new int[n], t = new int[n];
			
			for(int i = 0; i < n; i++)
			{
				r[i] = sc.nextInt();
				t[i] = sc.nextInt();
			}
			
			double ans[] = new double[n];
			Point o = new Point(0, 0);
			Point p = new Point(ans[0]= r[0], 0).rotate(T * 2.0 * Math.PI / t[0]);
			for(int i = 1; i < n; i++)
			{
				double angle = T * 2.0 * Math.PI / t[i];
				Vector v = new Vector(o, p);
				p = new Point(r[i], 0);
				p = p.rotate(angle).translate(v);
				ans[i] = p.distToOrigin();
			}
			
			for(int i = 0; i < n - 1; i++)
				out.printf("%.4f ", ans[i]);
			out.printf("%.4f\n",ans[n-1]);
		}
		out.flush();
		
	}
	
	static class Point 
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		double distToOrigin()
		{
			return Math.sqrt(x * x + y * y);
		}
		
		Point rotate(double angle)
		{
			double c = Math.cos(angle), s = Math.sin(angle);
			return new Point(x * c - y * s, x * s + y * c);
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
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
