package v007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TripleTiesTheOrganizersNightmare_UVa703 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);


		while(sc.ready())
		{
			int n = sc.nextInt(), a[][] = new int[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					a[i][j] = sc.nextInt();
			ArrayList<Triple> sol = new ArrayList<Triple>(); 
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
					for(int k = j + 1; k < n; ++k)
					{
						int x = a[i][j] + a[j][k] + a[k][i];
						int y = a[j][i] + a[k][j] + a[i][k];
						if(x + y == 0 || x == 3)
							sol.add(new Triple(i + 1, j + 1, k + 1));
						else if(y == 3)
							sol.add(new Triple(i + 1, k + 1, j + 1));
					}
			Collections.sort(sol);
			out.println(sol.size());
			for(Triple t: sol)
				out.println(t.x + " " + t.y + " " + t.z);
		}
		out.flush();
		out.close();
	}

	static class Triple implements Comparable<Triple>
	{
		int x, y, z;

		Triple(int a, int b, int c)
		{
			if(b < c)
			{
				x = a; y = b; z = c;
			}
			else
			{
				x = b; y = c; z = a;
			}

		}
		
		public int compareTo(Triple t)
		{
			if(x != t.x)
				return x - t.x;
			if(y != t.y)
				return y - t.y;
			return z - t.z;
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