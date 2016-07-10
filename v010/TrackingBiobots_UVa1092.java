package v010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrackingBiobots_UVa1092 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int m = sc.nextInt(), n = sc.nextInt(), w = sc.nextInt();
			if(m == 0)
				break;
			Wall[] walls = new Wall[w + m];
			// Normal walls
			for(int i = 0; i < w; ++i)
				walls[i] = new Wall(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			
			// Add walls at the right end
			for(int i = w; i < w + m - 1; ++i)
				walls[i] = new Wall(n, i - w, n, i - w);
			
			// Add the upper wall
			walls[w + m - 1] = new Wall(0, m, n - 1, m);
			Arrays.sort(walls);
			long ans = 0;
			for(int i = 0; i < w + m - 1; ++i)
			{
				if(walls[i + 1].y == walls[i].y && walls[i + 1].x1 == walls[i].x2 + 1)
				{
					walls[i + 1].x1 = walls[i].x1;	//merge adjacent walls
					continue;
				}
				
				for(int j = i; j < w + m; ++j)
					if(walls[j].y + 1 < walls[i].y)
						break;
					else if(walls[j].y + 1 == walls[i].y)
						ans += walls[i].merge(walls[j]);
			}
			out.printf("Case %d: %d\n", tc++, ans);
		}
		out.flush();
		out.close();
	}

	static class Wall implements Comparable<Wall>
	{
		int x1, x2, y;

		Wall(int a, int d, int b, int c) { x1 = a; x2 = b; y = c; }

		public int compareTo(Wall w)
		{
			if(y != w.y)
				return w.y - y;
			return x1 - w.x1;
		}

		int merge(Wall w)
		{
			int ret = 0;
			if(w.x1 >= x1 && w.x1 <= x2 + 1)
			{
				ret += w.x1 - x1;
				w.x1 = x1;
			}
			
			if(w.x2 >= x1 && w.x2 <= x2)
				x1 = w.x2 + 1;
			
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

	}
}