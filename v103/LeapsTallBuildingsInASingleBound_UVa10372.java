package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class LeapsTallBuildingsInASingleBound_UVa10372 {

	static final double g = 9.8, EPS = 1e-9;

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int n = sc.nextInt();
			double[] height = new double[n + 1], dist = new double[n + 1];
			double d = 0.0;
			for(int i = 0; i < n; ++i)
			{    
				height[i] = sc.nextDouble();
				dist[i] = d;
				d += sc.nextDouble();
			}
			double v = -1, t = -1, lo = 0, hi = 1e9;
			for(int i = 0; i < 1000; ++i)
			{
				double h = (lo + hi) / 2;
				double vy = Math.sqrt(2 * g * h);
				double vx = d * g / (2 * vy);
				if(possible(vx, vy, n, height, dist))
				{
					v = Math.sqrt(vx * vx + vy * vy);
					t = Math.atan(vy / vx);
					hi = h;
				}
				else
					lo = h;
			}
			out.printf("%.2f %.2f\n", t * 180 / Math.PI, v);
		}
		out.flush();
		out.close();
	}

	static boolean possible(double vx, double vy, int n, double[] height, double[] dist)
	{
		for(int i = 1; i < n; ++i)
		{
			double t = dist[i] / vx;
			double h = vy * t - 0.5 * g * t * t;
			if(h + EPS < Math.max(height[i], height[i-1]))
				return false;
		}
		return true;
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