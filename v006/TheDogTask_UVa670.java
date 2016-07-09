package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheDogTask_UVa670 {

	static final double EPS = 1e-9;
	static int n, m, match[];
	static ArrayList<Integer>[] adjList;
	static boolean[] vis;
	
	static int maxMatches()
	{
		match = new int[m];
		Arrays.fill(match, -1);
		int matches = 0;
		for(int i = 0; i < n; ++i)
		{
			vis = new boolean[n];
			matches += aug(i);
		}
		return matches;
	}
	
	static int aug(int u)
	{
		vis[u] = true;
		for(int v: adjList[u])
			if(match[v] == -1 || !vis[match[v]] && aug(match[v]) == 1)
			{
				match[v] = u;
				return 1;
			}
		return 0;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), M = sc.nextInt();
			Point[] meet = new Point[N], inter = new Point[M];
			for(int i = 0; i < N; ++i)
				meet[i] = new Point(sc.nextInt(), sc.nextInt());
			for(int i = 0; i < M; ++i)
				inter[i] = new Point(sc.nextInt(), sc.nextInt());
			n = N - 1;
			m = M;
			adjList = new ArrayList[n];
			for(int i = 0; i < n; ++i)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < n; ++i)
			{
				Point p = meet[i], q = meet[i+1];
				for(int j = 0; j < m; ++j)
					if(inter[j].canReach(p, q))
						adjList[i].add(j);
			}
	
			int matches = maxMatches();
			int[] interAfter = new int[n];
			Arrays.fill(interAfter, -1);
			for(int i = 0; i < m; ++i)
				if(match[i] != -1)
					interAfter[match[i]] = i;
			sb.append(N + matches + "\n");
			for(int i = 0; i < n; ++i)
			{
				if(i != 0)
					sb.append(" ");
				Point p = meet[i];
				sb.append(p.x + " " + p.y);
				if(interAfter[i] != -1)
				{
					p = inter[interAfter[i]];
					sb.append(" " + p.x + " " + p.y);
				}
			}
			sb.append(" " + meet[N-1].x + " " + meet[N-1].y + "\n");
			if(tc != 0)
				sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		boolean canReach(Point p, Point q) { return p.dist(q) * 2 + EPS > dist(p) + dist(q); }
		
		double dist(Point p) { return Math.sqrt(sq(x - p.x) + sq(y - p.y)); }
		
		int sq(int x) { return x * x; }
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}