package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Airlines_UVa10075 {
	
	static final double R = 6378;
	static final int INF = (int)1e9;
	
	static double degToRad(double theta) { return theta * Math.PI / 180.0; }
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while(true)
		{
			int N = sc.nextInt(), M = sc.nextInt(), Q = sc.nextInt();
			if(N == 0 && M == 0 && Q == 0)
				break;
			if(tc != 1)
				sb.append("\n");
			Point[] cities = new Point[N];
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < N; ++i)
			{
				map.put(sc.next(), i);
				cities[i] = new Point(sc.nextDouble(), sc.nextDouble());
			}
			
			int[][] adjMat = new int[N][N];
			for(int i = 0; i < N; ++i)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
			}
			while(M-->0)
			{
				int u = map.get(sc.next()), v = map.get(sc.next());
				adjMat[u][v] = (int)Math.round(cities[u].dist(cities[v]));
			}
			
			for(int k = 0; k < N; ++k)
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
			
			sb.append("Case #"+ tc++ + "\n");
			while(Q-->0)
			{
				int u = map.get(sc.next()), v = map.get(sc.next());
				int ans = adjMat[u][v];
				if(ans == INF)
					sb.append("no route exists\n");
				else
					sb.append(ans+" km\n");
			}
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Point
	{
		double x, y, z;
		
		Point(double alpha, double theta) 
		{
			alpha = degToRad(alpha);
			theta = degToRad(theta);
			x = R * Math.cos(alpha) * Math.cos(theta);
			y = R * Math.cos(alpha) * Math.sin(theta);
			z = R * Math.sin(alpha);
		}
		
		double dist(Point p) { return Math.acos(dot(p) / (R * R)) * R; }
		
		double dot(Point p) { return x * p.x + y * p.y + z * p.z; }
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