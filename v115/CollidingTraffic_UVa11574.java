package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CollidingTraffic_UVa11574 {
	
	/*
	 * Another solution: brute force + binary search
	 * For every pair of boats, do binary search to find min t to collide
	 * => Get mid
	 * => if dist <= r, ans = hi = mid
	 * => else check (mid - dt, mid + dt) to detect whether function is increasing or decreasing at this point
	 */
	static final double EPS = 1e-9, INF = 1e9;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt();
			double r = sc.nextDouble();
			
			Boat[] boats = new Boat[N];
			for(int i = 0; i < N; ++i)
				boats[i] = new Boat(sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
			boolean collide = false;

			double min = INF;
			for(int i = 0; i < N; ++i)
			{
				Boat b1 = boats[i];
				for(int j = i + 1; j < N; ++j)
				{
					Boat b2 = boats[j];
					if(b1.collide(b2, r))
					{
						min = 0.0;
						collide = true;
						continue;
					}
					double vx = b1.vx - b2.vx, vy = b1.vy - b2.vy;
					double x = b1.x - b2.x, y = b1.y - b2.y;
					double a = sq(vx) + sq(vy);
					double b = 2 * (vx * x + vy * y); 
					double c = sq(x) + sq(y) - r * r;
					double d = sq(b) - 4 * a * c;
					if(d + EPS < 0 || Math.abs(a) < EPS)
						continue;
					double t = (-b - Math.sqrt(d)) / (2 * a);	//1st sol (-ve) at their entrance to circle
																//2nd sol (+ve) at their exit to circle
					if(t + EPS > 0)
					{
						min = Math.min(min, t);
						collide = true;
					}
				}
			}
			
			if(!collide)
				out.println("No collision.");
			else
				out.println(Math.round(min));
		}
		out.flush();
		out.close();
	}
	
	static double sq(double x) { return x * x; }
	
	static double degToRad(double theta) { return theta * Math.PI / 180.0; }
	
	static class Boat
	{
		double x, y, vx, vy;
		
		Boat(double a, double b, double d, double s)
		{
			x = a; y = b;
			double theta = degToRad(90 - d);
			vx = s * Math.cos(theta);
			vy = s * Math.sin(theta);
		}
		
		double getX(double t) { return x + vx * t; }
		
		double getY(double t) { return y + vy * t; }
		
		boolean collide(Boat b, double r) { return sq(x - b.x) + sq(y - b.y) < r * r + EPS; }
	
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