package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class DecidingVictoryInGo_UVa852 {

	
	static char[][] board;
	static boolean[][] visited;
	static int[] dx = new int[]{0,0,1,-1};
	static int[] dy = new int[]{1,-1,0,0};
	static int counter; 
	static char border;
	static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==9 || j==9)
			return false;
		return true;
	}
	
	static void dfs(int i, int j)
	{
		counter++;
		visited[i][j] = true;
		
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y))
			{
				if(board[i][j]=='.' && board[x][y]!='.')
				{
					if(border=='#')
						border = board[x][y];
					else
						if(border!=board[x][y])
							border = '$';
				}
				if(board[x][y]==board[i][j] && !visited[x][y])
					dfs(x,y);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			board = new char[9][9];
			for(int i = 0; i < 9; i++)
			{
				String line = br.readLine();
				for(int j = 0; j < 9; j++)
					board[i][j] = line.charAt(j);
			}
			visited = new boolean[9][9];
			int blackScore = 0, whiteScore = 0;
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++)
					if(!visited[i][j])
					{
						counter =  0;
						border = '#';
						dfs(i,j);
						switch(board[i][j])
						{
						case 'X':blackScore+=counter;break;
						case 'O':whiteScore+=counter;break;
							default:
								if(border!='#'&&border!='$')
								{
									if(border=='X')
										blackScore+=counter;
									else
										whiteScore+=counter;
								}
						}
					}
			out.printf("Black %d White %d\n",blackScore,whiteScore);
		}
		out.flush();
	}
}
