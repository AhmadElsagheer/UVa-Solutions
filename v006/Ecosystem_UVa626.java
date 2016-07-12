package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Ecosystem_UVa626 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();		

		while(sc.ready())
		{
			int n = sc.nextInt(), adjMat[][] = new int[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					adjMat[i][j] = sc.nextInt();

			ArrayList<Triple> triples = new ArrayList<Triple>();
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
					for(int k = i + 1; k < n; ++k)
						if(j != k)
							if(adjMat[i][j] + adjMat[j][k] + adjMat[k][i] == 3)
								if(j < k)
									triples.add(new Triple(i + 1, j + 1, k + 1));
								else
									triples.add(new Triple(j + 1, k + 1, i + 1));

			Collections.sort(triples);
			for(Triple t: triples)
				sb.append(t.x + " " + t.y + " " + t.z + "\n");
			sb.append("total:"+triples.size()+"\n\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Triple implements Comparable<Triple>
	{
		int x, y, z;

		Triple(int a, int b, int c) { x = a; y = b; z = c; }

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