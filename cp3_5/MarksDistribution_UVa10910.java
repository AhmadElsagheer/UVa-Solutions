package cp3_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MarksDistribution_UVa10910 {

	static int[][] memo;
	static final int UNCAL = -1;
	static int N, P;
	
	public static int dp(int i, int t)
	{
		if(i==N-1)
			return 1;
		if(memo[i][t]!=UNCAL)
			return memo[i][t];
		int count = 0;int max = t - (N-i-1)*P;
		for(int k = P; k <= max;k++)
			count += dp(i+1,t - k);
		return memo[i][t] = count;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			memo = new int[N][T+1];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			out.println(dp(0,T));
		}
		out.flush();
		out.close();
	}
}
