package cp2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DemandingDilemma_UVa11550 {

	static int[][] InMatrix;
	static boolean[][] adjMatrix;
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			InMatrix = new int[N][M];
			adjMatrix = new boolean[N][N];
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++)
					InMatrix[i][j] = Integer.parseInt(st.nextToken());	
			}
			sb.append(possible()?"Yes":"No").append("\n");
		}
		
		System.out.print(sb);
	}
	
	static boolean possible()
	{
		for(int j = 0; j < M; j++)
		{
			int u = -1, v = -1;
			for(int i = 0; i < N; i++)
				if(InMatrix[i][j]==1)
				{
					if(u==-1)
						u = i;
					else
						if(v==-1)
							v = i;
						else
							return false;
				}
			if(v==-1 || adjMatrix[u][v])
				return false;
			adjMatrix[u][v] = adjMatrix[v][u] = true;
			
		}
		
		return true;
	}
}
