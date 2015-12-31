package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BrowniePoints_UVa10865 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			
			Point[] points = new Point[n];
			for(int i = 0; i < n; i++) points[i] = new Point(sc.nextInt(), sc.nextInt());
			Point c = points[n>>1];

			int a = 0, b = 0;
			for(int i = 0; i < n; i++)
			{
				Point p = points[i];
				if(p.x == c.x || p.y == c.y)
					continue;
				if(p.x > c.x && p.y > c.y || p.x < c.x && p.y < c.y)
					a++;
				else
					b++;
			}
			out.printf("%d %d\n", a, b);
		}
		out.flush();
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b)
		{
			x = a;
			y = b;
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
					res = Integer.parseInt(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Integer.parseInt(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
