package v104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HomerSimpson_UVa10465 {

	static final int INF = 1000000;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int[] memo = new int[t+1];
			memo[0] = 0;
			for(int i = 1; i <= t; i++)
			{
				int x = -INF, y = -INF;
				if(i-n>=0)
					x = 1 + memo[i-n];
				if(i-m>=0)
					y = 1 + memo[i-m];
				memo[i] = Math.max(x, y);
			}
			int i;
			for(i = t; memo[i] < 0; i--);
			out.println(memo[i]+(i==t?"":(" "+(t-i))));
		}
		out.flush();
		out.close();
	}
}
