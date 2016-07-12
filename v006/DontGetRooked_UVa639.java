package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DontGetRooked_UVa639 {

	//1. Solution 1: Easy Backtracking  (N <= 20)
	//2. Solution 2: Bipartite Matching (N <= 100)
	static int n, m, match[];
	static boolean[] visited;
	static ArrayList<Integer>[] adjList;
	
	static int maxMatches()
	{
		int matches = 0;
		match = new int[m];
		Arrays.fill(match, -1);
		for(int i = 0; i < n; ++i)
		{
			visited = new boolean[n];
			matches += aug(i);
		}
		return matches;
	}
	
	static int aug(int u)
	{
		visited[u] = true;
		for(int v: adjList[u])
		{
			int ux = match[v];
			if(ux == -1 || !visited[ux] && aug(ux) == 1)
			{
				match[v] = u;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			
			ArrayList<Segment> rows = new ArrayList<Segment>(), cols = new ArrayList<Segment>();
			char[][] grid = new char[N][];
			for(int i = 0; i < N; ++i)
			{
				grid[i] = sc.next().toCharArray();
				for(int y1 = 0, y2 = 0; y2 <= N; ++y2)
					if(y2 == N || grid[i][y2] == 'X')
					{
						if(y1 < y2)
							rows.add(new Segment(i, y1, i, y2 - 1));
						y1 = y2 + 1;
					}
			}
			
			for(int j = 0; j < N; ++j)
				for(int x1 = 0, x2 = 0; x2 <= N; ++x2)
					if(x2 == N || grid[x2][j] == 'X')
					{
						if(x1 < x2)
							cols.add(new Segment(x1, j, x2 - 1, j));
						x1 = x2 + 1;
					}
			n = rows.size(); m = cols.size();
			adjList = new ArrayList[n];
			for(int i = 0; i < n; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
					if(rows.get(i).intersect(cols.get(j)))
						adjList[i].add(j);
			out.println(maxMatches());
		}
		out.flush();
		out.close();
	}

	static class Segment
	{
		int x1, y1, x2, y2;
		
		Segment(int a, int b, int c, int d) { x1 = a; y1 = b; x2 = c; y2 = d; }
		
		boolean intersect(Segment s)		//this is horizontal and s is vertical
		{
			return s.y1 >= y1 && s.y1 <= y2 && x1 >= s.x1 && x1 <= s.x2;
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