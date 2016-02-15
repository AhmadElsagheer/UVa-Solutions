package cp4_6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MarchOfThePenguins_UVa12125 {

	static final double EPS = 1e-9;
	static final int INF = (int)1e9;
	static int V, s, t, f[][], c[][], p[];
	static ArrayList<Integer>[] adjList;
	
	static int maxFlow()
	{
		int mf = 0;
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			p = new int[V];
			Arrays.fill(p, -1);
			q.add(s);
			p[s] = s;
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == t)
					break;
				for(int i = 0; i < adjList[u].size(); ++i)
				{
					int v = adjList[u].get(i);
					if(f[u][v] < c[u][v] && p[v] == -1)
					{
						p[v] = u;
						q.add(v);
					}
				}
			}
			
			if(p[t] == -1)
				break;
			mf += augment(t, INF);
		}
		return mf;
	}
	
	static int augment(int v, int flow)
	{
		if(v == s)
			return flow;
		flow = 	augment(p[v], Math.min(flow, c[p[v]][v] - f[p[v]][v]));
		f[p[v]][v] += flow; f[v][p[v]] -= flow;
		return flow;
	}
	
	static void addEdge(int u, int v, int w)
	{
		adjList[u].add(v);
		adjList[v].add(u);
		c[u][v] = w;
	}
	
	static void addEdge(int u, int w)
	{
		adjList[u].add(0);
		adjList[0].add(u);
		c[0][u] = w;
	}
	
	static void clear()
	{
		for(int i = 0; i < adjList[0].size(); ++i)
		{
			int u = adjList[0].get(i);
			c[0][u] = 0;
			adjList[u].remove(adjList[u].size() - 1);
		}
		adjList[0] = new ArrayList<Integer>();
		f = new int[V][V];
	}
	static int Vin(int x) { return 1 + (x<<1); }
	static int Vout(int x) { return 2 + (x<<1); }
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), total = 0;
			double d = sc.nextDouble();
			V = (N<<1) + 1;
			c = new int[V][V];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			Point[] floes = new Point[N];
			int[] pens = new int[N];
			for(int i = 0; i < N; ++i)
			{
				floes[i] = new Point(sc.nextInt(), sc.nextInt());
				total += pens[i] = sc.nextInt();
				addEdge(Vin(i), Vout(i), sc.nextInt());
			}
			
			for(int i = 0; i < N; ++i)
				for(int j = i + 1; j < N; ++j)
					if(floes[i].dist2(floes[j]) <= d * d + EPS)
					{
						addEdge(Vout(i), Vin(j), INF);
						addEdge(Vout(j), Vin(i), INF);
					}
			int count = 0;
			boolean first = true;
			for(int i = 0; i < N; ++i)
			{
				clear();
				t = Vin(i);
				for(int j = 0; j < N; ++j)
					if(i != j)
						addEdge(Vin(j), pens[j]);
				if(maxFlow() == total - pens[i])
				{
					count++;
					if(first)
					{
						first = false;
						out.print(i);
					}
					else
						out.format(" %d", i);
				}
			}
			if(count == 0)
				out.print(-1);
			out.println();
		}
		out.flush();
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		double dist2(Point p) { return (double)(p.x - x) * (p.x - x) + (double) (p.y - y) * (p.y - y); }
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
