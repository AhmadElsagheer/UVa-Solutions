package cp4_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class InterstarTransport_UVa1247 {
	
	static final int INF = (int)1e9;
	static int V;
	static int[][] adjMat;
	static int[][] stations;
	static int[][] P;
	
	static void floyd()
	{
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					if(adjMat[i][j] > adjMat[i][k] + adjMat[k][j] || adjMat[i][j] == adjMat[i][k] + adjMat[k][j] && stations[i][j] > stations[i][k] + stations[k][j] - 1)
					{
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
						P[i][j] = P[k][j];
						stations[i][j] = stations[i][k] + stations[k][j] - 1;
					}
	}
	
	static void print(int i, int j, boolean last)
	{
		if(i != j)
			print(i, P[i][j], false);
		
		if(last)
			out.printf("%c\n", (char)(j + 'A'));
		else
			out.printf("%c ", (char)(j + 'A'));
	}
	
	static PrintWriter out = new PrintWriter(System.out);
	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		int E = sc.nextInt();
		adjMat = new int[V][V];
		stations = new int[V][V];
		P = new int[V][V];
		for(int i = 0; i < V; ++i)
		{
			Arrays.fill(adjMat[i], INF);
			adjMat[i][i] = 0;
			stations[i][i] = 1;
		}
		while(E-->0)
		{
			int u = sc.next().charAt(0) - 'A', v = sc.next().charAt(0) - 'A', cost = sc.nextInt();
			adjMat[u][v] = adjMat[v][u] = cost;
			stations[u][v] = stations[v][u] = 2;
			P[u][v] = u;
			P[v][u] = v;
		}
		floyd();
		int q = sc.nextInt();
		while(q-->0)
		{
			
			int u = sc.next().charAt(0) - 'A', v = sc.next().charAt(0) - 'A';
			print(u, v, true);
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


