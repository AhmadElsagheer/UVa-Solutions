package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Commandos_UVa11463 {

	static final int INF = 1000000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++)
		{
			int V = Integer.parseInt(br.readLine());
			int R = Integer.parseInt(br.readLine());
			int[][] adjMatrix = new int[V][V];
			for(int i = 0; i < V; i++)
				for(int j = 0; j < V; j++)
					if(i!=j)
						adjMatrix[i][j] = INF;
			while(R-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adjMatrix[u][v] = adjMatrix[v][u] = 1;
			}
			for(int k = 0; k < V; k++)
				for(int i = 0; i < V; i++)
					for(int j = 0; j < V; j++)
						adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int max = 0;
			for(int i = 0; i < V; i++)
				max = Math.max(max, adjMatrix[s][i] + adjMatrix[i][d]);
			sb.append("Case "+t+": "+max+"\n");
		}
		System.out.print(sb);
	}
}
