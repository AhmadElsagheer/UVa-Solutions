package cp7_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AHoleToCatchAMan_UVa10060 {
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			
			double v = 0.0;
			while(n-->0)
			{
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				double t = sc.nextDouble(st.nextToken());
				ArrayList<Point> corners = new ArrayList<Point>();
				while(st.hasMoreTokens())
					corners.add(new Point(sc.nextDouble(st.nextToken()), sc.nextDouble(st.nextToken())));
				v += t * area(corners);
			}
			double r = sc.nextDouble(), t = sc.nextDouble();
			double vManHole = Math.PI * r * r * t;
			out.println((long)(v / vManHole));
						
		}
		out.flush();
	}
	
	
	static double area(ArrayList<Point> g)
	{
		double ret = 0.0;
		for(int i = 0; i < g.size() - 1; i++)
			ret += g.get(i).x * g.get(i+1).y - g.get(i).y * g.get(i+1).x;
		return Math.abs(ret) / 2.0;
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
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
		
		public double nextDouble(String x) throws IOException
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
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
