package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumSumOnATorus_UVa10827 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			String line = br.readLine();
			if(line.isEmpty())
			{
				tc++;
				continue;
			}
			int N = Integer.parseInt(new StringTokenizer(line).nextToken());
			int[][] cum = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
				{
					cum[i][j] = Integer.parseInt(st.nextToken());
					if(i > 0) cum[i][j] += cum[i-1][j];
					if(j > 0) cum[i][j] += cum[i][j-1];
					if(i > 0 && j > 0) cum[i][j] -= cum[i-1][j-1];
				}
			}
			
			int max = -100 * 75 * 75;
			for(int rl = 0; rl < N; rl++)
				for(int cl = 0; cl < N; cl++)
					for(int rh = rl; rh < N; rh++)
						for(int ch = cl; ch < N; ch++)
						{
							int cur = cum[rh][ch];
							if(rl > 0) cur -= cum[rl-1][ch];
							if(cl > 0) cur -= cum[rh][cl-1];
							if(rl > 0 && cl > 0) cur += cum[rl-1][cl-1];
							
							max = Math.max(max, cur);
							int v = cum[N-1][ch] - (cl > 0?cum[N-1][cl-1]:0) - cur;
							if(rh - rl + 1!= N)
								max = Math.max(max, v);
							int h = cum[rh][N-1] - (rl > 0?cum[rl-1][N-1]:0) - cur;
							if(ch - cl + 1!= N)
								max = Math.max(max, h);
							if(rh - rl + 1!= N && ch - cl + 1!= N)
								max = Math.max(max, cum[N-1][N-1] - cur - v - h);
						}
			sb.append(max+"\n");
		}
		System.out.print(sb);
	}
}
