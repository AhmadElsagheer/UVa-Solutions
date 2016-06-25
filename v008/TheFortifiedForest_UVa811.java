package v008;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheFortifiedForest_UVa811 {
	
	static final double EPS = 1e-9;
	static int N, minVal, cuttedTrees;
	static double extraWood;
	static Tree[] trees;
	
	static void bt(int idx, int cut)
	{
		if(idx == N)
		{
			//try building fence and minimize
			Point[] remTrees = new Point[N - Integer.bitCount(cut)];
			int totalWood = 0, remIdx = 0, cutVal = 0;
			for(int i = 0; i < N; ++i)
				if((cut & 1<<i) == 0)
					remTrees[remIdx++] = trees[i].loc;
				else
				{
					totalWood += trees[i].height;
					cutVal += trees[i].val;
				}
			Polygon p = new Polygon(remTrees);
			double minFenceLength = p.perimeter();
			if(totalWood + EPS > minFenceLength && cutVal < minVal)
			{
				cuttedTrees = cut;
				minVal = cutVal;
				extraWood = totalWood - minFenceLength;
			}
			return;
		}
		
		bt(idx + 1, cut | 1<<idx); 		//cut tree
		bt(idx + 1, cut);    		  	//don't cut
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			if(tc != 1)
				out.println();
			trees = new Tree[N];
			for(int i = 0; i < N; ++i)
				trees[i] = new Tree(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			minVal = Integer.MAX_VALUE;
			bt(0, 0);
			out.printf("Forest %d\nCut these trees:", tc++);
			for(int i = 0; i < N; ++i)
				if((cuttedTrees & 1<<i) != 0)
					out.print(" " + (i + 1));
			out.printf("\nExtra wood: %.2f\n", extraWood);
		}
		
		out.flush();
		out.close();
	}
	
	static boolean ccw(Point p, Point q, Point r)
	{
		return new Vector(p, q).cross(new Vector(p, r)) > 0;
	}
	
	static class Tree
	{
		int val, height;
		
		Point loc;
		
		Tree(int x, int y, int v, int l)
		{
			loc = new Point(x, y);
			val = v;
			height = l;
		}
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		double dist(Point p) { return Math.sqrt(sq(x - p.x) + sq(y - p.y)); }

		double sq(double x) { return x * x; }		
		
		public int compareTo(Point p)
		{
			if(x != p.x)
				return x - p.x;
			return y - p.y;
		}
	}
	
	static class Vector
	{
		int x, y;
		
		Vector(Point p, Point q) { x = q.x - p.x; y = q.y - p.y; }
		
		int cross(Vector v) { return x * v.y - y * v.x; }
	}
	
	static class Polygon
	{
		Point[] g;
		
		Polygon(Point[] points)
		{
			//Convex Polygon Construction
			int n = points.length;
			if(n == 0)
			{
				g = new Point[0];
				return;
			}
			Arrays.sort(points);
			g = new Point[n<<1];
			int size = 0, start = 0;
			
			for(int i = 0; i < n; ++i)
			{
				Point p = points[i];
				while(size - start >= 2 && !ccw(g[size-2], g[size-1], p)) --size;
				g[size++] = p;
			}
			
			start = --size;
			for(int i = n - 1; i >= 0; --i)
			{
				Point p = points[i];
				while(size - start >= 2 && !ccw(g[size-2], g[size-1], p)) --size;
				g[size++] = p;
			}
			
			g = Arrays.copyOf(g, size);
		}
		
		double perimeter()
		{
			double ret = 0;
			for(int i = 0; i < g.length - 1; ++i)
				ret += g[i].dist(g[i + 1]);
			return ret;
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}