package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Satellites_UVa10221 {
	
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		
		while(sc.ready())
		{
			int r = 6440 + sc.nextInt();
			double angle = sc.nextInt();
			if(sc.next().equals("min"))
				angle /= 60.0;
			if(angle > 180)
				angle = 360 - angle;
			out.printf("%.6f %.6f\n", arcLength(r, angle), chordLength(r, angle));
		}
		out.flush();
	}
	
	static double arcLength(double r, double angle)
	{
		return angle / 360.0 * circum(r);
	}
	
	static double circum(double r)
	{
		return Math.PI * 2 * r;
	}
	
	static double degToRad(double deg)
	{
		return Math.PI * deg / 180.0;
	}
	
	static double chordLength(double r, double angle)
	{
		return 2 * r * Math.sin(degToRad(angle) / 2.0);
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
