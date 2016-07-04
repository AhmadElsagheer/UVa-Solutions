package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MouseClicks_UVa142 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Region> regions = new ArrayList<Region>();
		ArrayList<Point> icons = new ArrayList<Point>();
		
		while(true)
		{
			char c = sc.next().charAt(0);
			if(c == '#')
				break;
			if(c == 'R')
			{
				Region r = new Region(new Point(sc.nextInt(), sc.nextInt()), new Point(sc.nextInt(), sc.nextInt()));
				regions.add(r);
				for(Point p: icons)
					if(r.contains(p))
						p.visible = false;
			}
			else if(c == 'I')
			{	
				Point icon = new Point(sc.nextInt(), sc.nextInt()); 
				icons.add(icon);
				for(Region r: regions)
					if(r.contains(icon))
						icon.visible = false;
			}
			else
			{
				Point click = new Point(sc.nextInt(), sc.nextInt());
				int ans = -1;
				for(int i = 0; i < regions.size(); ++i)
				{
					Region r = regions.get(i);
					if(r.contains(click))
						ans = i;
				}
				
				if(ans != -1)
					out.println((char)(ans + 'A'));
				else
				{
					ArrayList<Integer> sol = new ArrayList<Integer>();
					int min = Integer.MAX_VALUE;
					for(int i = 0; i < icons.size(); ++i)
					{
						Point icon = icons.get(i);
						if(!icon.visible)
							continue;
						int d2 = icon.dist2(click);
						if(d2 < min)
						{
							min = d2;
							sol = new ArrayList<Integer>();
						}
						
						if(d2 == min)
							sol.add(i + 1);
					}
					for(int x: sol)
						out.printf("%3d", x);
					out.println();
				}
			}
		}
		out.flush();
		out.close();
	}
	
	static class Point
	{
		int x, y;
		boolean visible = true;
		
		Point(int a, int b) { x = a; y = b; }
		
		int dist2(Point p) { return sq(x - p.x) + sq(y - p.y); }
		
		int sq(int x) { return x * x; }
	}
	
	static class Region
	{
		Point tl, br;
		
		Region(Point a, Point b) { tl = a; br = b; }
		
		boolean contains(Point p) { return tl.x <= p.x && p.x <= br.x && tl.y <= p.y && p.y <= br.y; }
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
	}
}