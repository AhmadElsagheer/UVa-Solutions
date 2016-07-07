package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OfCirclesAndSquares_UVa10823 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int R = sc.nextInt(), P = sc.nextInt();
			Shape[] shapes = new Shape[R];
			for(int i = 0; i < R; ++i)
			{
				String type = sc.next();
				Point p = new Point(sc.nextInt(), sc.nextInt());
				int len = sc.nextInt();
				Color col = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
				if(type.equals("CIRCLE"))
					shapes[i] = new Circle(p, len, col);
				else
					shapes[i] = new Square(p, len, col);
			}
			out.printf("Case %d:\n", t);
			while(P-->0)
			{
				Point p = new Point(sc.nextInt(), sc.nextInt());
				Color col = new Color(0, 0, 0);
				int count = 0;
				for(Shape s: shapes)
				{
					int cont = s.contains(p);
					if(cont == 0)
					{
						count = -1;
						col = new Color(0, 0, 0);
						break;
					}
					else if(cont == 1)
					{	
						col.mix(s.col);
						++count;
					}
				}
				
				if(count > 0)
					col.avg(count);
				else if(count == 0)
					col = new Color(255, 255, 255);
				out.println(col);
			}
			
			if(t != tc)
				out.println();
		}
		out.flush();
		out.close();
	}

	static class Color 
	{ 
		int r, g, b; 

		Color(int x, int y, int z) { r = x; g = y; b = z;} 

		void mix(Color c) { r += c.r; g += c.g; b += c.b; }
		
		void avg(int den) 
		{
			r = (int) Math.round(r * 1.0 / den);
			g = (int) Math.round(g * 1.0 / den);
			b = (int) Math.round(b * 1.0 / den);
		}

		public String toString() { return "(" + r + ", " + g + ", " + b + ")"; } 
	}

	static class Point
	{
		int x, y;

		Point(int a, int b) { x = a; y = b; }

		int dist2(Point p) { return sq(x - p.x) + sq(y - p.y); }

		int sq(int x) { return x * x; }
	}

	static abstract class Shape { Color col; abstract int contains(Point p); }	//1 in, 0 border, -1 out

	static class Circle extends Shape
	{
		Point c;
		int r;

		Circle(Point a, int b, Color co) { c = a; r = b; col = co; }

		int contains(Point p)
		{
			int d2 = c.dist2(p);
			if(d2 == r * r)
				return 0;
			return d2 < r * r ? 1 : -1;
		}
	}

	static class Square extends Shape
	{
		Point ll, ur;

		Square(Point a, int b, Color co)
		{
			ll = a;
			ur = new Point(ll.x + b, ll.y + b);
			col = co;
		}

		int contains(Point p)
		{
			if(ll.x <= p.x && p.x <= ur.x && ll.y <= p.y && p.y <= ur.y)
			{
				if(p.x == ll.x || p.x == ur.x || p.y == ll.y || p.y == ur.y)
					return 0;
				return 1;
			}
			return -1;
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}