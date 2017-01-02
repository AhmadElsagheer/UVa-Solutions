package v110;


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
import java.util.TreeMap;

public class MyTshirtsSuitsMe_UVa11045 {
	
	static final int INF = 1<<30;
	static int V, res[][], p[];
	static ArrayList<Integer>[] adjList;
	
	static int maxFlow()
	{
		int mf = 0;
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			p = new int[V];
			Arrays.fill(p, -1);
			p[0] = 0;
			q.add(0);
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == 1)
					break;
				for(int i = 0; i < adjList[u].size(); ++i)
				{
					int v = adjList[u].get(i);
					if(p[v] == -1 && res[u][v] > 0)
					{
						p[v] = u;
						q.add(v);
					}
				}
			}
			if(p[1] == -1)
				break;
			augment(1);
			++mf;
		}
		return mf;
	}
	
	static void augment(int v)
	{
		if(v == 0)
			return;
		augment(p[v]);
		--res[p[v]][v];
		++res[v][p[v]];
	}
	public static void main(String[] args) throws IOException {
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		map.put("XS", 2);map.put("S", 3);map.put("M", 4);
		map.put("L", 5);map.put("XL", 6);map.put("XXL", 7);
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			V = N + M + 2;
			res = new int[V][V];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 2; i <= 7; ++i)
			{
				adjList[0].add(i);
				adjList[i].add(0);
				res[0][i] = N / 6;
			}
			
			for(int i = 1; i <= M; ++i)
			{
				adjList[i + 7].add(1);
				adjList[1].add(i + 7);
				res[i + 7][1] = 1;
				int x = map.get(sc.next()), y = map.get(sc.next());
				adjList[i + 7].add(x);
				adjList[i + 7].add(y);
				adjList[x].add(i + 7);
				adjList[y].add(i + 7);
				res[x][i + 7] = res[y][i + 7] = 1;
			}
			out.println(maxFlow() == M ? "YES" : "NO");
			
		}
		out.flush();

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
