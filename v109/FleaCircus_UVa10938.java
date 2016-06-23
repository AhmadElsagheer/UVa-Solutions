package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FleaCircus_UVa10938 {
	
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int[] H, E, L;
	static int[][] P;
	static int N, idx;
	static RMQ q;
	
	static void preprocess()
	{
		H = new int[N<<1]; E = new int[N<<1]; L = new int[N<<1];
		Arrays.fill(H, -1);
		idx = 0;
		int k = (int)(Math.floor(Math.log(N)/Math.log(2))) + 1;
		P = new int[N][k];
		visited = new boolean[N];
		dfs(0, 0);
		ancestors();
		q = new RMQ(L);
	}
	
	static void dfs(int u, int depth)
	{
		visited[u] = true;
		H[u] = idx;
		E[idx] = u;
		L[idx++] = depth;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			if(visited[v])
				continue;
			P[v][0] = u;
			dfs(v, depth + 1);
			E[idx] = u;
			L[idx++] = depth;
		}
	}
	
	static void ancestors()
	{
		int k = P[0].length; P[0][0] = -1;
		
		for(int i = 0; i < N; i++)
			for(int j = 1; j < k; j++)
				P[i][j] = -1;
		
		for(int j = 1; j < k; j++)			
			for(int i = 1; i < N; i++)
				if(P[i][j-1] != -1)
					P[i][j] = P[P[i][j-1]][j-1];
	}
	
	static int kthAncestor(int u, int k)
	{
		while(k != 0 && u != -1)
		{
			int x = (int)(Math.floor(Math.log(k)/Math.log(2)));
			u = P[u][x];
			k -= 1<<x;
		}
			
		return u;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < N - 1; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				adjList[u].add(v); 
				adjList[v].add(u);

			}
			
			preprocess();
			
			int x = Integer.parseInt(br.readLine());
			while(x-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				if(H[i]>H[j])
				{
					int tmp = i;i = j; j = tmp;
				}
				int lca = E[q.rmq(H[i], H[j])];
				int num = L[H[i]] + L[H[j]] - (L[H[lca]]<<1) + 1;
				if(L[H[i]]<L[H[j]])
				{
					int tmp = i;i = j; j = tmp;
				}
				if(num%2==1)
					sb.append("The fleas meet at "+(kthAncestor(i,num/2)+1)+".\n");
				else
				{
					int r = kthAncestor(i,num/2 - 1);
					int p = P[r][0];
					if(p>r)
					{
						int tmp = p; p = r; r = tmp;
					}
					sb.append("The fleas jump forever between "+(p+1)+" and "+(r+1)+".\n");
				}
			}
		}
		
		System.out.print(sb);
	}
}

class RMQ
{
	int A[], SpT[][];
	
	RMQ(int[] A) 
	{ 
		int n = A.length; this.A = A;
		int k = (int)(Math.floor(Math.log(n)/Math.log(2))) + 1;
		SpT = new int[n][k];
		
		for(int i = 0; i < n; i++)
			SpT[i][0] = i;
		
		for(int j = 1; 1 << j <= n; j++)
			for(int i = 0; i + (1<<j) - 1 < n; i++)
				if(A[SpT[i][j-1]] <= A[SpT[i+(1<<j-1)][j-1]])
					SpT[i][j] = SpT[i][j-1];
				else
					SpT[i][j] = SpT[i+(1<<j-1)][j-1];
	}
	
	int rmq(int i, int j)
	{
		int k = (int)(Math.floor(Math.log((j-i+1))/Math.log(2)));
		if(A[SpT[i][k]] <= A[SpT[j-(1<<k)+1][k]])
			return SpT[i][k];
		else
			return SpT[j-(1<<k)+1][k];
	}
}