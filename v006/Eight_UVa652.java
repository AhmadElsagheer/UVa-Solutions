package v006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Eight_UVa652 {

	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != 3 && y != 3;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		Permutation t = new Permutation();
		char[] moves = new char[] {'l', 'r', 'u', 'd'};
		int[] dx = new int[] { 0, 0, -1, 1};
		int[] dy = new int[] { -1, 1, 0, 0};
		
		
		TreeMap<Permutation, Parent> map = new TreeMap<Permutation, Parent>();
		map.put(t, new Parent(-1, null));
		Queue<Permutation> q = new LinkedList<Permutation>();
		q.add(t);
		while(!q.isEmpty())
		{
			Permutation cur = q.remove();
			Parent p = map.get(cur);
			for(int k = 0; k < 4; ++k)
			{
				int x = cur.x + dx[k], y = cur.y + dy[k];
				if(!valid(x, y))
					continue;
				int[][] nxt = new int[3][3];
				for(int i = 0; i < 3; ++i)
					for(int j = 0; j < 3; ++j)
						nxt[i][j] = cur.grid[i][j];
				int tmp = nxt[cur.x][cur.y];
				nxt[cur.x][cur.y] = nxt[x][y];
				nxt[x][y] = tmp;
				Permutation pn = new Permutation(nxt, x, y);
				if(!map.containsKey(pn))
				{
					map.put(pn, new Parent(k ^ 1, p));
					q.add(pn);
				}
			}
		}
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int[][] grid = new int[3][3];
			int x = -1, y = -1;
			for(int i = 0; i < 3; ++i)
				for(int j = 0; j < 3; ++j)
				{
					char c = sc.next().charAt(0);
					if(c == 'x')
					{
						c = '0';
						x = i; y = j;
					}
					grid[i][j] = c - '0';
				}

			Permutation s = new Permutation(grid, x, y);
			Parent res = map.get(s);
			if(res == null)
				out.println("unsolvable");
			else
			{
				StringBuilder sb = new StringBuilder();
				while(res.p != null)
				{
					sb.append(moves[res.move]);
					res = res.p;
				}
				out.println(sb);
			}
			if(tc != 0)
				out.println();
		}
		out.flush();
		out.close();
	}

	static class Permutation implements Comparable<Permutation>
	{
		int[][] grid;
		int x, y;

		Permutation(int[][] a, int b, int c) { grid = a; x = b; y = c; }
		
		Permutation()
		{
			grid = new int[3][3];
			for(int i = 0; i < 3; ++i)
				for(int j = 0; j < 3; ++j)
					grid[i][j] = i * 3 + j + 1;
			grid[x = 2][y = 2] = 0;
		}

		public int compareTo(Permutation p)
		{
			for(int i = 0; i < 3; ++i)
				for(int j = 0; j < 3; ++j)
				{
					int x = grid[i][j], y = p.grid[i][j];
					if(x != y)
						return x > y ? 1 : -1;
				}
			return 0;
		}
	}

	static class Parent
	{
		Parent p;
		int move;		//m = 0(l), 1(r), 2(u), 3(d)
		
		Parent(int x, Parent y) { move = x; p = y; }
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


	}
}