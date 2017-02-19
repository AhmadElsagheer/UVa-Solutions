package v107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheOrcAttack_UVa10793 {

	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int V = sc.nextInt(), E = sc.nextInt();
			int[][] adjMat = new int[V][V];
			for(int i = 0; i < V; ++i)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
			}
			while(E-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
				adjMat[u][v] = adjMat[v][u] = Math.min(adjMat[u][v], w);
			}
			
			for(int k = 0; k <V; ++k)
				for(int i = 0; i <V; ++i)
					for(int j = 0; j <V; ++j)
						adjMat[i][j] = Math.min(adjMat[i][k] + adjMat[k][j], adjMat[i][j]);
			boolean connected = true;
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					if(adjMat[i][j] == INF)
						connected = false;
			if(!connected)
				out.format("Map %d: -1\n", t);
			else
			{
				int cost = INF;
				for(int i = 5; i < V; ++i)
				{
					int x = adjMat[i][0];
					for(int j = 1; j < 5; ++j)
						if(x != adjMat[i][j])
							x = -1;
					if(x != -1)
					{
						int cur_cost = 0;
						for(int j = 0; j < V; ++j)
							cur_cost = Math.max(cur_cost, adjMat[i][j]);
						cost = Math.min(cur_cost, cost);
					}
				}
				out.format("Map %d: %d\n", t, cost == INF ? -1 : cost);
			}
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

		public boolean ready() throws IOException {return br.ready(); }


	}
}
