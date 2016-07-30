package v122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AntsColony_UVa12238 {

	static ArrayList<Pair>[] adjList;
	static int N, idx;
	static int[] E, L, H;
	static boolean[] visited;
	static long[] dist;
	static Pair[][] P;
	static RMQ q;
	
	static void preprocess()
	{
		E = new int[N<<1]; L = new int[N<<1]; H = new int[N<<1];
		Arrays.fill(H, -1);
		visited = new boolean[N];
		int k = (int)(Math.floor(Math.log(N)/Math.log(2))) + 1;
		P = new Pair[N][k];
		idx = 0;
		dist = new long[N];
		dfs(0, 0);
		q = new RMQ(L);
	}
	
	static void dfs(int u, int depth)
	{
		visited[u] = true;
		H[u] = idx;
		L[idx] = depth;
		E[idx++] = u;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			Pair next = adjList[u].get(i);
			if(!visited[next.node])
			{
				dist[next.node] = dist[u] + next.length;
				dfs(next.node, depth + 1);
				P[next.node][0] = new Pair(u,next.length);
				L[idx] = depth;
				E[idx++] = u;
			}
			
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Pair>();
			for(int i = 1; i <= N - 1; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int v =Integer.parseInt(st.nextToken());
				int length =Integer.parseInt(st.nextToken());
				adjList[i].add(new Pair(v,length));
				adjList[v].add(new Pair(i,length));
			}
			
			preprocess();
			
			int Q = Integer.parseInt(br.readLine());
			while(Q-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if(H[u]>H[v])
				{
					int tmp = u; u = v; v = tmp;
				}
				int lca = E[q.rmq(H[u], H[v])];
				long length = dist[u] + dist[v] - (dist[lca]<<1);
				sb.append(length).append(Q!=0?" ":"\n");	
			}
		}
		
		
		System.out.print(sb);
	}
	
	static class Pair
	{
		int node; long length;
		Pair(int x, long y)
		{
			node = x;
			length = y;
		}
	}
	
	static class RMQ
	{
		int[] A, SpT[];
		
		RMQ(int[] A)
		{
			int n = A.length; this.A = A;
			int k = (int)(Math.floor(Math.log(n)/Math.log(2))) + 1;
			SpT = new int[n][k];
			for(int i = 0; i < n; i++)
				SpT[i][0] = i;
			
			for(int j = 1; 1<<j <= n; j++)
				for(int i = 0; i + (1<<j) - 1 < n; i++)
					if(A[SpT[i][j-1]] <= A[SpT[i+(1<<(j-1))][j-1]])
						SpT[i][j] = SpT[i][j-1];
					else
						SpT[i][j] = SpT[i+(1<<(j-1))][j-1];
		}
		
		int rmq(int i, int j)
		{
			int k = (int)(Math.floor(Math.log(j-i+1)/Math.log(2)));
			if(A[SpT[i][k]] <= A[SpT[j - (1<<k) + 1][k]])
				return SpT[i][k];
			else
				return SpT[j - (1<<k) + 1][k];
		}
	}
	
	
	
}

