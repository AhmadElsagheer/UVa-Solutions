package regionals.latinAmerica2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class HideAndSeek {

	static final double EPS = 1e-11;
	static class Point implements Comparable<Point>
	{
		int x, y, s = -1;							//s = -1 for kid, even for wall start, odd for wall end
		double angle;
		Point(int a, int b) { x = a; y = b; }
		
		void set()
		{
			angle = angle(this, O, X);
			if(!ccw(O, X, this))
				angle = Math.PI * 2 - angle;
		}
		
		public int compareTo(Point p)
		{
			return angle > p.angle ? 1 : -1;
		}

	}
	
	static class Wall implements Comparable<Wall>
	{
		Point start, end;			//ccw according to the seeking kid
		Wall(Point p, Point q) { start = p; end = q; }
		
		void set(int k) { start.s = k<<1; end.s = (k<<1)+1; start.set(); end.set(); }
		
		void swap() { Point tmp = start; start = end; end = tmp; }
		
		public int compareTo(Wall w)
		{
			if(w.start.equals(start) && w.end.equals(end))
				return 0;
			if(ccw(O, w.start, start) && !ccw(O, w.end, start))
				return ccw(w.start, w.end, O) != ccw(w.start, w.end, start) ? 1 : -1;
			if(ccw(O, w.start, end) && !ccw(O, w.end, end))
				return ccw(w.start, w.end, O) != ccw(w.start, w.end, end) ? 1 : -1;
			return ccw(start, end, O) != ccw(start, end, w.end) ? -1 : 1; 
		}
	
	} 
	
	static class Vector
	{
		int x, y;
		
		Vector(Point p, Point q) { x = q.x - p.x; y = q.y - p.y; }
		
		long cross(Vector v) { return (long) x * v.y - (long) y * v.x; }
		
		long dot(Vector v) { return (long) x * v.x + (long) y * v.y; }
		
		long norm2() { return (long) x * x + (long) y * y; }
	}
	
	static boolean ccw(Point p, Point q, Point r)
	{
		return new Vector(p, q).cross(new Vector(p, r)) >= 0;
	}
	
	static double angle(Point a, Point o, Point b)
	{
		Vector oa = new Vector(o, a), ob = new Vector(o, b);
		return Math.acos(oa.dot(ob) / Math.sqrt(oa.norm2() * ob.norm2())); 
	}
	
	static Point O, X;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int S = sc.nextInt(), K = sc.nextInt(), W = sc.nextInt();
			Point[] kids = new Point[K];
			for(int i = 0; i < K; ++i)
				kids[i] = new Point(sc.nextInt(), sc.nextInt());
			Wall[] walls = new Wall[W];
			for(int i = 0; i < W; ++i)
				walls[i] = new Wall(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
			
			for(int i = 0, k = 0; i < S; ++i, k = 0)
			{

				O = kids[i]; X = new Point(O.x + 10, O.y);
				
				Point[] L = new Point[(W<<1) + K - 1];
				for(int j = 0; j < K; ++j)
				if(i != j)
				{
					Point cur = kids[j];
					cur.set();
					L[k++] = cur;
				}
				
				for(int j = 0; j < W; ++j)
				{	
					Wall cur = walls[j];
					if(!ccw(O, cur.start, cur.end))
						cur.swap();
					cur.set(j);
					L[k++] = cur.start;
					L[k++] = cur.end;
				}
				Arrays.sort(L);
			
				TreeSet<Wall> R = new TreeSet<Wall>();
				for(int j = 0; j < W; ++j)
					if(ccw(O, X, walls[j].start) != ccw(O, X, walls[j].end) && ccw(O, walls[j].start, X))
						R.add(walls[j]);
				int ans = 0;
				for(int j = 0; j < L.length; ++j)
				{
					Point p = L[j];
					if(p.s == -1)
					{
						if(R.isEmpty())
							ans++;
						else
						{
							Wall w = R.first();
							if(ccw(w.start, w.end, O) == ccw(w.start, w.end, p))
								ans++;
						}
					}
					else
						if((p.s & 1) == 0)
							R.add(walls[p.s>>1]);
						else
							R.remove(walls[p.s>>1]);
						
				}
				out.println(ans);
			}
		}
		out.flush();
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
		
		public boolean ready() throws IOException {return br.ready();}


	}

}
