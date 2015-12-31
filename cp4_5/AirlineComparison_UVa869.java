package cp4_5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AirlineComparison_UVa869 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			boolean[][] graph1 = new boolean[100][100], graph2 = new boolean[100][100];
			int E = sc.nextInt();
			while(E-->0)
			{
				int u = sc.next().charAt(0) - 60, v = sc.next().charAt(0) - 60;
				graph1[u][v] = graph1[v][u] = true;
			}
			
			E = sc.nextInt();
			while(E-->0)
			{
				int u = sc.next().charAt(0) - 60, v = sc.next().charAt(0) - 60;
				graph2[u][v] = graph2[v][u] = true;
			}
			floyd(graph1); floyd(graph2);
			out.println(equivalent(graph1, graph2) ? "YES" : "NO");
			if(tc != 0)
				out.println();
		}
		out.flush();

	}
	
	static boolean equivalent(boolean[][] x, boolean[][] y)
	{
		for(int i = 0; i < 100; ++i)
			for(int j = 0; j < 100; ++j)
				if(x[i][j] != y[i][j])
					return false;
		return true;
					
	}
	
	static void floyd(boolean[][] adjMat)
	{
		for(int k = 0; k < 100; ++k)
			for(int i = 0; i < 100; ++i)
				for(int j = 0; j < 100; ++j)
					adjMat[i][j] = adjMat[i][j] || adjMat[i][k] && adjMat[k][j];
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
