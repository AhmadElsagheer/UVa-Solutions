package cp8_4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class LaserLines_UVa184 {
	
	/*
	 * Since the number of points is small, it can be solved
	 * using only collinear test O(n^3)
	 */
	static final double EPS = 1e-9;

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			TreeMap<Line, TreeSet<Point>> map = new TreeMap<Line, TreeSet<Point>>();

			ArrayList<Point> points = new ArrayList<Point>();
			int x = sc.nextInt(), y = sc.nextInt();
			if(x + y == 0)
				break;
			while(true)
			{
				points.add(new Point(x, y));
				x = sc.nextInt(); y = sc.nextInt();
				if(x + y == 0)
					break;
			}
			Collections.sort(points);
			for(int i = 0, sz = points.size(); i < sz; ++i)
			{
				Point p = points.get(i);
				for(int j = i + 1; j < sz; ++j)
				{
					Point q = points.get(j);
					Line l = new Line(p, q);
					TreeSet<Point> onLine = map.get(l);
					if(onLine == null)
					{
						map.put(l, onLine = new TreeSet<Point>());
						onLine.add(p);
					}
					onLine.add(q);
				}
			}
			ArrayList<TreeSet<Point>> lineSets = new ArrayList<TreeSet<Point>>();
			for(Entry<Line, TreeSet<Point>> e: map.entrySet())
				if(e.getValue().size() > 2)
					lineSets.add(e.getValue());
			if(lineSets.isEmpty())
				out.println("No lines were found");
			else
			{
				out.println("The following lines were found:");
				ArrayList<Point>[] lines = new ArrayList[lineSets.size()];
				int idx = 0;
				for(TreeSet<Point> set: lineSets)
					lines[idx++] = new ArrayList<Point>(set);
				Arrays.sort(lines, new Sorter());
				for(ArrayList<Point> l: lines)
					if(l.size() > 2)
					{
						for(Point p: l)
							out.printf("(%4d,%4d)", p.x, p.y);
						out.println();
					}
			}
		}

		out.flush();
		out.close();
	}

	static class Sorter implements Comparator<ArrayList<Point>>
	{
		public int compare(ArrayList<Point> x, ArrayList<Point> y)
		{
			Point a = x.get(0), b = y.get(0);
			if(a.compareTo(b) != 0)
				return a.compareTo(b);
			return x.get(1).compareTo(y.get(1));
		}
	}

	static class Point implements Comparable<Point>
	{
		int x, y;

		Point(int a, int b) { x = a; y = b; }

		public int compareTo(Point p)
		{
			if(x != p.x)
				return x - p.x;
			return y - p.y;
		}
	}

	static class Line implements Comparable<Line>{

		double a, b, c;

		Line(Point p, Point q)
		{
			if(Math.abs(p.x - q.x) < EPS) {	a = 1; b = 0; c = -p.x;	}
			else
			{
				a = 1.0 * (p.y - q.y) / (q.x - p.x);
				b = 1.0;
				c = -(a * p.x + p.y);
			}

		}

		public int compareTo(Line l)
		{
			if(Math.abs(a - l.a) > EPS)
				return a > l.a ? 1 : -1;
				if(Math.abs(b - l.b) > EPS)
					return b > l.b ? 1 : -1;
					if(Math.abs(c - l.c) > EPS)
						return c > l.c ? 1 : -1;
						return 0;
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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