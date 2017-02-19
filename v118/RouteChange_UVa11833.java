package v118;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RouteChange_UVa11833 {

	static final int INF = (int)1e9;
	static int V, E, C, K;
	static int[][] adjMat;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(true)
		{
			V = sc.nextInt(); E = sc.nextInt(); C = sc.nextInt(); K = sc.nextInt();
			if(V == 0)
				break;
			adjMat = new int[V][V];
			for(int i = 0; i < V; ++i)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0;
			}
			
			while(E-->0)
			{
				int u  = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				adjMat[u][v] = adjMat[v][u] = w;
			}
			
			for(int i = 0; i < C; ++i)
				for(int j = 0; j < V; ++j)
					if(i != j && (i == C- 1 || j != i + 1))
						adjMat[i][j] = INF;
			
			for(int k = 0; k < V; ++k)
				for(int i = 0; i < V; ++i)
					for(int j = 0; j < V; ++j)
						adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
			out.println(adjMat[K][C-1]);
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
