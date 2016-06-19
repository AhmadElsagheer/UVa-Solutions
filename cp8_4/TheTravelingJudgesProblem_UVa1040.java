package cp8_4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class TheTravelingJudgesProblem_UVa1040 {

	static final int INF = (int)1e9;
	static int V, D;
	
	static Deque<Integer> deque;
	static ArrayList<Edge>[] adjList;

	
	static boolean dfs(int u, int p)
	{
		deque.addLast(u);
		if(u == D)
			return true;
		for(Edge e: adjList[u])
			if(e.v != p && dfs(e.v, u))
				return true;
		deque.removeLast();
		return false;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			if(tc != 1)
				out.println();
			V = sc.nextInt();
			if(V == -1)
				break;

			D = sc.nextInt() - 1;
			int E = sc.nextInt();
			Edge[] edgeList = new Edge[E];
			for(int i = 0; i < E; ++i)
				edgeList[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
		
			Arrays.sort(edgeList);
			int J = sc.nextInt(), cities = 0, city[] = new int[J];
			for(int i = 0; i < J; ++i)
				cities |= 1 << (city[i] = sc.nextInt() - 1);
			
			int min = INF, best = -1;

			for(int i = 1; i < 1<<V; ++i)
				if((cities & i) == cities)
				{
					int cur = 0;
					UnionFind uf = new UnionFind(V);
					for(Edge e: edgeList)
						if((1<<e.u & i) != 0 && (1<<e.v & i) != 0 && uf.union(e.u, e.v))
							cur += e.cost;
					boolean valid = true;
					for(int j = 0; j < V; ++j)
						if((cities & 1<<j) != 0 && uf.findSet(j) != uf.findSet(D))
							valid = false;
					if(valid && cur < min || cur == min && Integer.bitCount(i) < Integer.bitCount(best)
							|| cur == min && Integer.bitCount(i) == Integer.bitCount(best) && smaller(i, best))
					{
						min = cur;
						best = i;
					}
					
				}
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Edge>();
			UnionFind uf = new UnionFind(V);
			for(Edge e: edgeList)
				if((1<<e.u & best) != 0 && (1<<e.v & best) != 0 && uf.union(e.u, e.v))
				{
					adjList[e.u].add(new Edge(e.u, e.v, e.cost));
					adjList[e.v].add(new Edge(e.v, e.u, e.cost));
				}
			out.printf("Case %d: distance = %d\n", tc++, min);
			StringBuilder sb = new StringBuilder();
			deque = new LinkedList<Integer>();
			for(int i = 0; i < J; ++i)
			{
				dfs(city[i], -1);
				boolean first = true;
				while(!deque.isEmpty())
				{
					int node = deque.pop() + 1;
					if(first)
					{
						sb.append("   " + node);
						first = false;
					}
					else
						sb.append("-" + node);
				}
				sb.append("\n");
			}
			out.print(sb);
			
		}
		out.flush();
		out.close();
	}
	
	static boolean smaller(int x, int y)
	{
		int i = 0, j = 0;
		while(true)
		{
			while((x & 1<<i) == 0)
				++i;
			while((y & 1<<j) == 0)
				++j;
			if(i != j)
				return i < j ? true : false;
			++i;
			++j;			
		}
	}
	
	
	static class UnionFind
	{
		int N, p[], rank[];
		
		UnionFind(int n) 
		{
			N = n;
			p = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
			rank = new int[N];
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
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, cost;
		
		Edge(int x, int y, int z) { u = x; v = y; cost = z; }
		
		public int compareTo(Edge e)
		{
			return cost - e.cost;
		}
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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