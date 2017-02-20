package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bicoloring_UVa10004 {

	static boolean[][] adjMatrix;
	static int N;
	
	public static boolean bfs(int u)
	{
		int[] visited = new int[N];
		visited[u] = 1;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(u);
		while(!q.isEmpty())
		{
			int cur = q.remove();
			for(int i = 0; i < N; i++)
				if(adjMatrix[cur][i])
					if(visited[i]==0)
					{
						visited[i] = -visited[cur];
						q.add(i);
					}
					else
						if(visited[i]==visited[cur])
							return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			int E = Integer.parseInt(br.readLine());
			adjMatrix = new boolean[N][N];
			while(E-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adjMatrix[u][v] = adjMatrix[v][u] = true;
			}
			out.println(bfs(0)?"BICOLORABLE.":"NOT BICOLORABLE.");
				
		}
		
		
		out.flush();
		out.close();
	}
}
