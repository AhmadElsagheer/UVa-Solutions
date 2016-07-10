package v103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ArcticNetwork_UVa10369 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int S = sc.nextInt(), P = sc.nextInt();
			Point[] outposts = new Point[P];
			for(int i = 0; i < P; ++i)
				outposts[i] = new Point(sc.nextInt(), sc.nextInt());
			int ans = -1, lo = 0, hi = (int)1e9;
			while(lo <= hi)
			{
				int d2 = lo + hi >> 1;
				UnionFind uf = new UnionFind(P);
				for(int i = 0; i < P; ++i)
				{
					Point p = outposts[i];
					for(int j = i + 1; j < P; ++j)
						if(p.dist2(outposts[j]) <= d2)
							uf.union(i, j);
				}
				
				if(uf.sets <= S)
				{
					ans = d2;
					hi = d2 - 1;
				}
				else
					lo = d2 + 1;
			}
			out.printf("%.2f\n", Math.sqrt(ans));
		}
		out.flush();
		out.close();
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		int dist2(Point p) { return sq(x - p.x) + sq(y - p.y); }
		
		int sq(int x) { return x * x; }
	}
	
	static class UnionFind
	{
		int[] p, rank;
		int sets;
		
		UnionFind(int N)
		{
			p = new int[sets = N];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		int find(int x) { return x == p[x] ? x : (p[x] = find(p[x])); }
		
		void union(int x, int y)
		{
			x = find(x);
			y = find(y);
			
			if(x == y)
				return;
			
			--sets;
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