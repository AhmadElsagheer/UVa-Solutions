package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class LiningUp_UVa270 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		sc.nextLine();
		while(tc-->0)
		{
			ArrayList<Point> points = new ArrayList<Point>();
			while(true)
			{
				String line = sc.nextLine();
				if(line == null || line.isEmpty())
					break;
				StringTokenizer st = new StringTokenizer(line);
				points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			int ans = 0, n = points.size();
			TreeMap<Line, Integer> lines = new TreeMap<Line, Integer>();
			for(int i = 0; i < n; ++i)
			{
				for(int j = i + 1; j < n; ++j)
				{
					Line l = new Line(points.get(i), points.get(j));
					Integer f = lines.get(l);
					if(f == null)
						f = 1;
					lines.put(l, f + 1);

				}
				for(int x: lines.values())
					ans = Math.max(ans, x);
				lines = new TreeMap<Line, Integer>();
			}
			out.println(ans);
			if(tc != 0)
				out.println();

		}
		out.flush();
		out.close();
	}

	static final double EPS = 1e-9;

	static class Point
	{
		int x, y;

		Point(int a, int b)
		{
			x = a; y = b;
		}
	}


	static class Line implements Comparable<Line>
	{
		double a, b, c;

		Line(Point p, Point q)
		{
			if(p.x == q.x)
			{
				a = 1.0; b = 0.0; c = -p.x;
			}
			else
			{
				a = (double)(p.y - q.y) / (q.x - p.x);
				b = 1.0;
				c = - a * p.x - p.y;
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


	}
}
