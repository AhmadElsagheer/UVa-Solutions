package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ShoppingTrip_UVa11284 {

	static int N, P;
	static int[] map;
	static int[][] adjMatrix;
	static int[][] dist;
	static int[][] memo;
	static int[] save;
	static final int INF = 900000000, UNCAL = -123456789;
	
	static int dp(int pos, int mask)
	{
		if(mask == (1 << P) - 1)
			return -dist[pos][0];
		if(memo[pos][mask]!=UNCAL)
			return memo[pos][mask];
		int max = -dist[pos][0];
		for(int i = 0; i < N; i++)
			if(map[i]!=-1 && (mask & (1<<map[i])) == 0)
				max = Math.max(max, save[i] - dist[pos][i] + dp(i,mask | (1 << map[i])));
		return memo[pos][mask] = max;
	}
	
	static int dijkstra(int S, int T)
	{
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[S] = 0;
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.add(new Pair(S,0));
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			if(cur.cost > dist[cur.to])
				continue;
			for(int i = 0; i < N; i++)
				if(adjMatrix[cur.to][i]!=-1)
				{
					int totalCost = cur.cost + adjMatrix[cur.to][i];
					if(totalCost<dist[i])
					{
						dist[i] = totalCost;
						q.add(new Pair(i,totalCost));
					}
				}
				
		}
		return dist[T];
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()) + 1;
			int E = Integer.parseInt(st.nextToken());
			adjMatrix = new int[N][N];
			dist = new int[N][N];
			save = new int[N];
			map = new int[N];
			Arrays.fill(map, -1);
			for(int i = 0; i < N; i++)
				Arrays.fill(adjMatrix[i], -1);
			while(E-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int cost = doubleToInt(st.nextToken());
				if(adjMatrix[u][v]!=-1)
					cost = Math.min(cost, adjMatrix[u][v]);
				adjMatrix[u][v] = adjMatrix[v][u] = cost;
			}
			for(int i = 0; i < N; i++)
				for(int j = i + 1; j < N; j++)
					dist[i][j] = dist[j][i] = dijkstra(i,j);
			P = Integer.parseInt(br.readLine());
			for(int i = 0; i < P; i++)
			{
				st = new StringTokenizer(br.readLine());
				int store = Integer.parseInt(st.nextToken());
				int val = doubleToInt(st.nextToken());
				save[store] = val;
				map[store] = i;
			}
			int mask = 0;
			memo = new int[N][1<<P];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			int max = dp(0,mask);
			if(max==0)
				sb.append("Don't leave the house\n");
			else
				sb.append("Daniel can save $").append(new DecimalFormat("0.00").format(max/100.0)).append("\n");
		}
		System.out.print(sb);
	}
	
	static int doubleToInt(String x)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < x.length(); i++)
			if(x.charAt(i)>='0'&&x.charAt(i)<='9')
				sb.append(x.charAt(i));
		return Integer.parseInt(sb.toString());
	}
}

class Pair implements Comparable<Pair>
{
	int to,cost;
	Pair(int x, int y) {to = x; cost = y;}
	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return cost - o.cost;
	}
	
}
