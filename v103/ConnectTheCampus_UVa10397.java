package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ConnectTheCampus_UVa10397 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			if(sc.nextEmpty())			//Malformed input
				continue;
			int N = sc.nextInt();
			Point[] buildings = new Point[N];
			UnionFind uf = new UnionFind(N);
			for(int i = 0; i < N; ++i)
				buildings[i] = new Point(sc.nextInt(), sc.nextInt());

			int M = sc.nextInt();
			while(M-->0)
				uf.union(sc.nextInt() - 1, sc.nextInt() - 1);


			Edge[] edgeList = new Edge[N * (N - 1) / 2];
			for(int i = 0, k = 0; i < N; ++i)
				for(int j = i + 1; j < N; ++j)
					edgeList[k++] = new Edge(i, j, buildings[i].dist2(buildings[j]));
			double mst = 0.0;
			Arrays.sort(edgeList);
			for(Edge e: edgeList)
				if(uf.union(e.u, e.v))
					mst += Math.sqrt(e.w);
			out.printf("%.2f\n", mst);

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

	static class Edge implements Comparable<Edge>
	{
		int u, v, w;

		Edge(int a, int b, int c) { u = a; v = b; w = c; }

		public int compareTo(Edge e) { return w - e.w; }
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
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}