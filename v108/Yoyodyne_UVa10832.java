package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Yoyodyne_UVa10832 {

	static final double EPS = 1e-9, INF = 1e9;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int k = 1;
		while(true)
		{
			double f = sc.nextInt(), b = sc.nextInt(), r = sc.nextInt();
			int n = sc.nextInt();
			if(n == 0) break;
			
			Point[] buoys = new Point[n];
			for(int i = 0; i < n; i++) buoys[i] = new Point(sc.nextInt(), sc.nextInt(), sc.nextInt());
			
			Point start = new Point(0, 0, 1), end = start;
			double totalDistance = 0, totalTime = 0;
			boolean success = true;
			for(int i = 0; i < n; i++)
			{
				int idx = -1;
				double d = INF;
				for(int j = 0; j < n; j++)
					if(buoys[j] != null && buoys[j].dist(end) + EPS < d)
					{
						d = buoys[j].dist(end);
						idx = j;
					}
				Point c = buoys[idx];
				buoys[idx] = null;
				
				if(success)
					if(d / r * b <= EPS + f)
					{
						f -= d / r * b;
						totalDistance += d;
						totalTime += d / r;
						end = c;
					}
					else
					{
						success = false;
						
						Vector v = new Vector(end, c);
						v = v.scale(f / b * r /v.norm());
						start = end.translate(v);
						totalDistance += f / b * r;
					}
				end = c;
			}
			if(success)
				out.printf("Mission %d: SUCCESS!! Time: %.2f  Traveled: %.2f  Fuel Left: %.2f\n",k++, round(totalTime), round(totalDistance), round(f));
			else
				out.printf("Mission %d: FAILURE!! Traveled: %.2f  From Home: %.2f\n", k++, round(totalDistance), round(end.dist(start)));
		}
		out.flush();
	}
	
	static double round(double d)
	{
		return Math.round(d * 100) / 100.0;
	}
	static class Point
	{
		double x, y, z;
		
		Point(double a, double b, double c)
		{
			x = a; y = b; z = c;
		}
		
		double dist(Point p)
		{
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) + (z - p.z) * (z - p.z));
		}
		
		Point translate(Vector v)
		{
			return new Point(x + v.x, y + v.y, z + v.z);
		}
	}

	static class Vector
	{
		double x, y, z;
		
		Vector(Point p, Point q)
		{
			x = q.x - p.x;
			y = q.y - p.y;
			z = q.z - p.z;
		}
		
		Vector(double a, double b, double c)
		{
			x = a;
			y = b;
			z = c;
		}
		double norm()
		{
			return Math.sqrt(x * x + y * y + z * z);
		}
		
		Vector scale(double s)
		{
			return new Vector(x * s, y * s, z * s);
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
