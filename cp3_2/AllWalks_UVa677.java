package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AllWalks_UVa677 {

	static StringBuilder sb = new StringBuilder();
	static int counter;
	static int[][] adjMatrix;
	static int[] trial;
	static boolean[] visited;
	static int N, K;
	
	static void backtrack(int u, int i)
	{
		visited[u] = true;
		trial[i] = u;
		if(i==K)
		{
			counter++;
			sb.append("(");
			for(int k = 0; k < K ; k++)
				sb.append(trial[k]+1+",");
			sb.append(trial[K]+1+")\n");
			visited[u] = false;
			return;
		}
		
		for(int j = 0; j < N; j++)
			if(adjMatrix[u][j]==1 && !visited[j])
				backtrack(j,i+1);
		visited[u] = false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean first = true;
		while(true)
		{
			if(first)
				first = false;
			else
				sb.append("\n");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			adjMatrix = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
			trial = new int[K+1];
			visited = new boolean[N];
			counter = 0;
			backtrack(0,0);
			if(counter==0)
				sb.append("no walk of length "+K+"\n");
			
			if(!br.ready() ||br.readLine()==null)
				break;
		}
		
		System.out.print(sb);
		
	}
}
