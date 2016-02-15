package cp4_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DownWentTheTitanic_UVa11380 {
	
	static final int INF = (int)1e9;
	static ArrayList<Integer>[] adjList;
	static int R, C, res[][], p[];
	static int[] dx = new int[]{0, 0, 1, -1};
	static int[] dy = new int[]{1, -1, 0, 0};
	
	static int Vin(int r, int c)
	{
		return 2 + (r * C + c << 1); 
	}
	
	static int Vout(int r, int c)
	{
		return 3 + (r * C + c << 1);
	}
	
	static boolean valid(int r, int c)
	{
		if(r == -1 || c == -1 || r == R || c == C)
			return false;
		return true;
	}
	
	static void augment(int v)
	{
		if(v == 0)
			return;
		augment(p[v]);
		--res[p[v]][v];
		++res[v][p[v]];
	}
	
	static int maxFlow()
	{
		int mf = 0;
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			Arrays.fill(p, -1);
			q.add(0);
			p[0] = 0;
			while(!q.isEmpty())
			{
				int u = q.remove();
				if(u == 1)
					break;
				for(int i = 0; i < adjList[u].size(); ++i)
				{
					int v = adjList[u].get(i);
					if(p[v] == -1 && res[u][v] > 0)
					{
						p[v] = u;
						q.add(v);
					}
				}
			}
			if(p[1] == -1)
				break;
			++mf;
			augment(1);
		}
		return mf;
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			R = sc.nextInt();
			
			C = sc.nextInt();
			int P = sc.nextInt(), V = 2 * R * C + 2;
			res = new int[V][V];
			p = new int[V];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>(5);
			for(int i = 0; i < R; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < C; ++j)
				{
					int Vin = Vin(i, j), Vout = Vout(i, j);
					adjList[Vin].add(Vout);
					adjList[Vout].add(Vin);
					switch(line.charAt(j))
					{
					case '*':
						res[0][Vin] = INF;
						adjList[0].add(Vin);
					case '.':
						res[Vin][Vout] = 1;
						break;
					case '#':
						res[Vout][1] = P;
						adjList[Vout].add(1);
					case '@':
						res[Vin][Vout] = INF;
					}
					for(int k = 0; k < 4; ++k)
					{
						int x = i + dx[k], y = j + dy[k];
						if(valid(x, y))
						{
							int v = Vin(x, y);
							res[Vout][v] = INF;
							adjList[Vout].add(v);
							adjList[v].add(Vout);
						}
					}
				}
				
			}
			out.println(maxFlow());		
		}
		out.flush();

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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
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
