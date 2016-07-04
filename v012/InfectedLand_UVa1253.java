package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class  InfectedLand_UVa1253 {

	static int[] dx = new int[] {0, 0, -1, -1, -1, 1, 1, 1};
	static int[] dy = new int[] {-1, 1, 0, -1, 1, 0, -1, 1};

	static boolean valid(int N, int i, int j)
	{
		return i != -1 && j != -1 && i != N && j != N;
	}

	static int countInfAdj(int x, int y, int msk, int N)
	{
		int c = 0;
		for(int k = 0; k < 8; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			if(valid(N, i, j))
			{
				int cell = i * N + j;
				if((msk & 1<<cell) != 0)
					++c;
			}
		}
		return c;
	}

	static int bfs(int N, State s)
	{
		TreeMap<State, Integer> dist = new TreeMap<State, Integer>();
		Queue<State> q = new LinkedList<State>();
		dist.put(s, 0);
		q.add(s);
		while(!q.isEmpty())
		{
			State cur = q.remove();

			int d = dist.get(cur);
			if(Integer.bitCount(cur.inf) == 1)
				return d;
			int xx = cur.pos / 5, yy = cur.pos%5;
			int nxtInf =cur.inf & ~(1<<xx * N + yy);
			for(int k = 0; k < 8; ++k)
			{
				int x = xx + dx[k], y = yy + dy[k];
				if(!valid(N, x, y))
					continue;
				int cell = x * N + y;
				if((cur.inf & 1<<cell) != 0)
					continue;

				int begin = nxtInf | 1<<cell, end = 1<<cell;
				for(int i = 0; i < N * N; ++i)
					if(i != cell)
					{
						int count = countInfAdj(i / N, i % N, begin, N);
						if((begin & 1<<i) == 0)
						{
							if(count == 3)
								end |= 1<<i;							
						}
						else
						{
							if(count == 2 || count == 3)
								end |= 1<<i;
						}
					}
				State nxt = new State(x, y, end);
				if(!dist.containsKey(nxt))
				{
					dist.put(nxt, d + 1);
					q.add(nxt);
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			int x = -1, y = -1, s = 0;
			for(int i = 0, k = 0; i < N; ++i)
			{
				char[] t = sc.next().toCharArray();
				for(int j = 0; j < N; ++j, ++k)
					if(t[j] == '@')
					{
						x = i; y = j; s |= 1<<k;
					}
					else if(t[j] == '#')
						s |= 1<<k;
			}
			out.println(bfs(N, new State(x, y, s)));
		}
		out.flush();
		out.close();
	}

	static class State implements Comparable<State>
	{
		int pos, inf;

		State(int a, int b, int c) { pos = a * 5 + b; inf = c; }

		public int compareTo(State s)
		{
			if(inf != s.inf)
				return inf - s.inf;
			return pos - s.pos;
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
	}
}