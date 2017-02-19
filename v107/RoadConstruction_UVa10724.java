package v107;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RoadConstruction_UVa10724 {
	
	static final double EPS =1e-9, INF = 1e8;
	static double[][] dist;
	static double floyd(int V, double[][] adjMat)
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
		double ret = 0.0;
		for(int i = 0; i < V; ++i)
			for(int j = i + 1; j < V; ++j)
				ret += adjMat[i][j];
		return ret;
	}
	
	static double floyd(int V, double[][] adjMat, int u, int v)
	{
		
		double ret = 0.0;
		for(int i = 0; i < V; ++i)
				for(int j = i + 1; j < V; ++j)
					ret += Math.min(adjMat[i][j], Math.min(adjMat[i][v] + dist[u][v] + adjMat[u][j], adjMat[i][u] + dist[u][v]+ adjMat[v][j]));
		return ret;
	}
	
	public static void main(String[] args) throws IOException {

		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int V = sc.nextInt(), E = sc.nextInt();
			if(V + E == 0)
				break;
			Point[] points = new Point[V];
			for(int i = 0; i < V; ++i)
				points[i] = new Point(sc.nextInt(), sc.nextInt());
			double[][] adjMat = new double[V][V];
			boolean[][] used = new boolean[V][V];
			dist = new double[V][V];
			for(int i = 0; i < V; ++i)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0.0;
			}
			for(int i = 0; i < V; ++i)
				for(int j = i + 1; j < V; ++j)
					dist[i][j] = dist[j][i] = points[i].dist(points[j]);
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjMat[u][v] = adjMat[v][u] = dist[u][v];
				used[u][v] = used[v][v] = true;
			}
			double X = floyd(V, adjMat),  max = 0.0, d = 0.0;
			int u = -1, v = -1;
			for(int i = 0; i < V; ++i)
				for(int j = i + 1; j < V; ++j)
					if(!used[i][j] && !used[j][i])
					{
						double Y = X - floyd(V, adjMat, i, j);
						if(Y <= 1.0 + EPS)
							continue;
						if(Y > max + EPS)
						{
							max = Y;
							u = i + 1;
							v = j + 1;
							d = dist[i][j];
						}
						else
						{
							if(Math.abs(max - Y) < EPS && dist[i][j] + EPS < d)
							{
								u = i + 1;
								v = j + 1;
								d = dist[i][j];
							}
						}
					}
			if(u == -1)
				out.println("No road required");
			else
				out.format("%d %d\n", u, v);
		}
		out.flush();
	}

	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p) { return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y)); }
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
