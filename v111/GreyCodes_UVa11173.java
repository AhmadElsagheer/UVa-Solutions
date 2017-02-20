package v111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreyCodes_UVa11173 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()) + 1;
			int k = Integer.parseInt(st.nextToken());
			int res = 0;
			while(n-->1)
				if(k >= 1<<n-1)
				{
					res += 1<<(n-1);
					k = (1<<n) - k - 1; 
				}
				
			sb.append(res+"\n");		
			
		}
		System.out.print(sb);
	}
}
