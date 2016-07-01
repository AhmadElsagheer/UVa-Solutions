package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class AgainstMammoths_UVa1221 {

	static final int NIL = 0, INF = (int)2e9;
	static int V, n, m;	//n(left) + m(right) = V
	static ArrayList<Integer>[] adjList;	//size = n, idx = [0, n-1], val = [0, m-1]
	static int[] pair_U, pair_V, dist;
	
	static int hopcroftKarp()
	{
		pair_U = new int[n + 1];	//filled with NIL
		pair_V = new int[m + 1];	//filled with NIL
		dist = new int[n + 1];
		
		int matching = 0;
		while(bfs())
			for(int u = 1; u <= n; ++u)
				if(pair_U[u] == NIL && dfs(u))
					++matching;
		return matching;
	}
	
	static boolean bfs()
	{
		Queue<Integer> q = new LinkedList<Integer>();
		for(int u = 1; u <= n; ++u)
			if(pair_U[u] == NIL)
			{
				dist[u] = 0;
				q.add(u);
			}
			else
				dist[u] = INF;
		dist[NIL] = INF;
		while(!q.isEmpty())
		{
			int u = q.remove();
			if(dist[u] < dist[NIL])
				for(int v : adjList[u])
					if(dist[pair_V[v]] == INF)
					{
						dist[pair_V[v]] = dist[u] + 1;
						q.add(pair_V[v]);
					}
		}
		return dist[NIL] != INF;
	}
	
	static boolean dfs(int u)
	{
		if(u == NIL)
			return true;
		
		for(int v : adjList[u])
			if(dist[pair_V[v]] == dist[u] + 1 && dfs(pair_V[v]))
			{
				pair_U[u] = v;
				pair_V[v] = u;
				return true;
			}
		dist[u] = INF;
		return false;
		
					
	}
	
	static boolean possible(int[][] adjMat, int maxTime)
	{
		adjList = new ArrayList[n + 1];
		for(int i = 0; i < n; ++i)
		{
			adjList[i + 1] = new ArrayList<Integer>();
			for(int j = 0; j < m; ++j)
				if(adjMat[i][j] != INF && adjMat[i][j] <= maxTime)
					adjList[i + 1].add(j + 1);
		}
		if(hopcroftKarp() == m)
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{

			n = sc.nextInt();
			m = sc.nextInt();
			if(n == 0 && m == 0)
				break;
			int[] H_rate = new int[n], H_ini = new int[n], A_rate = new int[m], A_ini = new int[m];
			for(int i = 0; i < n; ++i)
			{
				H_ini[i] = sc.nextInt();
				H_rate[i] = sc.nextInt();
			}
			for(int i = 0; i < m; ++i)
			{
				A_ini[i] = sc.nextInt();
				A_rate[i] = sc.nextInt();
			}
			int[][] travelling = new int[n][m];
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
					travelling[i][j] = sc.nextInt();
			if(m > n)
				out.println("IMPOSSIBLE");
			else
			{
				ArrayList<Integer> t = new ArrayList<Integer>(n * m);
				V = n + m;
				int[][] adjMat = new int[n][m];
				for(int i = 0; i < n; ++i)
					for(int j = 0; j < m; ++j)
					{
						int num = A_rate[j] * travelling[i][j] + A_ini[j] - H_ini[i];
						int den = H_rate[i] - A_rate[j];
						if(den <= 0)
						{
							if(H_ini[i] == 0 || num > 0)
								adjMat[i][j] = INF;
							else
								adjMat[i][j] = travelling[i][j];
						}
						else
							adjMat[i][j] = Math.max(H_ini[i] == 0 ? 1 : 0, (num + den - 1) / den) + travelling[i][j];
						t.add(adjMat[i][j]);	
					}
				Collections.sort(t);
				int ans = -1, lo = 0, hi = t.size() - 1;
				while(lo <= hi)
				{
					int mid = lo + (hi - lo) / 2;
					if(possible(adjMat, t.get(mid)))
					{
						ans = t.get(mid);
						hi = mid - 1;
					}
					else
						lo = mid + 1;
				}
				out.println(ans == -1 ? "IMPOSSIBLE" : ans);
			}
		
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
}


