package regionals.latinAmerica2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JoinTwoKingdoms {
	
	static ArrayList<Integer> [] graph1, graph2;
	static int[] dp_up, dp_down[];
	static int V1, V2, maxH;
	static boolean[] vis;
	
	static void dfs1(int u, ArrayList<Integer>[] graph)  
	{ 

		vis[u] = true;
		for (int i = 0; i < graph[u].size(); i++) {
			int v = graph[u].get(i);
			if(!vis[v]) { 
				dfs1(v, graph);
				if(dp_down[u][1] < dp_down[v][0]+1)
					dp_down[u][1] = dp_down[v][0] +1;
				
				if(dp_down[u][1] > dp_down[u][0]) {
					int temp = dp_down[u][1];
					dp_down[u][1] = dp_down[u][0];
					dp_down[u][0] = temp;
				}
			}
		}
	}
	
	static void dfs2(int u , int h, ArrayList<Integer>[] graph) 
	{ 
		dp_up[u] = h;
		vis[u] = true;
		for (int i = 0; i < graph[u].size(); i++) {
			int v = graph[u].get(i);
			if(!vis[v]) { 
				int h_nxt = 0;
				if(dp_down[u][0] == dp_down[v][0] + 1)
					h_nxt = dp_down[u][1] + 1; 
				else
					h_nxt = dp_down[u][0] +1;
				
				h_nxt = Math.max(h_nxt, dp_up[u] +1);
				dfs2(v,h_nxt, graph);	
			}
		}	
	}
	
	static int bs(int x, int[] k2) 
	{
		int lo = 0, hi = V2 - 1, ans = V2;
		
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(k2[mid] + 1 + x >= maxH)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ans;	
	}
	
	static int[] go(ArrayList<Integer>[] graph, int V)
	{
		vis = new boolean[V];
		dp_down = new int[V][2];
		dp_up = new int[V];
		int k[] = new int[V];
		
		dfs1(0, graph);
		vis = new boolean[V];
		dfs2(0, 0, graph);

		for (int i = 0; i < V; i++) 
			k[i] =  Math.max(Math.max(dp_down[i][0], dp_down[i][1]), dp_up[i]);
		Arrays.sort(k);
		return k;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready()) 
		{ 
			V1 = sc.nextInt(); V2 = sc.nextInt();
			
			graph1 = new ArrayList[V1+1];
			for (int i = 0; i < graph1.length; i++)
				graph1[i] = new ArrayList<Integer>();
			
			graph2 = new ArrayList[V2+1];
			for (int i = 0; i < graph2.length; i++) 
				graph2[i] = new ArrayList<Integer>();
			
			for (int i = 0; i < V1 - 1; i++) 
			{
				int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
				graph1[a].add(b);
				graph1[b].add(a);
			}
			
			for (int i = 0; i < V2 - 1; i++) 
			{
				int a = sc.nextInt() - 1, b = sc.nextInt() - 1;
				graph2[a].add(b);
				graph2[b].add(a);
			}
			
			int[] k1 = go(graph1, V1), k2 = go(graph2, V2);
			
			maxH = Math.max(k1[V1-1], k2[V2-1]);
			
			int [] prefix = new int[V2];
			for(int i=0; i < V2; i++)
			{
				prefix[i] = k2[i];
				if(i>0)	prefix[i] += prefix[i-1];
			}
			
			long calc = 0;
			for(int i = 0; i < V1; i++)
			{
				int k = bs(k1[i],k2);
				calc += (long)k * maxH + (prefix[V2-1] - (k>0?prefix[k-1]:0)) +(long) (k1[i]+1) * (V2 - k);
				
			}
			out.format("%.3f\n",(calc*1.0/(V1*V2)));			
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