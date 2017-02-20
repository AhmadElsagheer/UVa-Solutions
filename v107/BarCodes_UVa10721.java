package v107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BarCodes_UVa10721 {

	
	static int m, k;
	static long[][] memo;
	static final int UNCAL = -1;
	
	public static long dp(int i, int n)
	{
		if(n <= 0)
			return 0;
		if(i==k-1)
			return n<=m?1:0;
		if(memo[i][n]!=UNCAL)
			return memo[i][n];
				
		long count = 0;
		for(int j = 1; j <= m; j++)
			count += dp(i+1,n-j);
		return memo[i][n] = count;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			memo = new long[k][n+1];
			for(int i = 0; i < k; i++)
				Arrays.fill(memo[i], UNCAL);
			long sol = dp(0,n);
			out.println(sol);
		}
		out.flush();
		out.close();
		
	}
}
