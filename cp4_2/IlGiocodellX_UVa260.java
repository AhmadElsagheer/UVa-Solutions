package cp4_2;

import java.util.*;
import java.io.*;

public class IlGiocodellX_UVa260 {

	static char[][] board;
	static int N;
	static int[] dx = new int[]{-1,-1,0,0,1,1};
	static int[] dy = new int[]{-1,0,-1,1,0,1};
	
	public static void dfs(int i, int j)
	{
		char c = board[i][j];
		board[i][j] = c=='b'?'x':'y';
		for(int k = 0; k < 6; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && board[x][y]==c)
				dfs(x,y);
		}
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==N || j==N)
			return false;
		return true;
	}
	
	public static char findWinner()
	{
		for(int i = 0; i < N; i++)
			if(board[N-1][i]=='x')
				return 'B';
		return 'W';
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			board = new char[N][];
			for(int i = 0; i < N; i++)
				board[i] = br.readLine().toCharArray();
			for(int i = 0; i < N; i++)
				if(board[i][0]=='w')
					dfs(i,0);
			for(int i = 0; i < N; i++)
				if(board[0][i]=='b')
					dfs(0,i);
			out.printf("%d %c\n",k++,findWinner());
		}
		out.flush();
		out.close();
		
	}
}
