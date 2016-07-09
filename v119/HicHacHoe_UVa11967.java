package v119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class HicHacHoe_UVa11967 {

	static int[] dx = new int[] {0, 1, -1, 1};
	static int[] dy = new int[] {1, 0, 1, 1};

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			TreeSet<Point> a = new TreeSet<Point>(), b = new TreeSet<Point>();
			int n = sc.nextInt(), k = sc.nextInt();
			for(int i = 0; i < n; ++i)
				if((i & 1) == 0)
					a.add(new Point(sc.nextInt(), sc.nextInt()));
				else
					b.add(new Point(sc.nextInt(), sc.nextInt()));
			boolean cross = win(a, k), nous = win(b, k);
			String ans = "none";
			if(cross && nous)
				ans = "error";
			else if(cross)
				ans = "crosses";
			else if(nous)
				ans = "noughts";
			out.printf("Case %d: %s\n", t, ans);
		}
		out.flush();
		out.close();
	}
	
	static boolean isInt(long x)
	{
		return x <= Integer.MAX_VALUE && x >= Integer.MIN_VALUE;
	}

	static boolean win(TreeSet<Point> s, int k)
	{
		for(Point p: s)
		{
			for(int i = 0; i < 4; ++i)
			{
				boolean win = true;
				for(long j = 1; win && j < k; ++j)
				{
					long x = p.x + j * dx[i], y = p.y + j * dy[i];
					if(!isInt(x) || !isInt(y) || !s.contains(new Point((int)x, (int)y)))
						win = false;
				}				
				if(win)
					return true;
			}
		}
		return false;
	}

	static class Point implements Comparable<Point>
	{
		int x, y;

		Point(int a, int b) { x = a; y = b; }

		public int compareTo(Point p)
		{
			if(x != p.x)
				return x > p.x ? 1 : -1;
			if(y != p.y)
				return y > p.y ? 1 : -1;
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}