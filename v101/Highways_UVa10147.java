package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Highways_UVa10147 {
			
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt();
			Town[] towns = new Town[N];
			for(int i = 0; i < N; ++i)
				towns[i] = new Town(sc.nextInt(), sc.nextInt());
			PriorityQueue<Edge> edgeList = new PriorityQueue<Edge>();
			for(int i = 0, k = 0; i < N; ++i)
			{
				Town t = towns[i];
				for(int j = i + 1; j < N; ++j, ++k)
					edgeList.add(new Edge(i, j, t.dist2(towns[j])));
			}
			int M = sc.nextInt();
			UnionFind uf = new UnionFind(N);
			while(M-->0)
				uf.union(sc.nextInt() - 1, sc.nextInt() - 1);
			if(uf.sets == 1)
				out.println("No new highways need");
			else
			{
				Edge[] sol = new Edge[uf.sets - 1];
				int k = 0;
				while(uf.sets != 1)
				{
					Edge e = edgeList.remove();
					if(uf.union(e.u, e.v))
						sol[k++] = e;
				}
				for(Edge e: sol)
					out.printf("%d %d\n", e.u + 1, e.v + 1);
			}
			if(tc != 0)
				out.println();
		}
		out.flush();
		out.close();
	}
	
	static class Town
	{
		int x, y;
		
		Town(int a, int b) { x = a; y = b; }
		
		int dist2(Town t) { return sq(x - t.x) + sq(y - t.y); }
		
		int sq(int x) { return x * x; }
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		
		Edge(int x, int y, int z) { u = x; v = y; w = z; }
		
		public int compareTo(Edge e) { return w - e.w; }
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
		
		int findSet(int x) { return p[x] == x ? x : (p[x] = findSet(p[x])); }
		
		boolean union(int x, int y)
		{
			x = findSet(x);
			y = findSet(y);
			
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
			--sets;
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}