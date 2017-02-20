package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GraphColoring_UVa193 {

	static int N, MAX;
	static boolean[] black;
	static boolean[] sol;
	static boolean[][] adjMatrix;
	
	public static void backtrack(int i, int count)
	{
		if(i==N+1)
		{
			if(count>MAX)
			{
				MAX = count;
				for(int k = 1; k <= N; k++)
					sol[k] = black[k];
			}
			return;
		}
		if(!hasBlackNeighbours(i))
		{
			black[i] = true;
			backtrack(i+1,count+1);
		}
		black[i] = false;
		if(N-i+count > MAX)
			backtrack(i+1,count);
		
	
	}
	
	public static boolean hasBlackNeighbours(int i)
	{
		for(int j = 1; j <= N; j++)
			if(adjMatrix[i][j]&&black[j])
				return true;
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			adjMatrix = new boolean[N+1][N+1];
			while(E-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if(u!=v)
					adjMatrix[u][v] = adjMatrix[v][u] = true;
			}
			black = new boolean[N+1];
			sol = new boolean[N+1];
			MAX = 0;
			backtrack(1,0);
			
			sb.append(MAX).append("\n");
			boolean first = true;
			for(int i = 1; i <= N; i++)
				if(sol[i])
				{
					if(first)
						first = false;
					else
						sb.append(" ");
					sb.append(i);
				}
			sb.append("\n");
			
		}
			
		
		System.out.print(sb);
		
	}
}
