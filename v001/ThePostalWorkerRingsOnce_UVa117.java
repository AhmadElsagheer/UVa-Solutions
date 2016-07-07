package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThePostalWorkerRingsOnce_UVa117 {
	
	static final int INF = (int)1e9;
	
	static void floyd(int[][] adjMat, int V)
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int[] deg = new int[26];
			int[][] adjMat = new int[26][26];
			for(int i = 0; i < 26; ++i)
				Arrays.fill(adjMat[i], INF);
			int sum = 0;
			while(true)
			{
				String s = sc.next();
				if(s.equals("deadend"))
					break;
				int u = s.charAt(0) - 'a', v = s.charAt(s.length() - 1) - 'a';
				adjMat[u][v] = adjMat[v][u] = Math.min(adjMat[u][v], s.length());
				sum += s.length();
				deg[u]++;
				deg[v]++;
			}
			int x = -1, y = -1;
			for(int i = 0; i < 26; ++i)
				if(deg[i]%2 != 0)
					if(x == -1)
						x = i;
					else
						y = i;
			if(x == -1)
				out.println(sum);
			else
			{
				floyd(adjMat, 26);
				out.println(sum + adjMat[x][y]);
			}
		}
		out.flush();
		out.close();
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