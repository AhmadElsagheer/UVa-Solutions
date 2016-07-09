package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class FindingRectangles_UVa638 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			Point[] points = new Point[n];
			char[][] pointLabel = new char[50][50];
			for(int i = 0; i < 50; ++i)
				Arrays.fill(pointLabel[i], '$');
			for(int i = 0; i < n; ++i)
			{
				Point p = points[i] = new Point(sc.next().charAt(0), sc.nextInt(), sc.nextInt());
				pointLabel[p.x][p.y] = p.l;
			}

			ArrayList<String> ans = new ArrayList<String>();
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					if(i != j) for(int k = 0; k < n; ++k)
						if(i != k && j != k)
						{
							Point a = points[i], b = points[j], c = points[k];
							if(makeRectangle(a, b, c))
							{
								int x = a.x, y = c.y;
								char l = pointLabel[x][y];
								if(l != '$')
									ans.add(" " + a.l + b.l + c.l + l);
							}
						}
			if(ans.size() == 0)
				out.printf("Point set %d: No rectangles\n", tc++);
			else
			{
				Collections.sort(ans);
				out.printf("Point set %d:\n", tc++);
				for(int i = 0, k = 10; i < ans.size(); ++i, --k)
				{
					if(k == 0)
					{
						k = 10;
						out.println();
					}
					out.print(ans.get(i));
				}
				out.println();
			}

		}
		out.flush();
		out.close();
	}

	static boolean makeRectangle(Point a, Point b, Point c)
	{
		return a.y == b.y && b.x == c.x && a.y > c.y && a.x < b.x;
	}

	static class Point
	{
		int x, y;
		char l;

		Point(char a, int b, int c) { l = a; x = b; y = c; }
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