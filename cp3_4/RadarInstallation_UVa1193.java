package cp3_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RadarInstallation_UVa1193 {

	static final double EPS = 1e-9;
	static int d;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			d = sc.nextInt();
			Island[] islands = new Island[n];
			
			boolean possible = true;
			for(int i = 0; i < n; ++i)
			{
				int x = sc.nextInt(), y = sc.nextInt();
				if(y > d)
					possible = false;
				else
					islands[i] = new Island(x, y, x + Math.sqrt((double) d * d - (double) y * y));
			}
			int ans = 0;
			if(!possible)
				ans = -1;
			else
			{
				boolean[] covered = new boolean[n];
				Arrays.sort(islands);
				for(int i = 0; i < n; ++i)
					if(!covered[i])
					{
						++ans;
						for(int j = i; j < n; ++j)
							if(insideCircle(islands[j], islands[i].c))
								covered[j] = true;
							else
								break;
					}
			}
			out.format("Case %d: %d\n", tc++, ans);
		}
		out.flush();
	}
	
	static boolean insideCircle(Island p, double c)
	{
		 return ((double)p.y * p.y + (c - p.x) * (c - p.x)) <= (double) d * d + EPS;
	}
	
	static class Island implements Comparable<Island>
	{
		int x, y;
		double c;
		Island(int a, int b, double z) { x = a; y = b; c = z; }
		
		public int compareTo(Island p)
		{
			return c > p.c ? 1 : -1;
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
