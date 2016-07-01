package v011;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class IPTV_UVa1174 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			sc.next();
			int E = sc.nextInt(), N = 0;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			Edge[] edgeList = new Edge[E];
			for(int i = 0; i < E; ++i)
			{
				String s1 = sc.next(), s2 = sc.next();
				Integer u = map.get(s1), v = map.get(s2);
				if(u == null)
					map.put(s1, u = N++);
				if(v == null)
					map.put(s2, v = N++);
				edgeList[i] = new Edge(u, v, sc.nextInt());
			}
			Arrays.sort(edgeList);
			UnionFind uf = new UnionFind(N);
			int mst = 0;
			for(Edge e: edgeList)
				if(uf.union(e.u, e.v))
					mst += e.w;
			out.println(mst);
			if(tc != 0)
				out.println();
		}
		out.flush();
		out.close();
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