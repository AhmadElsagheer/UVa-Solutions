package cp4_2;
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

public class PlaceTheGuards_UVa11080 {

	static ArrayList<Integer>[] adjList;
	static int V;
	static boolean[] visited;
	
	static int bfs()
	{
		int ans = 0;
		visited = new boolean[V];
		for(int i = 0; i < V; ++i)
			if(!visited[i])
			{
				int c = bfs(i);
				if(c == -1)
					return -1;
				ans += c;
			}
		return ans;
	}
	
	static int bfs(int s)
	{
		int[] color = new int[V];
		Arrays.fill(color, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		color[s] = 0;
		visited[s] = true;
		q.add(s);
		while(!q.isEmpty())
		{
			int u = q.remove();
			for(int v: adjList[u])
				if(!visited[v])
				{
					color[v] = color[u] ^ 1;
					visited[v] = true;
					q.add(v);
				}
				else
					if(color[u] == color[v])
						return -1;
		}
		int c1 = 0, c2 = 0;
		for(int i = 0; i < V; ++i)
			if(color[i] != -1)
				if(color[i] == 0)
					++c1;
				else
					++c2;
		return Math.max(1, Math.min(c1, c2));
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			V = sc.nextInt();
			int E = sc.nextInt();
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
			{
				int u = sc.nextInt(), v = sc.nextInt();
				adjList[u].add(v);
				adjList[v].add(u);
			}
			out.println(bfs());
		}
		out.flush();
		out.close();
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

		public double nextDouble(String x) 
		{

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


	}
}
