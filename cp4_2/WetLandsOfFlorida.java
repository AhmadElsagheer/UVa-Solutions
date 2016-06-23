package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WetLandsOfFlorida {

	static char[][] adjMatrix = new char[200][200];
	static int N, M;
	static boolean[][] visited;
	static int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
	static int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
	
	public static int dfs(int i, int j)
	{
		
		
		visited[i][j] = true;
		
		if(adjMatrix[i][j]=='L')
			return 0;
		
		int area = 1;
		for(int k = 0; k < 8; k++)
		{
			if(!valid(i+dx[k],j+dy[k]) || visited[i+dx[k]][j+dy[k]])
				continue;
			area += dfs(i+dx[k],j+dy[k]);
		}
		
		return area;
		
		
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==N || j == M || i == -1 || j == -1)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		br.readLine();
		
		while(TC-->0)
		{
			
			String x = new StringTokenizer(br.readLine()).nextToken();
			
			adjMatrix[0] = x.toCharArray();
			M = x.length();
			N = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(true)
			{
				
				if(st.countTokens()>1)
					break;
				adjMatrix[N++] = st.nextToken().toCharArray();
				st = new StringTokenizer(br.readLine());
			}
			
			
			while(true)
			{
				
				int i = 0, j = 0;
				
				i =	Integer.parseInt(st.nextToken()) - 1;
				j = Integer.parseInt(st.nextToken()) - 1;
						
				visited = new boolean[200][200];
				
			
			
				
				int area = dfs(i,j);
				sb.append(area+"\n");
				if(!br.ready())
					break;
				st = new StringTokenizer(br.readLine());
				if(st.countTokens()==0)
					break;
			
			}
			
			
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
		
	}
}
