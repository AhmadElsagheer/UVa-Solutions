package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InjuredQueenProblem_UVa10401 {
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			if(st.countTokens()==0)
				continue;
			String line = st.nextToken();
			int n = line.length();
			int[] row = new int[n];

				for(int i = 0; i < n; i++)
					if(line.charAt(i)=='?')
						row[i] = -1;
					else
						if(line.charAt(i) >= '0' && line.charAt(i) <= '9')
							row[i] = line.charAt(i) - '0' - 1;
						else
							row[i] = line.charAt(i) - 'A' + 9;
		
				long[][] dp = new long[n][n];
				if(row[0]==-1)
					for(int i = 0; i < n; i++)
						dp[0][i] = 1;
				else
					dp[0][row[0]] = 1;
				for(int j = 1; j < n; j++)
					for(int i = 0; i < n; i++)
						if(row[j] == -1 || row[j] == i)
							for(int k = 0; k < n; k++)
								if(Math.abs(k-i) > 1)
									dp[j][i] += dp[j-1][k];
				long ways = 0;
				for(int i = 0; i < n; i++)
					ways += dp[n-1][i];
				sb.append(ways+"\n");			
		}
		System.out.print(sb);
		
		
	}
}
