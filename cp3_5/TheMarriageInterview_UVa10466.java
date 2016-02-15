package cp3_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;



public class TheMarriageInterview_UVa10466 {

	static BigInteger[][] memo;
	static BigInteger counter;
	
	static BigInteger trib(int n,int back)
	{
		if(n<=1) return BigInteger.ONE;	
		if(memo[n][back]!=null)
			return memo[n][back];
		BigInteger sum = BigInteger.ONE;
		for(int i=1;i<=back;i++)
			sum  = sum.add(trib(n-i,back));
		return memo[n][back] = sum;
	}
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		memo = new BigInteger[70][70];
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			if(n>61 || back > 60)
				break;
			sb.append("Case "+k+++": ").append(trib(n,back)).append("\n");
		}
		System.out.print(sb);
	}
	
}
