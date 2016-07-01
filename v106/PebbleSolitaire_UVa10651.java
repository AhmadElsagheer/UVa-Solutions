package v106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class PebbleSolitaire_UVa10651 {

	
	static int[] bitCount;
	static boolean[] noMove;
	
	
	static void pre()
	{
		bitCount = new int[1<<12];
		noMove = new boolean[1<<12];
		Arrays.fill(noMove, true);
		for(int i = 0; i < 12; i++)
			bitCount[1<<i] = 1;
		for(int i = 1; i < 1<<12; i++)
		{
			bitCount[i] = 1 + bitCount[i ^ (i & -i)];
			if(twoAdj(i)) noMove[i] = false;
		}
		noMove[(1<<12)-1] = true;	
	}
	
	static boolean twoAdj(int x)
	{
		for(int i = 0; i < 11; i++)
			if((x & (1<<i)) != 0 && (x & (1<<(i+1)))!= 0)
				return true;
		return false;
	}
	
	static int[] memo = new int[1<<12];
	
	static int dp(int msk)
	{
		if(noMove[msk]) return bitCount[msk];
		if(memo[msk] != -1) return memo[msk];
		
		int min = bitCount[msk];
		for(int i = 0; i < 11; i++)
			if((msk & (1<<i)) != 0 && (msk & (1<<(i+1)))!= 0)
			{
				if(i !=0 && (msk & (1<<(i-1))) == 0) min = Math.min(min, dp((msk | (1<<(i-1))) ^ (1<<(i)) ^ (1<<(i+1)) ));
				if(i !=10 && (msk & (1<<(i+2))) == 0) min = Math.min(min, dp((msk | (1<<(i+2))) ^ (1<<(i)) ^ (1<<(i+1)) ));
			}
		return memo[msk] = min;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		pre();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			String line = br.readLine();
			int msk = 0;
			for(int i = 0; i < 12; i++)
				if(line.charAt(i) == 'o') msk |= 1<<i;
			Arrays.fill(memo, -1);
			sb.append(dp(msk)+"\n");
		}
		System.out.print(sb);
	}
}
