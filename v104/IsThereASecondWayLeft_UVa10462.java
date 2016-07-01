package v104;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IsThereASecondWayLeft_UVa10462 {

	static int mst(Edge[] edgeList, int V, boolean first)
	{
		UnionFind uf = new UnionFind(V);
		int mst = 0;
		for(Edge e: edgeList)
			if(e.w != -1 && uf.union(e.u, e.v))
			{
				mst += e.w;
				e.used |= first;
			}
		if(uf.numSets != 1)
			return -1;
		if(!first && V == 1)
			return -1;
		return mst;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int V = sc.nextInt(), E = sc.nextInt();
			Edge[] edgeList = new Edge[E];
			for(int i = 0; i < E; ++i)
				edgeList[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
			Arrays.sort(edgeList);
			int mst = mst(edgeList, V, true);
			if(mst == -1)
				out.printf("Case #%d : No way\n", t);
			else
			{
				int mst2 = Integer.MAX_VALUE;
				for(Edge e: edgeList)
					if(e.used)
					{
						int x = e.w;
						e.w = -1;
						int curMst = mst(edgeList, V, false);
						if(curMst != -1 && curMst < mst2)
							mst2 = curMst;
						e.w = x;
					}
				if(mst2 == Integer.MAX_VALUE)
					out.printf("Case #%d : No second way\n", t);
				else
					out.printf("Case #%d : %d\n", t, mst2);
			}
		}
		out.flush();
		out.close();
	}

	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		boolean used;
		
		Edge(int a, int b, int c) { u = a; v = b; w = c; }
		
		public int compareTo(Edge e) { return w - e.w; } 
	}
	static class UnionFind
	{
		int[] p, rank;
		int numSets;

		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
			numSets = N;
		}

		int find(int x) { return x == p[x] ? x : (p[x] = find(p[x])); }

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
					++rank[x];
			}
			--numSets;
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