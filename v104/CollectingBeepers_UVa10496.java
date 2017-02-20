package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CollectingBeepers_UVa10496 {

	static int[][] adjMatrix;
	static boolean[] visited;
	static int N;
	
	public static int dfs(int u, int n)
	{
		if(n==1)
			return adjMatrix[u][0];
		visited[u] = true;
		int min = 50000;
		for(int i = 0; i < N; i++)
			if(!visited[i])
				min = Math.min(min, adjMatrix[u][i] + dfs(i,n-1));
		
		visited[u] = false;
		return min;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int[][] pos = new int[11][2];
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			pos[0][0] = Integer.parseInt(st.nextToken());
			pos[0][1] = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(br.readLine()) + 1;
			for(int i = 1; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			adjMatrix = new int[N][N];
			for(int i = 0; i < N; i++)
				for(int j = i + 1; j < N; j++)
					adjMatrix[i][j] = adjMatrix[j][i] = Math.abs(pos[i][0]-pos[j][0]) + Math.abs(pos[i][1]-pos[j][1]);
			visited = new boolean[N];
			out.printf("The shortest path has length %d\n",dfs(0,N));
		}
		
		out.flush();
	}
	
}