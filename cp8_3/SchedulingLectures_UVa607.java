package cp8_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SchedulingLectures_UVa607 {


	static int N, L, C;
	static int[] t;
	static Pair[][] memo;
	
	static Pair dp(int idx, int rem)
	{
		if(idx == N)
		{
			return new Pair(0, di(rem));
		}
		if(memo[idx][rem] != null) return memo[idx][rem];
		
		Pair ans = new Pair(0, 0);
		//open new lecture
		Pair nxt = dp(idx + 1, L - t[idx]);
		ans.lectures = nxt.lectures + 1;
		ans.DI = nxt.DI + di(rem);
		if(rem >= t[idx])	//compress
		{
			nxt = dp(idx + 1, rem - t[idx]);
			if(nxt.lectures < ans.lectures)
			{
				ans.lectures = nxt.lectures;
				ans.DI = nxt.DI;
			}
			else
				if(nxt.lectures == ans.lectures)
					ans.DI = Math.min(ans.DI, nxt.DI);
		}
		return memo[idx][rem] = ans;
	}
	
	static int di(int rem)
	{

		int di = 0;
		if(rem >= 1 && rem <= 10)
			di = -C;
		else
			if(rem > 10)
				di = (rem - 10) * (rem -10);
		return di;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0) break;
			if(k != 1)
				sb.append("\n");
			sb.append("Case "+k++).append(":\n");
			L = sc.nextInt();
			C = sc.nextInt();
			t = new int[N];
			for(int i = 0; i < N; i++) t[i] = sc.nextInt();
			
			memo = new Pair[N][L+1];
			Pair ans = dp(0, 0);
			sb.append("Minimum number of lectures: "+ans.lectures).append("\n");
			sb.append("Total dissatisfaction index: "+ans.DI).append("\n");
		}
		System.out.print(sb);
	}
	
	static class Pair
	{
		int lectures, DI;
		
		Pair(int x, int y)
		{
			lectures = x;
			DI = y;
		}
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
