package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TheColorGame_UVa10097 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			int[][] adjMat = new int[n][n];
			for(int i = 0; i < n; ++i)
				Arrays.fill(adjMat[i], -1);
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					adjMat[i][j] = sc.nextInt() - 1;
				
			int n1 = sc.nextInt() - 1, n2 = sc.nextInt() - 1, n3 = sc.nextInt() - 1;
			
			int[][] dist = new int[n][n];
			for(int i = 0; i < n; ++i)
				Arrays.fill(dist[i], -1);
			dist[n1][n2] = 0;
			Queue<State> q = new LinkedList<State>();
			q.add(new State(n1, n2));
			int ans = -1;
			while(!q.isEmpty())
			{
				State cur = q.remove();
				if(cur.a == n3 || cur.b == n3)
				{
					ans = dist[cur.a][cur.b];
					break;
				}
				
				int c = adjMat[cur.a][cur.b]; 
				if(c != -1 && dist[c][cur.b] == -1)
				{
					dist[c][cur.b] = dist[cur.a][cur.b] + 1;
					q.add(new State(c, cur.b));
				}
				
				c = adjMat[cur.b][cur.a]; 
				if(c != -1 && dist[cur.a][c] == -1)
				{
					dist[cur.a][c] = dist[cur.a][cur.b] + 1;
					q.add(new State(cur.a, c));
				}
			}
			if(ans == -1)
				out.format("Game #%d\nDestination is Not Reachable !\n\n", tc++);
			else
				out.format("Game #%d\nMinimum Number of Moves = %d\n\n", tc++, ans);

		}
	
		out.flush();
		out.close();
	}
	
	static class State
	{
		int a, b;
		
		State(int x, int y) { a = x; b = y; }
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
