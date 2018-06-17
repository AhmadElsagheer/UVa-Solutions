package v012;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class FindingNemo_UVa1202 {
	
	static final int INF = (int)1e9;
	static int[][] grid;
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};
	
	static int dijkstra(int Ex, int Ey)
	{
		if(Ex >= 200 || Ey >= 200)
			return 0;
		int[][] dist = new int[200][200];
		for(int i = 0; i < 200; ++i)
			Arrays.fill(dist[i], INF);
		dist[0][0] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(0, 0, 0));
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.x == Ex && cur.y == Ey)
				return cur.w;
			if(cur.w > dist[cur.x][cur.y])
				continue;
			for(int k = 0; k < 4; ++k)
			{
				int x = cur.x + dx[k], y = cur.y + dy[k];
				if(valid(x, y))
				{
					int cost = getCost(cur.x, cur.y, k);
					if(cost == 2)
						continue;
					if(cur.w + cost < dist[x][y])
						pq.add(new Edge(x, y, dist[x][y] = cur.w + cost));
				}
			}
		}
		return -1;
	}
	
	static int getCost(int x, int y, int k)
	{
		k <<= 1;
		return grid[x][y]>>k & 3;
	}
	
	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != 200 && y != 200;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			if(N + M == -2)
				break;
			grid = new int[200][200];	// 0 -> 7 (2 bits each): N, incomp.E, S, W (0 nth, 1 door, 2 wall)
			while(N-->0)
			{
				int x = sc.nextInt(), y = sc.nextInt(), d = sc.nextInt(), t = sc.nextInt();
				if(d == 0)
					buildX(x, y, t, 2);
				else
					buildY(x, y, t, 2);
			}
			
			while(M-->0)
			{
				int x = sc.nextInt(), y = sc.nextInt(), d = sc.nextInt();
				if(d == 0)
					buildX(x, y, 1, 1);
				else
					buildY(x, y, 1, 1);
			}
			
			int Ex = (int) (sc.nextDouble()), Ey = (int) (sc.nextDouble());
			out.println(dijkstra(Ex, Ey));
		}
		out.flush();
		out.close();

	}
	
	static void buildX(int x, int y, int t, int b)
	{
		for(int i = x; i < x + t; ++i)
		{
			grid[i][y] = msk(grid[i][y], 2, b);
			grid[i][y-1] = msk(grid[i][y-1], 0, b);
		}
	}

	static void buildY(int x, int y, int t, int b)
	{
		for(int i = y; i < y + t; ++i)
		{
			grid[x][i] = msk(grid[x][i], 3, b);
			grid[x-1][i] = msk(grid[x-1][i], 1, b);
		}
	}
	
	static int msk(int val, int pos, int bits)
	{
		pos <<= 1;
		return (val & ~(3<<pos)) | bits<<pos;
	}
	
	static class Edge implements Comparable<Edge>
	{
		int x, y, w;
		
		Edge(int a, int b, int c)
		{ 
			x = a; y = b; w = c;
		}
		
		public int compareTo(Edge e)
		{
			return w - e.w;
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