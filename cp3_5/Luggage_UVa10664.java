package cp3_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Luggage_UVa10664 {

	static final int UNCAL = -1;
	static int N, weight[], memo[][];
	
	public static int dp(int suitcase, int remW)
	{
		if(remW==0)
			return 1;
		if(suitcase==N || remW < 0)
			return 0;
		
		if(memo[suitcase][remW]!=UNCAL)
			return memo[suitcase][remW];
		
		return memo[suitcase][remW] = dp(suitcase+1,remW) | dp(suitcase+1,remW-weight[suitcase]);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC  = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			int totalWeight = 0;
			weight = new int[20]; N = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens())
				totalWeight += weight[N++] = Integer.parseInt(st.nextToken());
				
			memo = new int[N][totalWeight+1];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			if(totalWeight%2==1 || dp(0,totalWeight/2) == 0)
				sb.append("NO\n");
			else
				sb.append("YES\n");
			
			
			
			
		}
		System.out.print(sb);
	}
}

