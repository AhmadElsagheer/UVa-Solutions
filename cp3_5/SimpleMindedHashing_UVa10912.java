package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SimpleMindedHashing_UVa10912 {

	static final int UNCAL = -1;
	static int[][][] memo;
	static int N;
	static int dp(int idx, int minChar, int remS)
	{
		if(idx==N)
			if(remS==0)
				return 1;
			else
				return 0;
		if(remS<0 || minChar > 26)
			return 0;
		if(memo[idx][minChar][remS]!=UNCAL)
			return memo[idx][minChar][remS];
		int count = 0;
		for(int c = minChar; c <= 26; c++)
			count += dp(idx+1,c+1,remS-c);
		return memo[idx][minChar][remS] = count;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int k = 1;
		while(true)
		{
			StringTokenizer st= new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			int S = Integer.parseInt(st.nextToken());
			out.printf("Case %d: ",k++);
			if(N>26 || S > 676)
				out.println(0);
			else
			{
				memo = new int[N][30][S+1];
				for(int i = 0; i < N; i++)
					for(int j = 0; j < 30; j++)
						Arrays.fill(memo[i][j], UNCAL);
				out.println(dp(0,1,S));
			}
		}
		out.flush();
	}
}
