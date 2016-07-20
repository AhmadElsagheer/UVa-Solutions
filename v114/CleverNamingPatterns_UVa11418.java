package v114;


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

public class CleverNamingPatterns_UVa11418 {
	
	static int[][] res;
	static ArrayList<Integer>[] adjList;	
	static int N, p[];
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt(), map[] = new int[n];
			N = 2 + (n<<1);
			String[][] names = new String[n][];
			for(int i = 0; i < n; ++i)
			{
				map[i] = i == 0 ? N : (map[i-1] + names[i-1].length);
				int k = sc.nextInt();
				N += k;
				names[i] = new String[k];
				for(int j = 0; j < k; ++j)
					names[i][j] = sc.next();
			}
			String[] unmap = new String[N];
			res = new int[N][N];
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < n; ++i)
			{
				addEdge(0, n + 2 + i, 1);
				addEdge(i + 2, 1, 1);
				for(int j = 0; j < names[i].length; ++j)
				{
					if(Character.toUpperCase(names[i][j].charAt(0)) - 'A' >= n)
						continue;
					unmap[map[i] + j] = names[i][j];
					addEdge(n + 2 + i, map[i] + j, 1);
					
					addEdge(map[i] + j, Character.toUpperCase(names[i][j].charAt(0)) - 'A' + 2, 1);
				}
			}
			maxFlow();
			out.format("Case #%d:\n", t);
			for(int i = 0; i < n; ++i)
			{
				int u = 2 + i;
				for(int j = 0; j < adjList[u].size(); ++j)
				{
					int v = adjList[u].get(j);
					if(res[u][v] == 1)
					{
						String x = unmap[v];
						out.println(Character.toUpperCase(x.charAt(0)) + x.substring(1).toLowerCase());
					}
				}
			}
			
		}
		out.flush();
		
	}
	
	static void maxFlow()
	{
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			p = new int[N];
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
				return;
			augment(1);
		}
	}
	
	static void augment(int v)
	{
		if(v == 0)
			return;
		res[p[v]][v]--;
		res[v][p[v]]++;
		augment(p[v]);
	}
	static void addEdge(int x, int y, int w)
	{
		adjList[x].add(y);
		adjList[y].add(x);
		res[x][y] = w;
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
