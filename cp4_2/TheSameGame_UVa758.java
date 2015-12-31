package cp4_2;
import java.io.*;
import java.util.*;

public class TheSameGame_UVa758 {

	static char[][] board = new char[10][15];;
	static int[][] cluster;
	static boolean[][] visited;
	static int[] dx = new int[]{-1,1,0,0};
	static int[] dy = new int[]{0,0,-1,1};
	static PrintWriter out = new PrintWriter(System.out);
	static int score, countBalls;
	
	public static boolean process(int k)
	{
		int max = 0, x = -1, y = -1;
		char color = 'X';
		visited = new boolean[10][15];
		cluster = new int[10][15];
		
		for(int j = 0; j < 15; j++)
			for(int i = 0; i < 10; i++)
				if(!visited[i][j] && board[i][j]<'X')
				{
					dfs(i,j,false);
					if(cluster[i][j]>max)
					{
						max = cluster[i][j];
						x = i; y = j;color = board[i][j];
					}
				}
		if(max<2)
		{
			if(countBalls==0) score += 1000;
			out.printf("Final score: %d, with %d balls remaining.\n",score,countBalls);
			return false;
		}
		int subscore = (max-2)*(max-2);
		score += subscore;
		countBalls -= max;
		visited = new boolean[10][15];
		dfs(x,y,true);
		compress();
		out.printf("Move %d at (%d,%d): removed %d balls of color %c, got %d points.\n",k,x+1,y+1,max,color,subscore);
		return true;
	}
	
	public static void dfs(int i, int j, boolean removing)
	{
		visited[i][j] = true;
		cluster[i][j] = 1;
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && !visited[x][y] && board[x][y]==board[i][j])
			{
				dfs(x,y,removing);
				cluster[i][j] += cluster[x][y];
			}
		}
		if(removing)
			board[i][j] = 'X'; 
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i == 10 || j ==15)
			return false;
		return true;
	}
	
	public static void compress()
	{
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 15; j++)
				if(board[i][j]=='X')
					shiftDown(i,j--);
		for(int j = 0; j < 14; j++)
			if(board[0][j]=='Y')
				shiftLeft(j--);
	}
	
	public static void shiftDown(int x, int y)
	{
		for(int i = x; i < 9; i++)
			board[i][y] = board[i+1][y];
		board[9][y] = 'Y';
	}
	
	public static void shiftLeft(int Col)
	{
		for(int j = Col; j < 14; j++)
			for(int i = 0; i < 10; i++)
				board[i][j] = board[i][j+1];
		for(int i = 0; i < 10; i++)
			board[i][14] = 'Z';
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++)
		{
			br.readLine();
			for(int i = 9; i > -1; i--)
				board[i] = br.readLine().toCharArray();
			score = 0;
			countBalls = 150;
			
			out.printf("Game %d:\n\n", t);
			int k = 1;
			while(process(k++));
			if(t!=TC)
				out.println();
		}
		
		out.flush();
	}
	
	
}
