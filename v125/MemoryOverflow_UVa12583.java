package v125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MemoryOverflow_UVa12583 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for(int t = 1; t <= tc; t++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			String ppl = st.nextToken();
			int[] last = new int[26];
			Arrays.fill(last, -50000);
			int ans = 0;
			for(int i = 0; i < n; i++)
			{
				int p = ppl.charAt(i) - 'A';
				if(i - k <= last[p])
					ans++;
				last[p] = i;
			}
			sb.append("Case "+t+": "+ans+"\n");
		}
		System.out.print(sb);
	}
}
