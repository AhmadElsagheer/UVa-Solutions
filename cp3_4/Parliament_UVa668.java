package cp3_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Parliament_UVa668 {

	static BigInteger[][] memo;
	static final int UNCAL = -1;
	static int N;
	static PrintWriter out = new PrintWriter(System.out);
	
	public static BigInteger dp(int n, int curSum)
	{
		if(curSum==N)
			return BigInteger.ONE;
		if(curSum>N || n > N)
			return BigInteger.ZERO;
		if(memo[n][curSum]!=null)
			return memo[n][curSum];
		
		return memo[n][curSum] = dp(n+1,curSum).max(BigInteger.valueOf(n).multiply(dp(n+1,curSum+n)));
	}
	
	public static void print(int n, int curSum)
	{
		if(curSum>N || n > N)
			return;
		BigInteger optimal = dp(n,curSum);
		if(optimal.equals(dp(n+1,curSum)))
			print(n+1,curSum);
		else
		{
			out.print(n+(curSum+n==N?"\n":" "));
			print(n+1,curSum+n);
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		while(TC-->0)
		{
			br.readLine();
			N = Integer.parseInt(br.readLine());
			memo = new BigInteger[1001][1001];
			print(1,0);
			if(TC!=0)
				out.println();
		}
		
		
		out.flush();
	}
}
