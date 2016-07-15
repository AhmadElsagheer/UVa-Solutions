package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IsThisIntegration_UVa10209 {
	/*
	 * Another solution
	 * ----------------
	 * 1. left the circle with center at A is C, center is O(0, 0)
	 * 2. find y = h for which x = a / 2 using the equation of the circle
	 * 3. let (a / 2, h) be Q and (a, h) be S, find the angle QBS
	 * 4. find the area of the segment QC (call it Z)
	 * 5. the checkered area = a * (a - h) - 2 * Z 
	 */
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			double[] ans = go(sc.nextDouble());
			out.printf("%.3f %.3f %.3f\n", ans[0], ans[1], ans[2]);
		}
		out.flush();
	
	}
	
	static double[] go(double a)
	{
		double[] out = new double[3];
		
		double x = a * a * (0.5 * Math.PI - 1);
		double y = sectorArea(30, a) + 2 * segmentArea(60, a);
		
		out[1] = 4 * (x - y);
		out[0] = x - 2 * (x - y);
		out[2] = a * a - out[0] - out[1];
		
		return out;
	}
		
	static double sectorArea(double deg, double r) { return deg / 360.0 * Math.PI * r * r; }
	
	static double segmentArea(double deg, double r)
	{
		return sectorArea(deg, r) - r * r * Math.sin(degToRad(deg)) / 2.0;
	}
	
	static double degToRad(double deg) { return deg *Math.PI / 180.0; }
	
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
