package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class ExactChange_UVa11517 {

	static final int UNCAL = -1, INF = 100000000;
	static int[][] memo;
	static int N,money, value[];
	
	public static int dp(int i, int curMoney)
	{
		if(curMoney >= money)
			return curMoney;
		if(i==N)
			return INF;
		if(memo[i][curMoney]!=UNCAL)
			return memo[i][curMoney];
		return memo[i][curMoney] = Math.min(dp(i+1,curMoney),dp(i+1,curMoney+value[i]));
	}
	
	public static int minCoins(int i, int curMoney)
	{
		if(curMoney == 0)
			return 0;
		if(curMoney < 0 || i == N)
			return INF;
		if(memo[i][curMoney]!=UNCAL)
			return memo[i][curMoney];
		return memo[i][curMoney] = Math.min(minCoins(i+1,curMoney), 1 + minCoins(i+1,curMoney-value[i]));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			money = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			value = new int[N];
			for(int i = 0; i < N; i++)
				value[i] = Integer.parseInt(br.readLine());
			memo = new int[N][money];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			money = dp(0,0);
			memo = new int[N][money+1];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			out.printf("%d %d\n",money,minCoins(0,money));
		}
		out.flush();
	}
}
