package cp4_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BombsNoTheyAreMines_UVa10653 {

	static int R, C;
	static boolean[][] bomb;
	static int[] dx = new int[]{0, 0, 1, -1};
	static int[] dy = new int[]{1, -1, 0, 0};
	
	static int bfs(int Si, int Sj, int Ti, int Tj)
	{
		int[][] dist = new int[R][C];
		for(int i = 0; i < R; ++i)
			Arrays.fill(dist[i], -1);
		dist[Si][Sj] = 0;
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(Si, Sj));
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			if(cur.i == Ti && cur.j == Tj)
				break;
			for(int k = 0; k < 4; ++k)
			{
				int x = cur.i + dx[k], y = cur.j + dy[k];
				if(valid(x, y) && dist[x][y] == -1)
				{
					dist[x][y] = dist[cur.i][cur.j] + 1;
					q.add(new Pair(x, y));
				}
					
			}
		}
		return dist[Ti][Tj];
	}
	static boolean valid(int i, int j)
	{
		if(i == -1 || j == -1 || i == R || j == C || bomb[i][j])
			return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			R = sc.nextInt();
			C = sc.nextInt();
			if(R == 0)
				break;
			int b = sc.nextInt();
			bomb = new boolean[R][C];
			while(b-->0)
			{
				int r = sc.nextInt(), k = sc.nextInt();
				while(k-->0)
					bomb[r][sc.nextInt()] = true;
			}
			out.println(bfs(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
			
		}
		out.flush();
		
	}
	
	
	static class Pair
	{
		int i, j;
		
		Pair(int x, int y) { i = x; j = y; }
		
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
