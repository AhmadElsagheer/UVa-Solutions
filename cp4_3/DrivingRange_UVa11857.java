package cp4_3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class DrivingRange_UVa11857 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			if(N + M == 0)
				break;
			Edge[] edgeList = new Edge[M];
			for(int i = 0; i < M; ++i)
				edgeList[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
			Arrays.sort(edgeList);
			UnionFind uf = new UnionFind(N);
			int ans = 0;
			for(Edge e: edgeList)
				if(uf.findSet(e.u) != uf.findSet(e.v))
				{
					ans = Math.max(ans, e.w);
					uf.union(e.u, e.v);
				}
			if(uf.sets > 1)
				out.println("IMPOSSIBLE");
			else
				out.println(ans);
			
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
			return w - e.w;
		}
	}
	
	static class UnionFind
	{
		int[] p, rank;
		int sets;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			sets = N;
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		int findSet(int x)
		{
			return p[x] == x ? x : (p[x] = findSet(p[x]));
		}
		
		void union(int x, int y)
		{
			x = findSet(x);
			y = findSet(y);
			
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
			--sets;
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader r) {  br = new BufferedReader(r); }

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