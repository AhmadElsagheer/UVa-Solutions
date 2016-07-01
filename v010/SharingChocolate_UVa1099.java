package v010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SharingChocolate_UVa1099 {

	static byte[][] memo;
	static int[] pieces;
	static int n;
	
	static void pre_pieceCount(int[] in)
	{
		pieces = new int[1<<n];
		for(int i = 0; i < n; i++)
			pieces[1<<i] = in[i];
		for(int i = 1; i < 1<<n; i++)
		{
			int last = i & -i;
			pieces[i] = pieces[last] + pieces[i^last];
		}
	}
	
	static int dp(int m, int x)	// x is always smaller than or equal to y
	{
		
		int msk = ((1<<n) - 1) ^ m;
		if((msk & (msk - 1)) == 0)	return 1;
		if(memo[msk][x] != - 1)	return memo[msk][x];
		
		int y = pieces[msk] / x, msk1 = (msk - 1) & msk, msk2 = msk ^ msk1;
		
		while(msk1 > msk2)
		{	
			if(pieces[msk1]%x == 0 && pieces[msk2]%x == 0 && dp(m | msk1, x) == 1 && dp(m | msk2, x) == 1)
				return memo[msk][x] = 1;
			if(pieces[msk1]%y == 0 && pieces[msk2]%y == 0 && dp(m | msk2, pieces[msk1]/y) == 1 && dp(m | msk1, pieces[msk2]/y) == 1)
				return memo[msk][x] = 1;

			msk1 = (msk1 - 1) & msk;
			msk2 = msk ^ msk1;				
		}
		return memo[msk][x] = 0;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int k = 1;
		while(true)
		{
			n = Integer.parseInt(br.readLine());
			if(n==0) break;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int[] in = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				in[i] = Integer.parseInt(st.nextToken());
			pre_pieceCount(in);
			memo = new byte[1<<n][Math.min(x, y) + 1];
			for(int i = 0; i < 1<<n; i++)
				Arrays.fill(memo[i], (byte)-1);
			sb.append("Case "+k+++": ");
			if(x * y != pieces[(1<<n)-1] || dp(0, Math.min(x, y)) == 0)
				sb.append("No\n");
			else
				sb.append("Yes\n");
		}
		
		System.out.print(sb);
	}
}
