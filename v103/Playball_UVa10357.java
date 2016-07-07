package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Playball_UVa10357 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int p = Integer.parseInt(sc.next().substring(8));
		Player[] players = new Player[p];
		for(int i = 0; i < p; i++) players[i] = new Player(new Point(sc.nextInt(), sc.nextInt()), sc.nextInt());
		
		int b = Integer.parseInt(sc.next().substring(6));
		for(int k = 1; k <= b; k++)
		{
			int[] a = new int[7];
			for(int i = 0; i < 7; i++) a[i] = sc.nextInt();
			
			int t = f(a[0], a[1], a[2]), x = a[3] * t + a[4], y = a[5] * t + a[6];
			if(x < 0 || y < 0)
				out.printf("Ball %d was foul at (%d,%d)\n",k, x, y);
			else
			{
				boolean reachable = false;
				Point c = new Point(x, y);
				for(int i = 0; i < p && !reachable; i++)
					if(players[i].canReach(c, t))
						reachable = true;
				if(reachable)
					out.printf("Ball %d was caught at (%d,%d)\n",k, x, y);
				else
					out.printf("Ball %d was safe at (%d,%d)\n",k, x, y);
			}
		}
		out.flush();
	}
	
	static int f(int a, int b, int c)
	{
		double rt = Math.sqrt(b * b - 4 * a * c);
		if(Math.ceil((-rt - b) / (2 * a)) > 0)
			return (int) Math.ceil((-rt - b) / (2 * a));
		return (int) Math.ceil((rt - b) / (2 * a));
	}
	
	static class Player
	{
		Point pos;
		int v;
		
		Player(Point p, int k)
		{
			pos = p;
			v = k;
		}
		
		boolean canReach(Point p, int t)
		{
			return pos.dist(p) <= v * t;
		}
	}
	static class Point
	{
		int x, y;
		
		Point(int a, int b)
		{
			x = a;
			y = b;
		}
		
		double dist(Point p)
		{
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
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
