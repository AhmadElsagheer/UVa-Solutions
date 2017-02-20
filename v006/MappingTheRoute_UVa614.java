package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MappingTheRoute_UVa614 {

	
	static int[][] path;
	static boolean[][][] block;
	static int R, C, x, y;
	static int[] dx = new int[]{0,-1,0,1};
	static int[] dy = new int[]{-1,0,1,0};
	
	public static boolean dfs(int i, int j)
	{
		if(i==x && j==y)
			return true;
		for(int k = 0; k < 4; k++)
		{
			int a = i + dx[k];
			int b = j + dy[k];
			if(!block[i][j][k] && path[a][b]==0 )
			{
				path[a][b] = path[i][j] + 1; 
				if(dfs(a,b))
					return true;
			}
		}
		path[i][j] = -1;
		return false;
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j == -1 || i == R || j==C)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;int k = 1;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			while(!st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			while(!st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			if(R==0)
				break;
			while(!st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			while(!st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken()) - 1;
			while(!st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			while(!st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken()) - 1;
			
			path = new int[R][C];
			block = new boolean[R][C][4];
			for(int i = 0; i < R; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < C; j++)
				{
					int c = Integer.parseInt(st.nextToken());
					switch(c)
					{
					case 1:	block[i][j][2] = true;if(valid(i,j+1))block[i][j+1][0] = true;break;
					case 3:	block[i][j][2] = true;if(valid(i,j+1))block[i][j+1][0] = true;
					case 2:	block[i][j][3] = true;if(valid(i+1,j))block[i+1][j][1] = true;break;
					}
				}
			}
			for(int i = 0; i < R; i++)
			{
				block[i][0][0] = true;
				block[i][C-1][2] = true;
			}
			for(int i = 0; i < C; i++)
			{
				block[0][i][1] = true;
				block[R-1][i][3] = true;
			}
			path[a][b] = 1;
			dfs(a,b);
			//output
			out.printf("Maze %d\n\n",k++);
			for(int i = 0; i < R; i++)
			{
				out.print("+");
				for(int j = 0; j < C; j++)
					out.print(block[i][j][1]?"---+":"   +");
				out.println();
				for(int j = 0; j < C; j++)
				{
					out.print(block[i][j][0]?"|":" ");
					if(path[i][j]==-1)
						out.print("???");
					else
						if(path[i][j]==0)
							out.print("   ");
						else
							out.printf("%3d",path[i][j]);
				}
				out.println("|");
			}
			out.print("+");
			for(int j = 0; j < C; j++)
				out.print("---+");
			out.println("\n\n");
			
			
			br.readLine();
		}
		out.flush();
		out.close();
	}
}
