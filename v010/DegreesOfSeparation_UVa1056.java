package v010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DegreesOfSeparation_UVa1056 {

	static final int INF = 1000000000;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			if(V==0)
				break;
			int[][] adjMat = new int[V][V];
			for(int i = 0; i < V; i++)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
			}
			int next = 0;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			while(E-->0)
			{
				if(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				String x = st.nextToken();
				if(!map.containsKey(x))
					map.put(x, next++);
				if(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				String y = st.nextToken();
				if(!map.containsKey(y))
					map.put(y, next++);
				
				int u = map.get(x), v = map.get(y);
				adjMat[u][v] = adjMat[v][u] = 1;
			}
			
			for(int k = 0; k < V; k++)
				for(int i = 0; i < V; i++)
					for(int j = 0; j < V; j++)
						adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
			
			int max = 0;
			for(int i = 0; i < V; i++)
				for(int j = 0; j < V; j++)
					max = Math.max(max, adjMat[i][j]);
			sb.append("Network "+tc+++": ").append(max==INF?"DISCONNECTED":max).append("\n\n");
		}
		System.out.print(sb);
	}
}
