package cp3_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Divisibility_UVa10036 {

	static final int UNCAL = -1;
	
	static int k;
	static int N;
	static int[] numbers;
	static int[][] memo;
	
	public static int dp(int i, int sumMod)
	{
		if(i==N)
			return sumMod%k == 0?1:0;
		
		if(memo[i][sumMod]!=UNCAL)
			return memo[i][sumMod];
		
		int pos = dp(i+1,fix(sumMod+numbers[i]));
		int neg = dp(i+1,fix(sumMod-numbers[i]));
		
		return memo[i][sumMod] = neg | pos;
	}
	
	public static int fix(int x)
	{
		return (x%k+k)%k;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		   
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				numbers[i] = Integer.parseInt(st.nextToken());
			memo = new int[N][k];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			
			if(dp(1,fix(numbers[0])%k)==1)
				sb.append("Divisible\n");
			else
				sb.append("Not divisible\n");
		}
		System.out.print(sb);
	}
}
