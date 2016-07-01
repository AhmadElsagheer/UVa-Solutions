package v103;

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

public class killingAliensInBorgMaze_UVa10307 {

	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[][] grid;
	static int R, C, A;
	
	static int[] bfs(int x, int y)
	{
		int[] ret = new int[A], dist[] = new int[R][C];
		for(int i = 0; i < R; ++i)
			Arrays.fill(dist[i], -1);
		dist[x][y] = 0;
		Queue<Cell> q = new LinkedList<Cell>();
		q.add(new Cell(x, y));
		while(!q.isEmpty())
		{
			Cell cur = q.remove();
			x = cur.x; y = cur.y;
			int d = dist[x][y];
			if(grid[x][y] >= 0)
				ret[grid[x][y]] = d;
			
			for(int k = 0; k < 4; ++k)
			{
				int i = x + dx[k], j = y + dy[k];
				if(grid[i][j] != -1 && dist[i][j] == -1)
				{
					dist[i][j] = d + 1;
					q.add(new Cell(i, j));
				}
					
			}
		}
		return ret;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			C = sc.nextInt(); R = sc.nextInt(); A = 0;
			grid = new int[R][C];
			ArrayList<Cell> pos = new ArrayList<Cell>();
			for(int i = 0; i < R; ++i)
			{
				char[] s = sc.nextLine().toCharArray();
				for(int j = 0; j < C; ++j)
					if(s[j] == ' ')
						grid[i][j] = -2;
					else if(s[j] == '#')
						grid[i][j] = -1;
					else
					{
						grid[i][j] = A++;
						pos.add(new Cell(i, j));
					}
				
			}
			int[][] adjMat = new int[A][A];
			for(int i = 0; i < A; ++i)
			{
				Cell s = pos.get(i);
				adjMat[i] = bfs(s.x, s.y);
			}
			Edge[] edgeList = new Edge[A * (A-1) / 2];
			for(int i = 0, k = 0; i < A; ++i)
				for(int j = i + 1; j < A; ++j)
					edgeList[k++] = new Edge(i, j, adjMat[i][j]);
			Arrays.sort(edgeList);
			int mst = 0;
			UnionFind uf = new UnionFind(A);
			for(Edge e: edgeList)
				if(uf.union(e.u, e.v))
					mst += e.w;
			out.println(mst);
		}
		out.flush();
		out.close();
	}

	static class Cell
	{
		int x, y;
		
		Cell(int a, int b) { x = a; y = b; }
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		
		Edge(int x, int y, int z) { u = x; v = y; w = z; }
		
		public int compareTo(Edge e) { return w - e.w; }
	}
	
	static class UnionFind
	{
		int[] p, rank;
		
		UnionFind(int N)
		{
			p = new int[N];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		int findSet(int x) { return p[x] == x ? x : (p[x] = findSet(p[x])); }
		
		boolean union(int x, int y)
		{
			x = findSet(x);
			y = findSet(y);
			if(x == y)
				return false;
			
			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					++rank[y];
			}
			return true;
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
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}