package v100;


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

public class TheProblemWithTheProblemSetter_UVa10092 {

	static ArrayList<Edge>[] adjList;
	static Edge[] path;
	static int V, p[];
	static int maxFlow()
	{
		int mf = 0;
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			p = new int[V];
			Arrays.fill(p, -1);
			path = new Edge[V];
			q.add(0);
			p[0] = 0;
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == 1)
					break;
				for(int i = 0; i < adjList[u].size(); ++i)
				{
					Edge e = adjList[u].get(i);
					
					if(e.cost > 0 && p[e.to] == -1)
					{
						p[e.to] = u;
						path[e.to] = e;
						q.add(e.to);
					}
				}
			}
			if(p[1] == -1)
				break;
			++mf;
			augment(1);
		}
		return mf;
	}
	
	static void augment(int v)
	{
		if(v == 0)
			return;
		augment(p[v]);
		--path[v].cost;
		++path[v].rev.cost;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int nk = sc.nextInt(), np = sc.nextInt(), exp = 0;
			if(nk == 0)
				break;
			V = nk + np + 2;
			adjList = new ArrayList[V];
			adjList[0] = new ArrayList<Edge>(np);
			for(int i = 1; i < V; ++i)
				adjList[i] = new ArrayList<Edge>();
						
			for(int i = 0; i < nk; ++i)
			{
				int w = sc.nextInt();
				exp += w;
				Edge e1 = new Edge(i + np + 2, 0), e2 = new Edge(1, w);
				adjList[1].add(e1);
				adjList[i + np + 2].add(e2);
				e1.rev = e2;
				e2.rev = e1;
			}
			
			for(int i = 0; i < np; ++i)
			{
				Edge e1 = new Edge(i + 2, 1), e2 = new Edge(0, 0);
				adjList[0].add(e1);
				adjList[i + 2].add(e2);
				e1.rev = e2;
				e2.rev = e1;
				int c = sc.nextInt();
				while(c-->0)
				{
					int to = 1 + np + sc.nextInt();
					e1 = new Edge(to, 1);
					e2 = new Edge(i + 2, 0);
					adjList[i + 2].add(e1);
					adjList[to].add(e2);
					e1.rev = e2;
					e2.rev = e1;
				}
			}
			
			int mf = maxFlow();
			if(mf != exp)
				out.println(0);
			else
			{
				out.println(1);
				for(int i = 0; i < nk; ++i)
				{
					int u = i + np + 2;
					for(int j = 0, k = 0; j < adjList[u].size(); ++j)
					{
						Edge e = adjList[u].get(j);
						if(e.to != 1 && e.cost == 1)
							if(k++ != 0)
								out.format(" %d", e.to - 1);
							else
								out.format("%d", e.to - 1);
					}
					out.println();
				}
			}
		}
		out.flush();

	}
	
	static class Edge
	{
		int to, cost;
		Edge rev;
		
		Edge(int x, int y) { to = x; cost = y; }
		
		public String toString() { return to + "  " + cost; }
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
