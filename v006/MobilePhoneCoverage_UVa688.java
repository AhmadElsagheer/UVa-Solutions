package v006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MobilePhoneCoverage_UVa688 {

	static final double EPS = 1e-9;
	
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
			
			Rectangle[] recs = new Rectangle[n];
			double[] x = new double[n<<1], y = new double[n<<1];
			for(int i = 0; i < n; ++i)
			{
				Rectangle r = recs[i] = new Rectangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
				x[i<<1] = r.ll.x;
				x[i<<1|1] = r.ur.x;
				y[i<<1] = r.ll.y;
				y[i<<1|1] = r.ur.y;
			}
			
			Arrays.sort(x);
			Arrays.sort(y);
	
			double ans = 0;
			for(int i = 0, end = (n<<1)-1; i < end; ++i)
				for(int j = 0; j < end; ++j)
				{
					double x1 = x[i], x2 = x[i+1], y1 = y[j], y2 = y[j+1];
					Point p = new Point((x1+x2)/2, (y1+y2)/2);
					for(Rectangle r: recs)
						if(r.contains(p))
						{
							ans += (x2 - x1) * (y2 - y1);
							break;
						}		
				}
			out.printf("%d %.2f\n", tc++, ans);
		}
		out.flush();
		out.close();
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
	}
	
	static class Rectangle
	{
		Point ll, ur;
		
		Rectangle(double x, double y, double r)
		{
			ll = new Point(x - r, y - r);
			ur = new Point(x + r, y + r);
		}
		
		boolean contains(Point p)
		{
			return ll.x < p.x + EPS && p.x < ur.x + EPS && ll.y < p.y + EPS && p.y < ur.y + EPS;
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