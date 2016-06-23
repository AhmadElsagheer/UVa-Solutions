package v010;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SimplifiedGSMNetwork_UVa1039 {

	static int B, C, R, Q;
	static Point[] towers, cities;
	static int[][] adjMat;

	public static void main(String[] args) throws IOException {

		Point[] g = new Point[5];
		g[0] = g[4] = new Point(-1100, -1100);
		g[1] = new Point(1100, -1100);
		g[2] = new Point(1100, 1100);
		g[3] = new Point(-1100, 1100);
		Polygon grid = new Polygon(g);


		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{

			B = sc.nextInt();
			C = sc.nextInt();
			R = sc.nextInt();
			Q = sc.nextInt();
			
			if(B == 0)
				break;
			
			towers = new Point[B];
			Polygon[] towerArea = new Polygon[B];
			for(int i = 0; i < B; ++i)
			{	
				towers[i] = new Point(sc.nextDouble(), sc.nextDouble());
				towerArea[i] = grid;
			}

			for(int i = 0; i < B; ++i)
				for(int j = i + 1; j < B; ++j)
				{
					Point a = new Point((towers[i].x + towers[j].x)/2, (towers[i].y + towers[j].y)/2);
					Point b = a.translate(new Vector(towers[i], towers[j]).rotate90());

					if(Point.ccw(a, b, towers[i]))
					{
						towerArea[i] = towerArea[i].cutPolygon(a, b);
						towerArea[j] = towerArea[j].cutPolygon(b, a);
					}
					else
					{
						towerArea[i] = towerArea[i].cutPolygon(b, a);
						towerArea[j] = towerArea[j].cutPolygon(a, b);
					}

				}

			cities = new Point[C];
			for(int i = 0; i < C; ++i)
				cities[i] = new Point(sc.nextDouble(), sc.nextDouble());



			adjMat = new int[C][C];
			for(int i = 0; i < C; ++i)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
			}
			while(R-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, cost = 0;
				LineSegment ls = new LineSegment(cities[u], cities[v]);
				for(int i = 0; i < B; ++i)
					if(towerArea[i].intersect(ls))
						cost++;
					
				adjMat[u][v] = adjMat[v][u] = Math.max(0, cost - 1);
			}
			for(int k = 0; k < C; ++k)
				for(int i = 0; i < C; ++i)
					for(int j = 0; j < C;++j)
						adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] +  adjMat[k][j]);
			out.format("Case %d:\n", tc++);
			while(Q-->0)
			{
				int ans = adjMat[sc.nextInt() - 1][sc.nextInt() - 1];
				if(ans == INF)
					out.println("Impossible");
				else
					out.println(ans);
			}


		}

		out.flush();
		out.close();


	}


	static final int INF = (int)1e9;
	static final double EPS = 1e-9;

	static class Point implements Comparable<Point>
	{
		double x, y;

		Point(double a, double b) { x = a; y = b; }

		public int compareTo(Point p)
		{
			if(Math.abs(x - p.x) > EPS) return x > p.x ? 1 : -1;
			if(Math.abs(y - p.y) > EPS) return y > p.y ? 1 : -1;
			return 0;
		}

		double dist2(Point p) { return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y); }

		Point translate(Vector v) { return new Point(x + v.x , y + v.y); }

		boolean between(Point a, Point b)
		{
			return x <= Math.max(a.x, b.x) + EPS && x + EPS >= Math.min(a.x, b.x) && y <= Math.max(a.y, b.y) + EPS && y + EPS >= Math.min(a.y, b.y);
		}

		static boolean ccw(Point p, Point q, Point r) { return new Vector(p, q).cross(new Vector(p, r)) > 0; }

		static double angle(Point a, Point o, Point b) //angle AOB
		{
			Vector oa = new Vector(o, a), ob = new Vector(o, b);
			return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2())); 
		}
		public String toString()
		{
			return "("+x+", "+y+")";
		}
	}

	static class Line 
	{
		double a, b, c; 

		Line(Point p1, Point p2)
		{
			if(Math.abs(p1.x - p2.x) < EPS) {a = 1.0; b = 0.0; c = -p1.x;}
			else
			{
				a = -(p1.y - p2.y) / (p1.x - p2.x);
				b = 1.0;
				c = -(a * p1.x) - p1.y;
			}
		}

		Line(Point p, double m) { a = -m; b = 1; c =  -(a * p.x) - p.y; } 

		boolean parallel(Line l) {return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS;}

		boolean same(Line l) {	return parallel(l) && Math.abs(c - l.c) < EPS; }

		Point intersect(Line l)
		{
			if(this.parallel(l)) return null;

			double x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
			double y;
			if(Math.abs(b) > EPS) y = -(a * x + c);
			else				  y = -(l.a * x + l.c);

			return new Point(x,y);
		}

		Point closestPoint(Point p)
		{
			if (Math.abs(b) < EPS) return new Point(-c, p.y);
			if (Math.abs(a) < EPS) return new Point(p.x, -c);

			return intersect(new Line(p, 1 / a)); 
		}


	}


	static class LineSegment
	{
		Point p, q;

		LineSegment(Point a, Point b)
		{
			p = a;
			q = b;
		}

		boolean intersect(LineSegment ls)
		{
			Line l1 = new Line(p, q), l2 = new Line(ls.p, ls.q);
			if(l1.parallel(l2))
			{
				if(l1.same(l2))
					if(p.between(ls.p, ls.q) || q.between(ls.p, ls.q) || ls.p.between(p, q) || ls.q.between(p, q))
						return true;
				return false;
			}
			Point c = l1.intersect(l2);
			if(c.between(p, q) && c.between(ls.p, ls.q))
				return true;
			return false;
		}

	}

	static class Vector 
	{
		double x, y; 

		Vector(double a, double b) { x = a; y = b; }

		Vector(Point a, Point b) { this(b.x - a.x, b.y - a.y); }

		double dot(Vector v) { return (x * v.x + y * v.y); }

		double cross(Vector v) { return x * v.y - y * v.x; }

		double norm2() { return x * x + y * y; }

		Vector rotate90() {	return new Vector(y, -x); }		
	}

	static class Polygon
	{
		Point[] g;

		Polygon(Point[] in) { g = in; }

		boolean inside(Point p)
		{
			double sum = 0.0;
			for(int i = 0; i < g.length - 1; i++)
			{
				double angle = Point.angle(g[i], p, g[i+1]);
				if((Math.abs(angle) < EPS || Math.abs(angle - Math.PI) < EPS) && p.between(g[i], g[i+1]))
					return true;
				if(Point.ccw(p, g[i], g[i+1]))
					sum += angle;
				else
					sum -= angle;
			}

			return Math.abs(2 * Math.PI - Math.abs(sum)) < EPS;
		}

		Polygon cutPolygon(Point a, Point b)	//returns the left part of the polygon, swap a & b for the right part
		{
			Point[] points = new Point[g.length<<2];
			int size = 0;

			Line l = new Line(a, b);
			for(int i = 0; i < g.length; i++)
			{
				Vector v = new Vector(a, b);
				double left1 = v.cross(new Vector(a, g[i]));
				double left2 = i == g.length - 1 ? 0 : v.cross(new Vector(a, g[i+1]));

				if(left1 + EPS >= 0)	points[size++] = g[i];
				if(left1 * left2 + EPS < 0)
					points[size++] = l.intersect(new Line(g[i], g[i+1]));
			}
			if(size != 0 && points[0] != points[size-1])
				points[size++] = points[0];
			return new Polygon(Arrays.copyOf(points, size));
		}

		boolean intersect(LineSegment ls)
		{
			for(int i = 0; i < g.length - 1; ++i)
				if(ls.intersect(new LineSegment(g[i], g[i + 1])))
					return true;
			return false;
		}

		public String toString()
		{
			return Arrays.toString(g);
		}
	}



	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}

		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}


