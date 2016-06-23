package cp8_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACORN_UVa4106 {

	static int[] memo;
	static int[][] acorns;
	static int t,f,H;
	
	public static int dp()
	{
		for(int i = H - 1; i >= 0; i--)
		{
			for(int j = 0; j < t; j++)
			{
				acorns[j][i] += Math.max(acorns[j][i+1], i + f <= H?memo[i+f]:0);
				memo[i] = Math.max(memo[i], acorns[j][i]);
			}
		}
		return memo[0];
			
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC =Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			f = Integer.parseInt(st.nextToken());
			acorns  = new int[t][H+1];
			for(int i = 0; i < t; i++)
			{
				st = new StringTokenizer(br.readLine());
				int count = Integer.parseInt(st.nextToken());
				while(count-->0)
					acorns[i][Integer.parseInt(st.nextToken())]++;
			}
			memo = new int[H+1];
			int max = dp();
			sb.append(max+"\n");
		}
		System.out.print(sb);
	}
}
