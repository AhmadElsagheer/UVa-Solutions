package cp3_5;
import java.util.*;
import java.io.*;

public class CuttingSticks_UVa10003 {

	static final int UNCAL = -1;
	static final int INF = 1000000;
	static int[][] memo;
	static int[] cuts;
	static int N;
	
	public static int dp(int b, int e,int start, int end)
	{
		if(b >= e || start > end)
			return 0;
		if(memo[start][end]!=UNCAL)
			return memo[start][end];
		int min = INF;
		for(int i = start; i <= end; i++)
		{
			
			int cur = e - b;
			int partial1 = dp(b,cuts[i],start,i-1);
			int partial2 = dp(cuts[i],e,i+1,end);
			cur += partial1 + partial2;
			min = Math.min(min, cur);
		}
		if(min==INF)
			min = 0;
		return memo[start][end] = min;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int L = Integer.parseInt(br.readLine());
			if(L==0)
				break;
			N = Integer.parseInt(br.readLine());
			cuts = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N; i++)
				cuts[i] = Integer.parseInt(st.nextToken());
			memo = new int[N][N];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			int min = dp(0,L,0,N-1);
			
			sb.append("The minimum cutting is "+min+".\n");
		}
		System.out.print(sb);
	}
}
