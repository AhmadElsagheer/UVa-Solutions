package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Euclid_UVa1249 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			double[] a = new double[12];
			Point[] points = new Point[6];
			for(int i = 0, j = 0; i < 12; i++)
			{
				a[i] = sc.nextDouble();
				if((i & 1) == 1)
					points[j++] = new Point(a[i-1], a[i]);
			}
			if(eof(a)) break;
			
			double angle = angle(points[1], points[0], points[2]);
			double area = area(points[3], points[4], points[5]);
			Vector ab = new Vector(points[0], points[1]), ac = new Vector(points[0], points[2]);
			
			Vector ah = ac.scale((area / (Math.sin(angle) * ab.norm() * ac.norm())));
			
			Point h = points[0].translate(ah), g = points[1].translate(ah);
			h.fix(); g.fix();
			out.printf("%.3f %.3f %.3f %.3f\n", g.x, g.y, h.x, h.y);
			
		}
		out.flush();
	}
	
	
	static boolean eof(double[] a)
	{
		for(int i = 0; i < a.length; i++)
			if(Math.abs(a[i]) > EPS)
				return false;
		return true;
	}
	
	static double area(Point p, Point q, Point r)
	{
		return Math.abs(p.x * q.y - p.y * q.x + q.x * r.y - q.y * r.x + r.x * p.y - r.y * p.x) / 2.0;
	}
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		return Math.acos(oa.dot(ob) / (oa.norm() * ob.norm()));
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a;
			y = b;
		}
		
		void fix()
		{
			x = round(x);
			y = round(y);
		}

		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		
		static double round(double d) 
		{
			return Math.round(d * 1000) / 1000.0;
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
		
		Vector(double a, double b)
		{
			x = a; y = b;
		}
		
		Vector scale(double u)
		{
			return new Vector(x * u, y * u);
		}
		
		double norm()
		{
			return Math.sqrt(x * x + y * y);
		}
		
		double dot(Vector v)
		{
			return x * v.x + y * v.y;
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
