package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ants_UVa10714 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			if(N==0)
			{
				sb.append("0 0\n");continue;
			}
			int min = 0;int max = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
			{
				if(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				int cur = Integer.parseInt(st.nextToken());
				min = Math.max(min, Math.min(cur, L - cur));
				max = Math.max(max, Math.max(cur, L - cur));
			}
			
			sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
}
