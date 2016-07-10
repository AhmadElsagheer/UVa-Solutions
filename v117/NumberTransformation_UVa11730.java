package v117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NumberTransformation_UVa11730 {
	
	static ArrayList<Integer>[] modifiedSieve(int N)
	{
		ArrayList<Integer>[] pf = new ArrayList[N];
		for(int i = 1; i < N; ++i)
			pf[i] = new ArrayList<Integer>();
		for(int i = 2; i < N; ++i)
			if(pf[i].isEmpty())
				for(int j = i<<1; j < N; j += i)
					pf[j].add(i);
		return pf;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Integer>[] pf = modifiedSieve(1001);
		int tc = 1;
		while(true)
		{
			int S = sc.nextInt(), T = sc.nextInt();
			if(S == 0 && T == 0)
				break;
			int[] dist = new int[1001];
			dist[S] = 1;
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(S);
			while(!q.isEmpty())
			{
				int u = q.remove(), d = dist[u];
				if(u == T)
					break;
				for(int p: pf[u])
					if(u + p <= T && dist[u + p] == 0)
					{
						dist[u + p] = d + 1;
						q.add(u + p);
					}
			}
			out.printf("Case %d: %d\n", tc++, dist[T] - 1);
		}
		out.flush();
		out.close();
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