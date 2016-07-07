package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DyingTree_UVa11474 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt(), d = sc.nextInt();
			Point[][] trees = new Point[n][];
			Point[] doctors = new Point[m];
			for(int i = 0; i < m; ++i)
				doctors[i] = new Point(sc.nextInt(), sc.nextInt());
			for(int i = 0; i < n; ++i)
			{
				int b = sc.nextInt();
				trees[i] = new Point[b];
				for(int j = 0; j < b; ++j)
					trees[i][j] = new Point(sc.nextInt(), sc.nextInt());
			}
			
			UnionFind uf = new UnionFind(n);
			for(int i = 0; i < n; ++i)
				for(Point p: trees[i])
				{
					boolean reach = false;
					for(Point doc: doctors)
						reach |= doc.dist2(p) <= d * d;
					uf.reach[i] |= reach;
				}
			
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
					if(!uf.sameSet(i, j))
						for(Point p: trees[i])
							for(Point q: trees[j])
								if(p.dist2(q) <= k * k)
									uf.union(i, j);
			
			out.println(uf.canReach() ? "Tree can be saved :)" : "Tree can't be saved :(");
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
		boolean[] reach;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			reach = new boolean[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		boolean canReach() { return reach[find(0)]; }
		
		boolean sameSet(int x, int y) { return find(x) == find(y); }
		
		int find(int x) { return x == p[x] ? x : (p[x] = find(p[x])); }
		
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
			
			reach[x] |= reach[y];
			reach[y] |= reach[x];
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