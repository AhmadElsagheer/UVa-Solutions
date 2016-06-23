package v112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TheHireACoderBusinessModel_UVa11267 {

	static ArrayList<Integer>[] adjList;
	static int[] color;
	
	static boolean bipartite(int u)
	{
		int c = color[u] * -1;
		for(int v: adjList[u])
			if(color[v] == 0)
			{
				color[v] = c;
				if(!bipartite(v))
					return false;
			}
			else if(color[v] != c)
				return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int V = sc.nextInt();
			if(V == 0)
				break;
			int E = sc.nextInt();
			
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			Edge[] edgeList = new Edge[E];
			for(int i = 0; i < E; ++i)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
				adjList[u].add(v);
				adjList[v].add(u);
				edgeList[i] = new Edge(u, v, w);
			}
			color = new int[V];
			color[0] = 1;
			if(!bipartite(0))
				out.println("Invalid data, Idiot!");
			else
			{

				long mst = 0;
				UnionFind uf = new UnionFind(V);
				Arrays.sort(edgeList);
				for(Edge e: edgeList)
					if(e.w <= 0 | uf.union(e.u, e.v))
						mst += e.w;
				out.println(mst);
			}
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
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}