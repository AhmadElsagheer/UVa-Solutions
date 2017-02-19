package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class TheBugSensorProblem_UVa1216 {

	static int n, t;
	static long dist2[][];
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			t = sc.nextInt();
			n = 0;
			ArrayList<Point> sensors = new ArrayList<Point>();
			while(true)
			{
				int x = sc.nextInt();
				if(x == -1)
					break;
				int y = sc.nextInt();
				n++;
				sensors.add(new Point(x, y));
			}
			dist2 = new long[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
					dist2[i][j] = dist2[j][i] = sensors.get(i).dist2(sensors.get(j));
			long ans = -1, lo = 0, hi = (int)1e12;
			while(lo <= hi)
			{
				long mid = lo + (hi - lo) /2;
				if(possible(mid))
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			out.println((int)Math.ceil(Math.sqrt(ans)));
		}
		out.flush();
		out.close();
	}
	
	static boolean possible(long maxDist)
	{
		adjList = new ArrayList[n];
		for(int i = 0; i < n; ++i)
			adjList[i] = new ArrayList<Integer>();
		for(int i = 0; i < n; ++i)
			for(int j = i + 1; j < n; ++j)
				if(maxDist >= dist2[i][j])
				{
					adjList[i].add(j);
					adjList[j].add(i);
				}
		boolean[] vis = new boolean[n];
		int cc = 0;
		for(int i = 0; i < n; ++i)
			if(!vis[i])
			{
				cc++;
				vis[i] = true;
				Queue<Integer> q= new LinkedList<Integer>();
				q.add(i);
				while(!q.isEmpty())
				{
					int u = q.remove();
					for(int v : adjList[u])
						if(!vis[v])
						{
							vis[v] = true;
							q.add(v);
						}
				}
			}
		return cc <= t;
	}
	
	static class Point 
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		long dist2(Point p) { return (long) (x - p.x) * (x - p.x) + (long) (y - p.y) * (y - p.y); }
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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}
}


