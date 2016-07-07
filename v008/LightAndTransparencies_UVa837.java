package v008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class LightAndTransparencies_UVa837 {

	/*
	 * Another Solution
	 * ----------------
	 * 1. Every interval will be added twice in an array
	 * 2. Every element in the array has three attributes
	 * 		a. the x - coordinate
	 * 		b. the coefficient
	 * 		c. whether it is an end or not
	 * 3. Loop on the array with start = a[0].x and c = 1.0
	 * 4. If a[i].x != start => an interval [start, a[i].x] -> c
	 * 5. If it is an end c /= a[i].c, otherwise c *= a[i].c
	 */
	static final double EPS = 1e-9;
	static Format f = new DecimalFormat("0.000");
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int segments = sc.nextInt();
			if(segments == 0)
			{
				out.printf("-inf +inf %.3f\n", 1.0);
				if(tc != 0)
					out.println();
				continue;
			}
			Stack<Interval> stack = new Stack<Interval>();
			Queue<Interval> intervals = new LinkedList<Interval>();
			double leftMost = Double.MAX_VALUE, rightMost = Double.MIN_VALUE;
			while(segments-->0)
			{
				double x1 = sc.nextDouble();
				sc.next();
				double x2  = sc.nextDouble();
				sc.next();
				double c = sc.nextDouble();
				double left = Math.min(x1, x2), right = Math.max(x1, x2); 
				stack.push(new Interval(left, right, c));
				leftMost = Math.min(leftMost, left);
				rightMost = Math.max(rightMost, right);
			}
			Collections.sort(stack);
			Stack<Interval> tmp = new Stack<Interval>();

			while(!stack.isEmpty())
			{
				Interval cur = stack.pop();
				double start = cur.left, end = cur.right, c = cur.coeff;
				tmp.push(cur);
				while(!stack.isEmpty() && Math.abs(stack.peek().left - start) < EPS)
				{
					cur = stack.pop();
					end = Math.min(end, cur.right);
					c *= cur.coeff;
					tmp.push(cur);
				}
				if(!stack.isEmpty())
				{
					end = Math.min(end, stack.peek().left);
					stack.push(new Interval(end, stack.peek().left, 1.0));
				}
				if(Math.abs(end - start) > EPS)
					intervals.add(new Interval(start, end, c));
				while(!tmp.isEmpty())
				{
					cur = tmp.pop();
					cur.left = end;
					if(cur.left > cur.right || Math.abs(cur.left - cur.right) < EPS)
						continue;
					stack.push(cur);
				}
			}
			
			out.println(intervals.size() + 2);
			out.printf("-inf %.3f %.3f\n", round(leftMost), 1.0);
			while(!intervals.isEmpty())
			{
				Interval cur = intervals.remove();
				out.printf("%.3f %.3f %.3f\n", round(cur.left), round(cur.right), round(cur.coeff));
			}
			out.printf("%.3f +inf %.3f\n", round(rightMost), 1.0);
			
			if(tc != 0)
				out.println();
		}
		out.flush();


	}
	
	static double round(double d)
	{
		return Math.round(d * 1000) / 1000.0;
	}
	

	static class Interval implements Comparable<Interval>
	{
		double left, right, coeff;
		
		Interval(double l, double r, double c)
		{
			left = l; right = r; coeff = c;
		}

		
		public int compareTo(Interval o) {
			
			if(Math.abs(left - o.left) > EPS)
				return left > o.left ? -1 : 1;
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
