package v110;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TheScroogeCoProblem_UVa11047 {
	
	static final int INF = (int)1e9;
	static int[][] P;
	
	static void floyd(int V, int[][] adjMat)
	{
		P = new int[V][V];
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				P[i][j] = i;
		for(int k = 0; k < V; ++k)
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
					if(adjMat[i][j] > adjMat[i][k] + adjMat[k][j])
					{
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
						P[i][j] = P[k][j];
					}
	}
	
	static ArrayList<Integer> sol;
	
	static void print(int i, int j)
	{
		if(P[i][j] != i)
			print(i, P[i][j]);
		sol.add(j);
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int V = sc.nextInt();
			int[][] adjMat = new int[V][V];
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			String[] unmap = new String[V];
			for(int i = 0; i < V; ++i)
				map.put(unmap[i] = sc.next(), i);
			
			for(int i = 0; i < V; ++i)
				for(int j = 0; j < V; ++j)
				{
					int w = sc.nextInt();
					if(w != -1)
						adjMat[i][j] = w;
					else
						adjMat[i][j] = INF; 
				}
			
			
			floyd(V, adjMat);
			int q = sc.nextInt();
			while(q-->0)
			{
				String x = sc.next(), y = sc.next(), z = sc.next();
				int u = map.get(y), v = map.get(z);
				if(adjMat[u][v] == INF)
					out.printf("Sorry Mr %s you can not go from %s to %s\n", x, y, z);
				else
				{
					out.printf("Mr %s to go from %s to %s, you will receive %d euros\n", x, y, z, adjMat[u][v]);
					sol = new ArrayList<Integer>();
					print(u, v);
					out.printf("Path:%s", y);
					for(int c: sol)
						out.printf(" %s", unmap[c]);
					out.println();
				}
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}