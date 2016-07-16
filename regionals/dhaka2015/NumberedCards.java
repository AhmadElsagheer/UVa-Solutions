package regionals.dhaka2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberedCards {

	static final int mod = (int)1e9 + 7;
	
	static int[][][] countMemo;
	static int[] number;
	static int digits;
	static int[] countNumbers;
	
	static int countNumbers(int low, int idx, int used)
	{
		if(idx == 9)
			return used == digits ? 1 : 0;
		if(countMemo[low][idx][used] != -1)
			return countMemo[low][idx][used];
		int ret = 0, maxDigit = number[idx];
	
		for(int i = 0, end = low == 1 ? 9 : maxDigit; i <= end; ++i)
			if((digits & 1<<i) != 0 || (i == 0 && used == 0))
			{	
				int nxt = used;
				if(i != 0 || nxt != 0)
					nxt |= 1<<i;
				ret = (ret + countNumbers(i < maxDigit ? 1 : low, idx + 1, nxt))%mod;
			}
		return countMemo[low][idx][used] = ret;
	}
	
	static void computeNumbers(int N)
	{
		number = new int[9];
		for(int i = 8; N > 0; --i, N /= 10)
			number[i] = N % 10;
		countNumbers = new int[1<<10];
		for(int i = 1; i < 1<<10; ++i)
		{
			digits = i;
			countMemo = new int[2][9][1<<10];
			for(int j = 0; j < 2; ++j)
				for(int k = 0; k < 9; ++k)
					Arrays.fill(countMemo[j][k], -1);
			countNumbers[i] = countNumbers(0, 0, 0);
		}
	}
	
	static int[][] subMemo;
	
	static int countSubsets(int subSize, int used)
	{
		if(subSize == 0)
			return 1;
		if(subMemo[subSize][used] != -1)
			return subMemo[subSize][used];
		int ret = 0;
		for(int i = 1; i < 1<<10; ++i)
			if((used & i) == 0)
				ret = (ret + mult(countNumbers[i], countSubsets(subSize - 1, used | i)))%mod;
		return subMemo[subSize][used] = ret;
	}
	
	static int mult(long x, long y) { return (int)((1l * x * y) % mod); }
	
	
	static int go(int N)
	{
		fac();
		computeNumbers(N);
		subMemo = new int[10][1<<10];
		for(int i = 0; i < 10; ++i)
			Arrays.fill(subMemo[i], -1);
		int ans = 0;
		for(int size = 1; size <= 9; ++size)
			ans = (ans + mult(countSubsets(size, 0), modPow(fac[size], mod - 2)))%mod;
		return ans;
	}
	
	static int[] fac;
	
	static void fac()
	{
		fac = new int[10];
		fac[0] = fac[1] = 1;
		for(int i = 2; i < 10; ++i)
			fac[i] = i * fac[i-1] % mod;
	}
	
	static int modPow(long b, int e)
	{
		long res = 1;
		while(e > 0)
		{
			if((e & 1) == 1)
				res = res * b % mod;
			b = b * b % mod;
			e >>= 1;
		}
		return (int)res;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
			out.printf("Case %d: %d\n", t, go(sc.nextInt()));
		out.flush();
		out.close();
	}
	
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;
		
		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }
		
		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();	
		}
		
		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
	}
}
