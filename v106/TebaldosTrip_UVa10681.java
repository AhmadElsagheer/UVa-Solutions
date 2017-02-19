package v106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TebaldosTrip_UVa10681 {

	static byte[][] memo;
	static ArrayList<Integer>[] adjList;
	static int dest;
	
	static int dp(int days, int city)
	{
		if(days == 0) return city == dest ? 1 : 0;
		
		if(memo[days][city] != -1) return memo[days][city];
		
		byte ans = 0;
		for(int i = 0; i < adjList[city].size(); i++)
		{
			int nxt = adjList[city].get(i);
			ans |= dp(days - 1, nxt);
		}
		return memo[days][city] = ans;
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int c = sc.nextInt(), l = sc.nextInt();
			if(c == 0) break;
			adjList = new ArrayList[c];
			for(int i = 0; i < c; i++) adjList[i] = new ArrayList<Integer>();
			while(l-->0)
			{
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				adjList[u].add(v); adjList[v].add(u);
			}
			int start = sc.nextInt() - 1;
			dest = sc.nextInt() - 1;
			int d = sc.nextInt();
			memo = new byte[d + 1][c];
			for(int i = 1; i <= d; i++) Arrays.fill(memo[i], (byte)-1);
			if(dp(d, start) == 1)
				sb.append("Yes, Teobaldo can travel.\n");
			else
				sb.append("No, Teobaldo can not travel.\n");
			
		}
		System.out.print(sb);
	}
	static class Scanner {
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
