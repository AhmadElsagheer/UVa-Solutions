package v103;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RoadsInTheNorth_UVa10308 {

	static ArrayList<Edge>[] adjList;
	static int maxDistance, maxNode;
	
	
	static void dfs(int u, int p, int dist)
	{
		if(dist > maxDistance)
		{
			maxDistance = dist;
			maxNode = u;
		}
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			Edge nxt = adjList[u].get(i);
			if(nxt.to == p)
				continue;
			dfs(nxt.to, u, dist + nxt.cost);
		}
	}
			
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		
		while(sc.ready())
		{
			
			adjList = new ArrayList[10000];
			while(sc.ready() && !sc.nxtEmpty())
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, c = sc.nextInt();
				addEdge(u, v, c);
			}
			if(adjList[0] == null)
			{
				out.println(0);
				continue;
			}
			maxDistance = maxNode = -1;
			dfs(0, -1, 0);
			int node = maxNode;
			maxDistance = maxNode = -1;
			dfs(node, -1, 0);
			
			out.println(maxDistance);
		}
		out.flush();
	}


	static void addEdge(int u, int v, int w)
	{
		if(adjList[u] == null)
			adjList[u] = new ArrayList<Edge>();
		if(adjList[v] == null)
			adjList[v] = new ArrayList<Edge>();
		adjList[u].add(new Edge(v, w));
		adjList[v].add(new Edge(u, w));
	}
	
	static class Edge
	{
		int to, cost;
		
		Edge(int x, int y) { to = x; cost = y; }
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
