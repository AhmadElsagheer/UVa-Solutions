package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Logo_UVa11505 {
	
	static final double INF = 1e5;

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Point start = new Point(0, 0), end = start;
			Vector v = new Vector(0, INF);
			while(n-->0)
			{
				double d = 1;
				switch(sc.next().charAt(0))
				{
				case 'r':
					d *= -1;
				case 'l':
					d *= sc.nextInt();
					v = v.rotate(d);
					break;
				case 'f':
					d = sc.nextInt();
					end = end.translate(v.scale(d / v.norm()));break;
					default:
						d = sc.nextInt();
						end = end.translate(v.reverse().scale(d / v.norm()));
						
				}
			}
			out.println(Math.round(end.dist(start)));
		}
		out.flush();
	}
	
	
	static double degToRad(double deg)
	{
		return deg * Math.PI / 180.0;
	}
	
	static class Point
	{
		double x, y;
		
		Point(double a, double b)
		{
			x = a; y = b;
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y);
		}
		

		double dist(Point p)
		{
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}
		
		
	}
	
	static class Vector
	{
		double x, y;
		
		Vector(double a, double b)
		{
			x = a; y = b;
		}
		
		Vector rotate(double angle)
		{
			angle = degToRad(angle);
			double c = Math.cos(angle), s = Math.sin(angle);
			return new Vector(x * c - y * s, x * s + y * c);
		}
		
		Vector reverse()
		{
			return new Vector(-x, -y);
		}
		
		Vector scale(double s)
		{
			return new Vector(x *s, y * s);
		}
		
		double norm()
		{
			return Math.sqrt(x * x + y * y);
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
