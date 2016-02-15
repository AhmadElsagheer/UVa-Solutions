package cp4_5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MPIMaelstrom_UVa423 {

	static int[][] adjMat;
	static final int INF = (int)1e9;
	static int V;
	
	static int floyd()
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
		int min = 0;
		for(int i = 1; i < V; ++i)
			min = Math.max(min, adjMat[0][i]);
		return min;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		V = sc.nextInt();
		adjMat = new int[V][V];
		for(int i = 0; i < V; ++i)
		{
			Arrays.fill(adjMat[i], INF);
			adjMat[i][i] = 0;
		}
		for(int i = 0; i < V; ++i)
			for(int j = 0; j < i; ++j)
			{
				String x = sc.next();
				if(!x.equals("x"))
					adjMat[i][j] =  adjMat[j][i] = Integer.parseInt(x);
			}
		out.println(floyd());
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
