package v110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BuildingDesigning_UVa11039 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			ArrayList<Integer> red = new ArrayList<Integer>(n);
			ArrayList<Integer> blue = new ArrayList<Integer>(n);
			while(n-->0)
			{
				int x = sc.nextInt();
				if(x < 0)
					red.add(-x);
				else
					blue.add(x);
			}
			Collections.sort(red);
			Collections.sort(blue);
			out.println(Math.max(getMax(red, blue), getMax(blue, red)));
		}
		out.flush();
		out.close();
	}
	
	static int getMax(ArrayList<Integer> x, ArrayList<Integer> y)
	{
		int i = 0, j = 0, lst = 0, ans = 0;
		while(true)
		{
			while(i < x.size() && x.get(i) <= lst)
				++i;
			if(i == x.size())
				break;
			++ans;
			lst = x.get(i++);
			
			while(j < y.size() && y.get(j) <= lst)
				++j;
			if(j == y.size())
				break;
			++ans;
			lst = y.get(j++);
		}
		return ans;
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