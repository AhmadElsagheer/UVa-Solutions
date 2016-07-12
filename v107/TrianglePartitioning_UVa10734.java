package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TrianglePartitioning_UVa10734 {

	static final double EPS = 1e-9;
	static TreeSet<Triangle> set;
	
	static void countStyles(Triangle t)
	{
		if(set.contains(t))
			return;
		set.add(t);
		makeTriangle(t.a, t.b, t.c);
	}
	
	static void makeTriangle(double a, double b, double c)
	{
		double cos = (a * a + c * c - b * b) / (2 * a * c);
		double d = c / 2, m = Math.sqrt(a * a + d * d - 2 * a * d * cos);
		countStyles(new Triangle(a, d, m));
		countStyles(new Triangle(b, d, m));
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			set = new TreeSet<Triangle>();
			countStyles(new Triangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble()));
			out.printf("Triangle %d: %d\n", t, set.size());
		}
		out.flush();
		out.close();
	}
	
	static class Triangle implements Comparable<Triangle>
	{
		double x, y, z, a, b, c;
		
		Triangle(double a, double b, double c)
		{
			double[] sides = new double[] { a, b, c};
			Arrays.sort(sides);
			this.a = sides[0];
			this.b = sides[1];
			this.c = sides[2];
			double[] angles = new double[3];
			angles[0] = Math.acos((a * a + b * b - c * c) / (2 * a * b));
			angles[1] = Math.acos((a * a + c * c - b * b) / (2 * a * c));
			angles[2] = Math.PI - (angles[0] + angles[1]);
			Arrays.sort(angles);
			x = angles[0];
			y = angles[1];
			z = angles[2];
		}
			
		public int compareTo(Triangle t)
		{
			if(Math.abs(x - t.x) > EPS)
				return x > t.x ? 1 : -1;
			if(Math.abs(y - t.y) > EPS)
				return y > t.y ? 1 : -1;
			if(Math.abs(z - t.z) > EPS)
				return z > t.z ? 1 : -1;
			return 0;
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