package v002;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class JackStraws_UVa273 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt();
			Straw[] straws = new Straw[N];
			for(int i = 0; i < N; ++i)
				straws[i] = new Straw(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			UnionFind uf = new UnionFind(N);
			for(int i = 0; i < N; ++i)
				for(int j = i + 1; j < N; ++j)
					if(straws[i].touch(straws[j]))
						uf.union(i, j);
			while(true)
			{
				int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
				if(x == -1 && y == -1)
					break;
				out.println(uf.sameSet(x, y) ? "CONNECTED" : "NOT CONNECTED");
			}
			if(tc != 0)
				out.println();
		}
		out.flush();
		out.close();
	}
	
	static class UnionFind
	{
		int[] p, rank;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		boolean sameSet(int x, int y) { return find(x) == find(y); }
		
		int find(int x) { return x == p[x] ? x : (p[x] = find(p[x])); }
		
		void union(int x, int y)
		{
			x = find(x);
			y = find(y);
			
			if(x == y)
				return;
			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					++rank[y];
			}
		}
		
	}
	
	static class Straw
	{
		Point a, b;
		
		Straw(Point x, Point y) { a = x; b = y; }
		
		boolean touch(Straw s)
		{
			Line l1 = new Line(a, b), l2 = new Line(s.a, s.b);
			if(l1.parallel(l2))
				return l1.same(l2) && (a.between(s.a, s.b) || b.between(s.a, s.b) || s.a.between(a, b));
			Point c = l1.intersect(l2);
			return c.between(a, b) && c.between(s.a, s.b);
		}
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		boolean between(Point a, Point b)
		{
			return Math.min(a.x, b.x) < x + EPS && x < Math.max(a.x, b.x) + EPS &&
					Math.min(a.y, b.y) < y + EPS && y < Math.max(a.y, b.y) + EPS;
		}
	}
	
	static class Line
	{
		double a, b, c;
		
		Line(Point p, Point q)
		{
			if(Math.abs(p.x - q.x) < EPS) { a = 1.0; b = 0.0; c = -p.x; }
			else
			{
				a = (p.y - q.y) / (q.x - p.x);
				b = 1.0;
				c = -p.x * a - p.y;
			}
		}
		
		boolean parallel(Line l) { return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS; }
		
		boolean same(Line l) { return Math.abs(c - l.c) < EPS && parallel(l); }
		
		Point intersect(Line l)
		{
			if(parallel(l))
				return null;
			double x = (l.c * b - c * l.b) / (a * l.b - l.a * b);
			double y;
			if(Math.abs(b) < EPS)
				y = - x * l.a - l.c;
			else
				y = - x * a - c;
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