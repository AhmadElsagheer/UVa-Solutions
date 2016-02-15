package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DogAndGopher_UVa10310 {

	static final double EPS = 1e-9;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(sc.ready())
		{
			int n = sc.nextInt();
			Point g = new Point(sc.next(), sc.next()), d  = new Point(sc.next(), sc.next());
			Point ans = null;
			for(int i = 0; i < n; ++i)
			{
				Point cur = new Point(sc.next(), sc.next());
				if(ans == null && cur.dist2(g) * 4  < cur.dist2(d) + EPS)
					ans = cur;
			}
			if(ans == null)
				out.format("The gopher cannot escape.\n");
			else
				out.format("The gopher can escape through the hole at (%.3f,%.3f).\n", ans.x, ans.y);
		}

		out.flush();
		out.close();
	}	
	
	static class Point
	{
		double x, y;
				
		Point(String aa, String bb)
		{
			x = toDouble(aa);
			y = toDouble(bb);
		}
		
		double dist2(Point p)
		{
			return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
		}
		
		public double toDouble(String x) 
		{
			
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


	}
}
