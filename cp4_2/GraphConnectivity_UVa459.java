package cp4_2;

import java.util.*;
import java.io.*;

public class GraphConnectivity_UVa459 {

	static int N;
	static boolean[][] adjMatrix;
	static boolean visited[];
	
	public static void dfs(int u)
	{
		visited[u] = true;
		for(int i = 0; i < N; i++)
			if(adjMatrix[u][i] && !visited[i])
				dfs(i);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		
		while(TC-->0)
		{
			
			N = br.readLine().charAt(0) - 'A' + 1;
			adjMatrix = new boolean[N][N];
			String line;
			while(br.ready() && !(line=br.readLine()).equals(""))
			{
				int u = line.charAt(0) - 'A';
				int v = line.charAt(1) - 'A';
				adjMatrix[u][v] = adjMatrix[v][u] = true;
			}
			visited = new boolean[N];
			int c = 0;
			for(int i = 0; i < N; i++)
				if(!visited[i])
				{
					c++; dfs(i);
				}
			
			sb.append(c+"\n");
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
