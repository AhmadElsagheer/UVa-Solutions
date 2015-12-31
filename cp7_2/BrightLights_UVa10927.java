package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BrightLights_UVa10927 {

	/*
	 * Another solution
	 * ----------------
	 * 1. sort points according to their polar angles
	 * 2. loop through points using an angle sweep, save maxH
	 * 3. a. if the current point has a different angle, update angle and maxH
	 * 	  b. else if the current point has h <= maxH, add point to ans
	 *    c. else update maxH
	 */
	static final double EPS = 1e-11;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int k = 1;
		while(true)
		{
			ArrayList<Point> ans = new ArrayList<Point>();
			TreeMap<Line, Line> map = new TreeMap<Line, Line>();
			int n = sc.nextInt();
			if(n == 0) break;
			Point[] poles = new Point[n];
				for(int i = 0; i < n; i++)
					poles[i] = new Point(sc.nextInt(), sc.nextInt(), sc.nextInt());
				
			Arrays.sort(poles);
				
			
			
			for(int i = 0; i < n; i++)
			{
				Line cur = new Line(poles[i]);
				Line l = map.get(cur);
				if(l == null)
					map.put(cur, cur);
				else
					if(l.compareHeight(cur) == 1)
						ans.add(poles[i]);
					else
						l.adjust(cur);

			}
			
				
		
			out.printf("Data set %d:\n", k++);
			if(ans.isEmpty())
				out.print("All the lights are visible.\n");
			else
			{
				out.print("Some lights are not visible:\n");
				Collections.sort(ans, new Sorter());
				for(int i = 0; i < ans.size() - 1; i++)
					out.printf("x = %d, y = %d;\n", ans.get(i).x, ans.get(i).y);
				out.printf("x = %d, y = %d.\n", ans.get(ans.size()-1).x, ans.get(ans.size()-1).y);
			}
		}
		out.flush();
	}
	
	static class Point implements Comparable<Point>
	{
		int x, y, z;
		double dist;
		
		Point(int a, int b, int c) 
		{ 
			x = a; y = b; z = c;
			dist = Math.sqrt((long)x *x + (long)y * y);
		}
		
		public int compareTo(Point p)
		{
			return dist > p.dist ? 1 : -1;
		}
	}
	
	static class Line implements Comparable<Line>
	{
		double a, b;
		int hPos = -1, hNeg = -1;
		
		Line(Point p)
		{
			if(p.x == 0)
			{
				a = 1.0; b = 0.0;
			}
			else
			{
				a = - ((double) p.y) / p.x;
				b = 1.0;
			}
			if(p.x >= 0)
				hPos = p.z;
			else
				hNeg = p.z;
		}
		
		public int compareTo(Line l)
		{
			if(Math.abs(a - l.a) > EPS)
				return a > l.a ? 1 : -1;
			
			if(Math.abs(b - l.b) > EPS)
				return b > l.b ? 1 : -1;

			return 0;
		}
		
		public int compareHeight(Line l)
		{
			if(l.hPos == -1)
				return hNeg >= l.hNeg ? 1 : -1;
			return hPos >= l.hPos ? 1 : -1;	
		}
		
		public void adjust(Line l)
		{
			if(l.hPos == -1)
				hNeg = l.hNeg;
			else
				hPos = l.hPos;
		}
	}
	
	static class Sorter implements Comparator<Point>
	{

		public int compare(Point p1, Point p2) 
		{
			if(p1.x != p2.x)
				return p1.x - p2.x;
			return p1.y - p2.y;
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
