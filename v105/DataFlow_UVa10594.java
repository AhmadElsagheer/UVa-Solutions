package v105;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class DataFlow_UVa10594 {

	static final long INF = (long)1e16;
	static ArrayList<Edge>[] adjList;
	static int N, p[];
	static long totalTime;
	static Edge[] pEdge;
	
	static long minTime(int D)
	{
		totalTime = 0;
		long flow = 0;
		while(flow < D)
		{
			p = new int[N];
			pEdge = new Edge[N];
			long[] dist = new long[N];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			
			for(int k = 0; k < N - 1; ++k)
				for(int u = 0; u < N; ++u)
					for(Edge e: adjList[u])
						if(e.cap > 0 && dist[e.node] > dist[u] + e.time)
						{
							dist[e.node] = dist[u] + e.time;
							p[e.node] = u;
							pEdge[e.node] = e;
						}
			if(pEdge[N - 1] == null)
				return -1;
			flow += aug(N - 1, D - flow);
		}
		return totalTime;
	}
	
	static long aug(int v, long flow)
	{
		if(v == 0)
			return flow;
		int u = p[v];
		Edge e = pEdge[v];
		flow = aug(u, Math.min(flow, e.cap));
		e.cap -= flow;
		e.rev.cap += flow;
		totalTime += flow * e.time;
		return flow;
	}
	
	static void addEdge(int u, int v, int c, int w)
	{
		Edge e1 = new Edge(v, c, w), e2 = new Edge(u, 0, -w);
		e1.rev = e2;
		e2.rev = e1;
		adjList[u].add(e1);
		adjList[v].add(e2);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			N = sc.nextInt();
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Edge>();
			int M = sc.nextInt();
			int[] U = new int[M], V = new int[M], T = new int[M];
			for(int i = 0; i < M; ++i)
			{
				U[i] = sc.nextInt() - 1;
				V[i] = sc.nextInt() - 1;
				T[i] = sc.nextInt();
			}
			
			int D = sc.nextInt(), K = sc.nextInt();
			for(int i = 0; i < M; ++i)
			{
				addEdge(U[i], V[i], K, T[i]);
				addEdge(V[i], U[i], K, T[i]);
			}
			long ans = minTime(D);
			if(ans == -1)
				out.println("Impossible.");
			else
				out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static class Edge
	{
		int node, cap, time;
		Edge rev;
		
		Edge(int a, int b, int c) { node = a; cap = b; time = c; }
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