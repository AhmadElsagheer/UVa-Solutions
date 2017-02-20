package v124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ShortStoryCompetition_UVa12482 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int lines = 0, remC = 0;
			st = new StringTokenizer(br.readLine());
			while(N-->0)
			{
				int cur = st.nextToken().length();
				if(cur > remC)
				{
					remC = C;
					lines++;
				}
				remC -= cur + 1;
			}
			sb.append((lines+L-1)/L).append("\n");
		}
		System.out.print(sb);
	}
}
