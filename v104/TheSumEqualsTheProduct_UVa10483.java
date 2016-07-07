package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TheSumEqualsTheProduct_UVa10483 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Triple> triples = new ArrayList<Triple>();
		
		for(double a = 0.01; a < 256; a += 0.01)
			for(double b = a; a * b < 256; b += 0.01)
			{
				if(Math.abs(a * b - 1) < 1e-9)
					continue;
					
				double c = Math.round((a + b) / (a * b - 1) * 100) / 100.0;

				if(c >= b && a * b * c < 256 && Math.abs(a + b + c - a * b * c) < 1e-9)
					triples.add(new Triple(a, b, c));
			}
		
		Collections.sort(triples);	
		while(sc.ready())
		{
			double l = sc.nextDouble(), r = sc.nextDouble();
			for(Triple t: triples)
				if(t.s >= l && t.s <= r)
					out.printf("%.2f = %.2f + %.2f + %.2f = %.2f * %.2f * %.2f\n", t.s, t.x, t.y, t.z, t.x, t.y, t.z);
				
		}
		out.flush();
		out.close();
	}
	
	static class Triple implements Comparable<Triple>
	{
		double x, y, z, s;
		
		Triple(double a, double b, double c) { x = a; y = b; z = c; s = x + y + z; }
		
		
		public int compareTo(Triple t)
		{
			if(Math.abs(s - t.s) < 1e-9)
				return x > t.x ? 1 : -1;
			return s > t.s ? 1 : -1;
		}
		public String toString()
		{
			return x + " " + y  + " " + z;
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}