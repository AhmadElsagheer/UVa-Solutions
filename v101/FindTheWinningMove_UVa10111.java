package v101;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class FindTheWinningMove_UVa10111 {

	static TreeMap<State, Boolean>memo;

	static boolean win(State state)
	{
		Boolean ret = memo.get(state);
		if(ret != null)
			return ret;
		ret = false;
		char opp = getOpp(state.turn);
		if(!gameOver(state.grid, opp))
		{
			for(int i = 0; !ret && i < 4; ++i)
				for(int j = 0; !ret && j < 4; ++j)
					if(state.grid[i][j] == '.')
					{
						State nextState = new State(state.grid, opp);
						nextState.grid[i][j] = state.turn;
						ret |= !win(nextState);
					}
		}
		memo.put(state, ret);
		return ret;
	}

	static char getOpp(char x) { return x == 'o' ? 'x' : 'o'; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		memo = new TreeMap<State, Boolean>();
		while(true)
		{
			String s = sc.next();
			if(s.equals("$"))
				break;
			char[][] grid = new char[4][];
			for(int i = 0; i < 4; ++i)
				grid[i] = sc.next().toCharArray();
			int r = -1, c = -1;
			for(int i = 0; r == -1 && i < 4; ++i)
				for(int j = 0; r == -1 && j < 4; ++j)
					if(grid[i][j] == '.')
					{
						State start = new State(grid, 'o');
						start.grid[i][j] = 'x';
						if(!win(start))
						{
							r = i;
							c = j;
						}
					}
			if(r == -1)
				out.println("#####");
			else
				out.printf("(%d,%d)\n", r, c);
		}
		out.flush();
		out.close();
	}

	static boolean gameOver(char[][] grid, char c)
	{
		for(int i = 0; i < 4; ++i)
		{
			boolean y1 = true, y2 = true;
			for(int j = 0; j < 4; ++j)
			{
				y1 &= grid[i][j] == c;
				y2 &= grid[j][i] == c;
			}
			if(y1 || y2)
				return true;
		}
		boolean y1 = true, y2 = true;
		for(int i = 0; i < 4; ++i)
		{
			y1 &= grid[i][i] == c;
			y2 &= grid[i][4-i-1] == c;
		}
		return y1 || y2;
	}

	static class State implements Comparable<State>
	{
		char[][] grid;
		char turn;

		State(char[][] x, char t) 
		{ 
			turn = t;
			grid = new char[4][4];
			for(int i = 0; i < 4; ++i)
				for(int j = 0; j < 4; ++j)
					grid[i][j] = x[i][j];
		}

		public int compareTo(State s)
		{
			if(turn != s.turn)
				return turn - s.turn;
			for(int i = 0; i < 4; ++i)
				for(int j = 0; j < 4; ++j)
					if(grid[i][j] != s.grid[i][j])
						return grid[i][j] - s.grid[i][j];
			return 0;
		}
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

		public boolean hasNext() throws IOException
		{
			while(br.ready() && (st == null || !st.hasMoreTokens()))
				st = new StringTokenizer(br.readLine());
			return st.hasMoreTokens();
		}


	}
}