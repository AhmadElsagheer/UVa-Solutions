package v108;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThunderMountain_UVa10803 {

	
	static final int INF = (int)1e8;
	static double[][] adjMat;
	static int V;
	
	static void floyd()
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			V = sc.nextInt();
			adjMat = new double[V][V];
			for(int i = 0; i < V; ++i)
			{
				Arrays.fill(adjMat[i], INF);
				adjMat[i][i] = 0.0;
			}
			
			int[][] pos = new int[V][2];
			for(int i = 0; i < V; ++i)
			{
				pos[i][0] = sc.nextInt();
				pos[i][1] = sc.nextInt();
			}
			for(int i = 0; i < V; ++i)
				for(int j = i + 1; j < V; ++j)
				{
					int x1 = pos[i][0], x2 = pos[j][0], y1 = pos[i][1], y2 = pos[j][1];
					int d2 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
					if(d2 <= 100)
						adjMat[i][j] = adjMat[j][i] = Math.sqrt(d2);
				}
			double max = 0;
			floyd();
			for(int i = 0; i < V; ++i)
				for(int j = i + 1; j < V; ++j)
					max = Math.max(max, adjMat[i][j]);
			out.format("Case #%d:\n", t);
			if(max > INF / 10)
				out.println("Send Kurdy\n");
			else
				out.format("%.4f\n\n", max);
			
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
