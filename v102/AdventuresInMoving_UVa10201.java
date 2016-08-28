package v102;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AdventuresInMoving_UVa10201 {
	
	static final int INF = (int)1e8;
	static int N, price[], dist[], memo[][];
		
	static int dp(int pos, int tank)
	{
		if(pos == N)
			return tank >= 100 ? 0 : INF;
		if(memo[pos][tank] != -1)
			return memo[pos][tank];
		
		int fuel = INF, go = INF;
		if(tank >= dist[pos])
			go = dp(pos + 1, tank - dist[pos]);
		if(tank < 200)
			fuel = price[pos] + dp(pos, tank + 1);
		return memo[pos][tank] = Math.min(fuel, go);
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int d = sc.nextInt(), last = 0;
			N = 0;
			price = new int[105];
			dist = new int[105];
			price[0] = INF;
			while(sc.ready() && !sc.nxtEmpty())
			{
				int pos = sc.nextInt(), p = sc.nextInt();
				dist[N++] = pos - last;
				price[N] = p;
				last = pos;
			}
			dist[N++] = d - last;
			memo = new int[N][201];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			int ans = dp(0, 100);
			if(ans == INF)
				out.println("Impossible");
			else
				out.println(ans);
			if(tc != 0)
				out.println();
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
