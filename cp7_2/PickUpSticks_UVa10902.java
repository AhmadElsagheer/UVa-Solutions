package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PickUpSticks_UVa10902 {

	static final double EPS = 1e-9;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			ArrayList<LineSegment> segments = new ArrayList<LineSegment>(1000);
			
			
			for(int i = 0; i < n; i++)
			{
				LineSegment cur = new LineSegment(new Point(sc.nextDouble(), sc.nextDouble()), new Point(sc.nextDouble(), sc.nextDouble()), i + 1);
				Line l = new Line(cur.left, cur.right);
				ArrayList<LineSegment> tmp = new ArrayList<LineSegment>(1000);
				for(int j = 0, size = segments.size(); j < size; j++)
				{
					LineSegment nxt = segments.get(j);
					Line ll = new Line(nxt.left, nxt.right);
					boolean top = true;
					if(l.parallel(ll))
					{
						if(l.same(ll))
							if(nxt.left.between(cur.left, cur.right) || nxt.right.between(cur.left, cur.right) || cur.left.between(nxt.left, nxt.right) || cur.right.between(nxt.left, nxt.right))
									top = false;
					}
					else
					{
						Point c = l.intersect(ll);
						if(c.between(cur.left, cur.right) && c.between(nxt.left, nxt.right))
							top = false;
						
					}
					if(top)
						tmp.add(nxt);
					
				}
				segments = tmp;
				segments.add(cur);
			}
			
			out.print("Top sticks:");
			for(int i = 0, size = segments.size(); i < size - 1; i++) out.printf(" %d,", segments.get(i).idx); 
			out.printf(" %d.\n", segments.get(segments.size() - 1).idx);
		}
		out.flush();
	}
	
	static class Point implements Comparable<Point>
	{
		double x, y;
	
		Point(double a, double b)
		{
			x = a;
			y = b;
		}
		
		public int compareTo(Point p)
		{
			if(Math.abs(x - p.x) > EPS) return x > p.x ? 1 : -1;
			if(Math.abs(y - p.y) > EPS) return y > p.y ? 1 : -1;
			return 0;
		}
		
		boolean between(Point p, Point q)
		{
			return x <= Math.max(p.x, q.x) + EPS && x + EPS >= Math.min(p.x, q.x) && y + EPS >= Math.min(p.y, q.y) && y <= Math.max(p.y, q.y) + EPS; 
		}
	}
	
	static class LineSegment implements Comparable<LineSegment>
	{
		Point left, right;
		int idx;
		
		LineSegment(Point a, Point b, int k)
		{
			if(a.compareTo(b) == 1)
			{
				left = b;
				right = a;
			}
			else
			{
				left = a;
				right = b;
			}
			idx = k;
		}
		
		public int compareTo(LineSegment lines)
		{
			if(left.compareTo(lines.left) != 0)
				return left.compareTo(lines.left);
			return right.compareTo(lines.right);
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
				c = -p.x * a - p.y;
			}
		}
		
		boolean parallel(Line lines)
		{
			return Math.abs(a - lines.a) < EPS && Math.abs(b - lines.b) < EPS;
		}
		
		boolean same(Line lines)
		{
			return parallel(lines) && Math.abs(c - lines.c) < EPS;
		}
		
		Point intersect(Line lines)
		{
			if(parallel(lines)) return null;
			
			double x = (b * lines.c - c * lines.b) / (a * lines.b - b * lines.a);
			double y;
			if(Math.abs(b) > EPS) y = - a * x - c;
			else				  y = - lines.a * x - lines.c;
			
			return new Point(x, y);
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
