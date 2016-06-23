package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheSettlersOfCatan_UVa539 {

	static boolean[][] adjMatrix;
	static boolean[] visited;
	static int N;
	
	public static int dfs(int u)
	{
		int max = 0;
		for(int i = 0; i < N; i++)
			if(adjMatrix[u][i])
				{
					adjMatrix[u][i] = adjMatrix[i][u] = false;
					max = Math.max(dfs(i)+1,max);
					adjMatrix[u][i] = adjMatrix[i][u] = true;
				}
		return max;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			int E = Integer.parseInt(st.nextToken());
			adjMatrix = new boolean[N][N];
			while(E-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adjMatrix[u][v] = adjMatrix[v][u] = true;
			}
			visited = new boolean[N];
			int max = 0;
			for(int i = 0; i < N; i++)
				max = Math.max(max, dfs(i));
			out.println(max);
		}
		out.flush();
	}
}

