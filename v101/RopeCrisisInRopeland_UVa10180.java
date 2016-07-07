package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RopeCrisisInRopeland_UVa10180 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			Point p = new Point(sc.nextDouble(), sc.nextDouble()), q = new Point(sc.nextDouble(), sc.nextDouble());
			double r = sc.nextDouble();
			double ans;
			if(!intersect(p, q, r))
				ans = p.dist(q);
			else
			{
				Point o = new Point(0, 0);
				double d = p.dist(o), angle = Math.acos(r / d);
				Vector v = new Vector(p, o); 
				v = v.scale((d - r) / d);
				
				Point p1 = p.translate(v).rotate(angle), p2 = p.translate(v).rotate(-angle);
				
				d = q.dist(new Point(0, 0)); angle = Math.acos(r / d);
				v = new Vector(q, o);
				v = v.scale((d - r) / d);
				
				Point q1 = q.translate(v).rotate(angle), q2 = q.translate(v).rotate(-angle);
								
				angle = Math.min(Math.min(angle(p1, o, q1), angle(p1, o, q2)), Math.min(angle(p2, o, q1), angle(p2, o, q2)));
				
				ans = p.dist(p1) + q.dist(q1) + angle * r;
			}
			out.printf("%.3f\n", ans);
		}
		out.flush();
	}	
	
	static boolean intersect(Point p, Point q, double r)
	{
		Line l = new Line(p, q);
		if(Math.abs(l.b) < EPS)
		{
			if(l.c * l.c + EPS >= r * r)
				return false;
			
			double y1 = Math.sqrt(r * r - l.c * l.c), y2 = -y1;
			return new Point(-l.c, y1).between(p, q) && new Point(-l.c, y2).between(p, q);
		}
		double a = l.a * l.a + 1, b = 2 * l.a * l.c, c = l.c * l.c - r * r;
		if(b * b - 4 * a * c + EPS < 0)
			return false;
		
		double dis = b * b - 4 * a * c;
		
		double x1 = (-b + Math.sqrt(dis)) / (2.0 * a), x2 = (-b - Math.sqrt(dis)) / (2.0 * a);
		
		return new Point(x1, - l.a * x1 - l.c).between(p, q) && new Point(x2, - l.a * x2 - l.c).between(p, q);
	}
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		return Math.acos(oa.dot(ob) / (oa.norm() * ob.norm()));
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
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
		
		boolean between(Point p, Point q)
		{
			return x <= Math.max(p.x, q.x) + EPS && x + EPS >= Math.min(p.x, q.x) && y <= Math.max(p.y, q.y) + EPS && y + EPS >= Math.min(p.y, q.y) ;
		}
		
		public String toString()
		{
			return "["+x+", "+y+"]";
		}
	}
	
	static class Line
	{
		double a, b, c;
		
		Line(Point p, Point q)
		{
			if(Math.abs(p.x - q.x) < EPS)
			{
				a = 1.0; b = 0.0; c = -p.x;
			}
			else
			{
				a = (p.y - q.y) / (q.x - p.x);
				b = 1.0;
				c = -a * p.x - p.y;
			}
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
		
		double dot(Vector v)
		{
			return x * v.x + y * v.y;
		}
		
		double norm()
		{
			return Math.sqrt(x * x + y * y);
		}
		
		Vector scale(double s)
		{
			return new Vector(x * s, y * s);
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
