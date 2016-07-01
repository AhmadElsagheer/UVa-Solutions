package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MeetingProfMiguel_UVa10171 {

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

		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			int[][] g1 = new int[26][26], g2 = new int[26][26];
			for(int i = 0; i < 26; ++i)
			{
				Arrays.fill(g1[i], INF);
				Arrays.fill(g2[i], INF);
				g1[i][i] = g2[i][i] = 0;
			}
			while(N-->0)
			{
				char a = sc.next().charAt(0);
				char b = sc.next().charAt(0);
				int u = sc.next().charAt(0) - 'A';
				int v = sc.next().charAt(0) - 'A';
				int w = sc.nextInt();
				int[][] g = a == 'Y' ? g1 : g2;
				g[u][v] = Math.min(g[u][v], w);
				if(b == 'B')
					g[v][u] = Math.min(g[v][u], w);
			}
			int s1 = sc.next().charAt(0) - 'A', s2 = sc.next().charAt(0) - 'A';
			floyd(g1, 26);
			floyd(g2, 26);
			int min = INF;
			ArrayList<Character> sol = null;
			for(int i = 0; i < 26; ++i)
			{
				int dist = g1[s1][i] + g2[s2][i];
				if(dist < min)
				{
					min = dist;
					sol = new ArrayList<Character>();
				}
				
				if(dist < INF && dist == min)
					sol.add((char)(i + 'A'));
			}
			if(sol == null)
				out.println("You will never meet.");
			else
			{
				out.print(min);
				for(char x: sol)
					out.print(" " + x);
				out.println();
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


	}
}