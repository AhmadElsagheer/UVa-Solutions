package cp4_5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class EdgetownsTrafficJams_UVa12319 {
	
	static final int INF = (int)1e6;
	
	static void floyd(int V, int[][] adjMat)
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					adjMat[i][j] = Math.min(adjMat[i][k] + adjMat[k][j], adjMat[i][j]);
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			int[][] graph1 = build(N, sc), graph2 = build(N, sc);
			if(accept(N, graph1, graph2, sc.nextInt(), sc.nextInt()))
				out.println("Yes");
			else
				out.println("No");
		}
		
		out.flush();
		out.close();
	}
	
	static boolean accept(int N, int[][] x, int[][] y, int A, int B)
	{
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				if(i != j && y[i][j] > x[i][j] * A + B)
					return false;
		return true;
	}
	
	static int[][] build(int N, Scanner sc) throws IOException
	{
		int[][] graph = new int[N][N];
		for(int i = 0; i < N; ++i)
			Arrays.fill(graph[i], INF);
		
		for(int i = 0; i < N; ++i)
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			while(st.hasMoreTokens())
			{
				int v = Integer.parseInt(st.nextToken()) - 1;
				graph[u][v] = 1;
			}
		}
		floyd(N, graph);
		return graph;
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){ br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){ br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

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

		public boolean ready() throws IOException {return br.ready();}


	}
}