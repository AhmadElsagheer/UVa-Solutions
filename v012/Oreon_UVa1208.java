package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Oreon_UVa1208 {

	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int C = sc.nextInt();
			ArrayList<Edge> edgeList = new ArrayList<Edge>();
			for(int i = 0; i < C; ++i)
			{
				StringTokenizer st = new StringTokenizer(sc.nextLine(), ",| ");
				for(int j = 0; j < C; ++j)
				{
					int w = Integer.parseInt(st.nextToken());
					if(w != 0)
						edgeList.add(new Edge(i, j, w));
				}
			}
			Collections.sort(edgeList);
			UnionFind uf = new UnionFind(C);
			out.printf("Case %d:\n", t);
			for(Edge e: edgeList)
				if(uf.union(e.u, e.v))
					out.printf("%c-%c %d\n", (e.u + 'A'), (e.v + 'A'), e.w);
		}
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		
		Edge(int a, int b, int c) { u = a; v = b; w = c; }
		
		public int compareTo(Edge e) 
		{ 
			if(w != e.w)
				return w - e.w;
			if(u != e.u)
				return u - e.u;
			return v - e.v;
		}
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