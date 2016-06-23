package v008;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class ChineseCheckers_UVa859 {

	static int[] dx = new int[] {0, 0, 1, 1, 1};
	static int[] dy = new int[] {-1, 1, 0, -1, 1};
	static int R, C;
	
	static boolean valid(int x, int y) { return x < R && y >= 0 && y < C; }
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		boolean first =true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			
			R = sc.nextInt(); C = sc.nextInt();
			int P = C<<2;
			int[][] used = new int[R][C];		//0 empty, 1 full, 2 processed
			while(P-->0)
			{
				int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
				used[x][y] = 1;
			}
			int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
			ArrayList<Cell> sol = new ArrayList<Cell>();
			Queue<Cell> q = new LinkedList<Cell>();
			//try steps
			for(int k = 0; k < 3; ++k)
			{
				int i = x + dx[k], j = y + dy[k];
				if(valid(i, j) && used[i][j] == 0)
					sol.add(new Cell(i, j, 1));
			}
			//try jumps
			q.add(new Cell(x, y, 0));
			while(!q.isEmpty())
			{
				Cell cur = q.remove();
				for(int k = 0; k < 5; ++k)
				{
					int x1 = cur.x + dx[k], y1 = cur.y + dy[k];
					int x2 = cur.x + dx[k]*2, y2 = cur.y + dy[k]*2;
					if(valid(x2, y2) && used[x1][y1] == 1 && used[x2][y2] == 0)
					{
						used[x2][y2] = 2;
						Cell nxt = new Cell(x2, y2, cur.moves + 1);
						sol.add(nxt);
						q.add(nxt);
					}
				}
			}
			Collections.sort(sol);
			for(Cell c: sol)
				out.println(c);
		}

		out.flush();
		out.close();
	}

	static class Cell implements Comparable<Cell>
	{
		int x, y, moves;

		Cell(int a, int b, int c) { x = a; y = b; moves = c; }
		
		public String toString() { return (x + 1) + " " + (y + 1) + " " + moves; }
		
		public int compareTo(Cell c)
		{
			if(x != c.x)
				return c.x - x;
			return y - c.y;
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

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}