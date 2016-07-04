package v109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SevenSeas_UVa10923 {
	
	static int[] dx = new int[]{0, 0, 0, 1, -1, -1, 1, -1, 1};
	static int[] dy = new int[]{0, -1, 1, 0, 0, -1, 1, 1, -1};
	
	static boolean isKing(int x, int y, int ships, char[][] board)
	{
		if(ships == 0)
			return true;
		
		for(int k = 0; k < 9; ++k)
		{
			boolean can = true;
			int nxtX = x + dx[k], nxtY = y + dy[k], dest = 0;
			
			if(!valid(nxtX, nxtY, board))
				continue;
			
			char[][] nxtBoard = new char[9][8];
			for(int i = 0; can && i < 9; ++i)
				for(int j = 0; can && j < 8; ++j)
					if(board[i][j] == 'E')
					{
						if(nxtBoard[i][j] == 0)
							nxtBoard[i][j] = '.';
						int mx = i + getSign(i, nxtX), my = j + getSign(j, nxtY);
						if(mx == nxtX && my == nxtY)
							can = false;
						if(board[mx][my] == '#' || nxtBoard[mx][my] == '#')
							++dest;
						else 
							if(nxtBoard[mx][my] == 'E')
							{
								dest += 2;
								nxtBoard[mx][my] = '#';
							}
							else
								nxtBoard[mx][my] = 'E';

					}
					else
						if(nxtBoard[i][j] == 0)
							nxtBoard[i][j] = board[i][j];
			
			if(can && isKing(nxtX, nxtY, ships - dest, nxtBoard))
				return true;
		}
		return false;
	}
	
	static int getSign(int from, int to)
	{
		return from > to ? -1 : ((from < to) ? 1 : 0);
	}
	
	static boolean valid(int x, int y, char[][] board)
	{
		return x >= 0 && y >= 0 && x < 9 && y < 8 && (board[x][y] == '.' || board[x][y] == 'S');
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		char[][] board = new char[9][8];
		while(tc-->0)
		{
			int x = -1, y = -1, ships = 0;
			for(int i = 0; i < 9; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < 8; ++j)
				{	
					board[i][j] = line.charAt(j);
					if(board[i][j] == 'S') 
					{ 
						x = i; y = j;
					}
					else
						if(board[i][j] == 'E')
							++ships;
					
				}
			}
			if(isKing(x, y, ships, board))
				out.println("I'm the king of the Seven Seas!");
			else
				out.println("Oh no! I'm a dead man!");
		}
		
		out.flush();
		out.close();
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

		public double nextDouble() throws IOException 
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


	}
}
