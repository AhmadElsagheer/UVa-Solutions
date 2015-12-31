package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CheckTheCheck_UVa10196 {

	static int[] dx_q = new int[]{0,0,-1,1,-1,-1,1,1};
	static int[] dy_q = new int[]{-1,1,0,0,-1,1,-1,1};
	static int[] dx_n = new int[]{-1,-1,-2,-2,1,1,2,2};
	static int[] dy_n = new int[]{-2,2,-1,1,-2,2,-1,1};
	static int[] runner = new int[]{1,2,3,4,5,6,7};
	
	static char[][] board;
	static char[][] board_black;  
	static char[][] board_white;  
	
	static void check(int i, int j)
	{
		char[][] checkAt = Character.isLowerCase(board[i][j])?board_black:board_white;
		int start,end, dx[], dy[];
		int[] step = runner;
		switch(board[i][j])
		{
		case 'r':case 'R':start = 0; end = 4;break;
		case 'b':case 'B':start = 4; end = 8;break;
		case 'P':start = 4; end = 6;step = new int[]{1};break;
		case 'p':start = 6; end = 8;step = new int[]{1};break;
		default:start = 0;end = 8;
		}
		switch(board[i][j])
		{
		case 'n':case 'N':dx = dx_n;dy = dy_n;step = new int[]{1};break;
		default:dx = dx_q; dy = dy_q;
		}
		for(int k = start; k < end; k++)
			for(int p = 0; p < step.length; p++)
			{
				int x = i + dx[k]*step[p];
				int y = j + dy[k]*step[p];
				if(!valid(x,y))
					break;
				checkAt[x][y] = 'X';
				if(board[x][y]!='.')
					break;
			}
	}
	
	
	static boolean valid(int i, int j)
	{
		if(i < 0 || j < 0 || i > 7 || j > 7)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			board = new char[8][8];
			int ik = -1, jk = -1, iK = -1, jK = -1;
			for(int i = 0; i < 8; i++)
			{
				String line = br.readLine();
				for(int j = 0; j < 8; j++)
				{
					board[i][j] = line.charAt(j);
					if(board[i][j]=='K'){iK = i; jK = j;}
					else if(board[i][j]=='k'){ik = i; jk = j;}
				}
			}
			if(ik==-1)
				break;
			board_black = new char[8][8];
			board_white = new char[8][8];
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++)
					if(board[i][j]!='.' && board[i][j]!='K' && board[i][j]!='k')
						check(i,j);
			if(board_white[ik][jk]=='X')
				out.printf("Game #%d: black king is in check.\n",k++);
			else
				if(board_black[iK][jK]=='X')
					out.printf("Game #%d: white king is in check.\n",k++);
				else
					out.printf("Game #%d: no king is in check.\n",k++);
			br.readLine();
		}
		out.flush();
	}
}
