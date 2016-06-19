package cp4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class KnightsInFEN_UVa10422 {

	/*
	 * Solvable with BFS, # states = 25C12 * 12 with maximum 8 transitions for each state 
	 */
	static int[] dx = new int[] {-1, -1, -2, -2, 1, 1, 2, 2};
	static int[] dy = new int[] {-2, 2, -1, 1, -2, 2, -1, 1};
	static TreeMap<State, Integer> reachableStates;

	static int bfs(State s, boolean query, TreeMap<State, Integer> map)
	{
		int x, y, msk;
		Queue<State> q = new LinkedList<State>();
		map.put(s, 0);	
		q.add(s);

		while(!q.isEmpty())
		{
			s = q.remove();
			Integer moves = map.get(s);
			if(query && reachableStates.containsKey(s))
				return moves + reachableStates.get(s);
			if(moves == 5)
				continue;
			x = s.x; y = s.y; msk = s.msk;
			int posCur = getMaskIndex(x, y);
			for(int k = 0; k < 8; ++k)
			{
				int i = x + dx[k], j = y + dy[k];
				if(valid(i, j))
				{
					int posNxt = getMaskIndex(i, j), bit = (msk >> posNxt) & 1;
					int nxt = msk & ~(1<<posNxt) | bit<<posCur;
					s = new State(i, j, nxt);
					if(!map.containsKey(s))
					{
						map.put(s, moves + 1);
						q.add(s);
					}
				}
			}
		}
		return -1;
	}

	static int getMaskIndex(int x, int y) {	return x * 5 + y; }

	static boolean valid(int x, int y) { return x >= 0 && y >= 0 && x < 5 && y  < 5; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		char[][] finalState = new char[54][];
		finalState[0] = "11111".toCharArray();
		finalState[1] = "01111".toCharArray();
		finalState[2] = "00 11".toCharArray();
		finalState[3] = "00001".toCharArray();
		finalState[4] = "00000".toCharArray();

		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[][] curState = new char[5][];
			for(int i = 0; i < 5; ++i)
				curState[i] = sc.nextLine().toCharArray();

			reachableStates = new TreeMap<State, Integer>();
			bfs(new State(curState), false, reachableStates);
			int ans = bfs(new State(finalState), true, new TreeMap<State, Integer>());

			if(ans == -1)
				out.println("Unsolvable in less than 11 move(s).");
			else
				out.printf("Solvable in %d move(s).\n", ans);
		}
		out.flush();
		out.close();
	}

	static class State implements Comparable<State>
	{
		int x, y, msk;

		State(int a, int b, int c) { x = a; y = b; msk = c; }

		State(char[][] grid)
		{
			for(int i = 0, k = 0; i < 5; ++i)
				for(int j = 0; j < 5; ++j, ++k)
					if(grid[i][j] != ' ')
						msk |= (grid[i][j]-'0')<<k;
					else
					{
						x = i; y = j;
					}
		}

		public int compareTo(State s)
		{
			if(msk != s.msk)
				return msk - s.msk;
			if(x != s.x)
				return x - s.x;
			return y - s.y;
		}
	}


	static class Scanner
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}