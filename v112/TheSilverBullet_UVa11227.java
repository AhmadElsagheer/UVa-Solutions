package v112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class TheSilverBullet_UVa11227 {

	static final double EPS = 1e-9;

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			TreeSet<Point> set = new TreeSet<Point>();
			int n = sc.nextInt();
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++)
			{
				Point p = new Point(sc.nextDouble(), sc.nextDouble());
				if(!set.contains(p))
				{
					points[i] = p;
					set.add(p);
				}
				else
				{
					n--; i--;
				}
			}
			int max = 2;
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
				{
					int cur = 2;
					for(int k = 0; k < n; k++)
						if(k != i && k != j && collinear(points[i], points[j], points[k]))
							cur++;
					max = Math.max(cur, max);
				}
			if(n == 1)
				out.format("Data set #%d contains a single gnu.\n", t);
			else
				out.format("Data set #%d contains %d gnus, out of which a maximum of %d are aligned.\n", t, n, max);

		}
		out.flush();
		out.close();

	}
	static boolean collinear(Point a, Point b, Point c)
	{
		return Math.abs(new Vector(a, b).cross(new Vector(a, c))) < EPS;
	}

	static class Point implements Comparable<Point>
	{
		double x, y;

		Point(double a, double b) { x = a; y = b; }

		public int compareTo(Point p)
		{
			if(Math.abs(x - p.x) > EPS)
				return x > p.x ? 1 : -1;
				if(Math.abs(y - p.y) > EPS)
					return y > p.y ? 1 : -1;
					return 0;
		}


	}

	static class Vector
	{
		double x, y;

		Vector(Point a, Point b) { x = b.x - a.x; y = b.y - a.y; }

		double cross(Vector v)
		{
			return x * v.y - y * v.x;
		}
	}
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;

		Scanner(InputStream s)
		{
			br = new BufferedReader(new InputStreamReader(s));
		}

		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		int nextInt() throws NumberFormatException, IOException
		{
			return Integer.parseInt(next());
		}

		double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0.0, f = 1;
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
					if(dec) f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}

	}
}
