package cp4_2;
import java.util.*;
import java.io.*;

public class RankTheLanguages_UVa10336 {

	static char[][] world;
	static boolean[][] visited;
	static int R,C;
	static int[] dx = new int[]{0,0,-1,1};
	static int[] dy = new int[]{1,-1,0,0};
	public static void dfs(int i, int j)
	{
		visited[i][j] = true;
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && !visited[x][y] && world[x][y]==world[i][j])
				dfs(x,y);
		}
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C)
			return false;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			out.printf("World #%d\n",k);
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			world = new char[R][];
			for(int i = 0; i < R; i++)
				world[i] = br.readLine().toCharArray();
			visited = new boolean[R][C];
			int[] count  = new int[26];
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(!visited[i][j])
					{
						count[world[i][j]-'a']++;
						dfs(i,j);
					}
			while(true)
			{
				int max = 0;int index = -1;
				for(int i = 0; i < 26; i++)
					if(count[i]>max)
					{
						max = count[i];
						index  = i;
					}
				if(max==0)
					break;
				else
				{
					out.printf("%c: %d\n",(char)(index+'a'),max);
					count[index] = 0;
				}
			}

	}
		
		out.flush();
	}
}
