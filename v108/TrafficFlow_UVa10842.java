package v108;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrafficFlow_UVa10842 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			Edge[] edgeList = new Edge[M];
			for(int i = 0; i < M; ++i)
				edgeList[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
			UnionFind uf = new UnionFind(N);
			Arrays.sort(edgeList);
			int ans = 0;
			for(Edge e: edgeList)
				if(uf.union(e.u, e.v))
					ans = e.w;
			out.printf("Case #%d: %d\n", t, ans);
		}
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		
		Edge(int a, int b, int c) { u = a; v = b; w = c; }
		
		public int compareTo(Edge e) { return e.w - w; }
	}
	
	static class UnionFind
	{
		int[] p, rank;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		int find(int x) { return p[x] == x ? x : (p[x] = find(p[x])); }
		
		boolean union(int x, int y)
		{
			x = find(x);
			y = find(y);
			
			if(x == y)
				return false;
			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					++rank[y];
			}
			return true;
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