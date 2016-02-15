package cp4_4;


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

public class GopherAndHawks_UVa10610 {

	static final double EPS = 1e-9;
	static int V;
	static ArrayList<Integer>[] adjList;
	
	static int bfs()
	{
		int[] dist = new int[V];
		Arrays.fill(dist, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		dist[0] = 0;
		q.add(0);
		while(!q.isEmpty())
		{
			int u = q.remove();
			if(u == 1)
				break;
			for(int i = 0; i < adjList[u].size(); ++i)
			{
				int v = adjList[u].get(i);
				if(dist[v] == -1)
				{
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
		}
		return dist[1];
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int v = sc.nextInt(), t = sc.nextInt() * 60;
			if(v == 0 && t == 0)
				break;
			double d = (double)v * t;
			d *= d;
			V = 0;
			Point[] holes = new Point[2000];
			while(!sc.nxtEmpty())
				holes[V++] = new Point(sc.nextDouble(), sc.nextDouble());
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < V; ++i)
				for(int j = i + 1; j < V; ++j)
					if(holes[i].dist2(holes[j]) <= d + EPS)
					{
						adjList[i].add(j);
						adjList[j].add(i);
					}
			int ans = bfs();
			if(ans == -1)
				out.println("No.");
			else
			{
				if(ans != 0)
					ans--;
				out.format("Yes, visiting %d other holes.\n", ans);
			}
			
		}
		out.flush();
		
	}
	
	static class Point
	{
		double x, y;
		Point(double a, double b) { x = a; y = b; }
		
		double dist2(Point p) { return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y); }
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
