package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Fatman_UVa295 {

	static final double EPS = 1e-6;

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();

		for(int t = 1; t <= tc; ++t)
		{
			int L = sc.nextInt(), W = sc.nextInt(), N = sc.nextInt();
			Point[] obs = new Point[N];
			for(int i = 0; i < N; ++i)
				obs[i] = new Point(sc.nextInt(), sc.nextInt());

			double lo = 0, hi = W;
			while(Math.abs(lo - hi) > EPS)
			{
				double d = (lo + hi) / 2.0;
				UnionFind uf = new UnionFind(N + 2);
				for(int i = 0; i < N; ++i)
				{
					Point p = obs[i];
					if(p.y < d + EPS)
						uf.union(N, i);
					if(W - p.y < d + EPS)
						uf.union(N + 1, i);

					for(int j = i + 1; j < N; ++j)
						if(p.dist2(obs[j]) < d * d + EPS)
							uf.union(i, j);
				}

				if(uf.same(N, N + 1))
					hi = d;
				else
					lo = d;
			}
			out.printf("Maximum size in test case %d is %.4f.\n", t, lo);

		}

		out.flush();
		out.close();
	}

	static class UnionFind
	{
		int[] p, rank;

		UnionFind(int N) 
		{
			rank = new int[N];
			p = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}

		int find(int x) { return x == p[x] ? x : (p[x] = find(p[x])); }

		boolean same(int x, int y) { return find(x) == find(y); }

		void union(int x, int y)
		{
			x = find(x);
			y = find(y);

			if(x == y)
				return;

			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					++rank[y];
			}
		}
	}

	static class Point
	{
		int x, y;

		Point(int a, int b) { x = a; y = b; }

		int dist2(Point p) { return sq(x - p.x) + sq(y - p.y); }

		int sq(int x) { return x * x; }
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