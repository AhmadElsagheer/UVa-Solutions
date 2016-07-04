package v012;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BribingFIPA_UVa1222 {
	
	static final int INF = (int)1e8;
	static int N, idx;
	static ArrayList<Integer>[] adjList;
	static int[] cost, size, array, memo[];
	
	static int toposort(int u)
	{
		int count = 0;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			count += toposort(v);
		}
		array[idx--] = u;
		return size[u] = count + 1;
	}
	
	
	static int dp(int c, int remM)
	{
		if(remM <=0)
			return 0;
		if(c == N)
			return INF;
		if(memo[c][remM] != -1)
			return memo[c][remM];
		return memo[c][remM] = Math.min(cost[array[c]] + dp(c + size[array[c]], remM - size[array[c]]), dp(c + 1, remM));
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			String line = sc.nextLine();
			if(line.equals("#"))
				break;
			StringTokenizer st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N];
			cost = new int[N];
			size = new int[N];
			array = new int[N];
			int[] parent = new int[N];
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			String[][] in = new String[N][];
			for(int i = 0; i < N; i++)
			{
				in[i] = sc.nextLine().split(" ");
				map.put(in[i][0], i);
				adjList[i] = new ArrayList<Integer>(in[i].length - 2);
			}
			for(int i = 0; i < N; i++)
			{
				cost[i] = Integer.parseInt(in[i][1]);
				for(int j = 2; j < in[i].length; j++)
				{
					int v = map.get(in[i][j]);
					adjList[i].add(v);
					parent[v]++;
				}
			}
			idx = N - 1;
			for(int i = 0; i < N; i++)
				if(parent[i] == 0)
					toposort(i);
			
			memo = new int[N][M + 1];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], -1);

			int ans = dp(0, M);
			out.println(ans);
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
