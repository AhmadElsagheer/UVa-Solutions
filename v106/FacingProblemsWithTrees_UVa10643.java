package v106;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class FacingProblemsWithTrees_UVa10643 {
	
	static BigInteger[] memo1[], memo2;
	
	
	static BigInteger dp1(int edges, int even)
	{
		if(edges == 0)
			return even == 0 ? BigInteger.ONE : BigInteger.ZERO;
		if(memo1[edges][even] != null)
			return memo1[edges][even];
		
		BigInteger ret = BigInteger.ZERO;
		for(int i = 0; i <= edges - 1; i += 2)
			ret = ret.add(dp1(edges - i - 1, even ^ 1).multiply(dp2(i)));
		return memo1[edges][even] = ret;
	}
	
	static BigInteger dp2(int edges)
	{
		if(edges == 0)
			return BigInteger.ONE;
		if(memo2[edges] != null)
			return memo2[edges];
		BigInteger ret = BigInteger.ZERO;
		for(int i = 0; i <= edges - 2; i += 2)
			ret = ret.add(dp2(i).multiply(dp2(edges - 2 - i)));
		return memo2[edges] = ret;
	}

	public static void main(String[] args) throws IOException {

		//		Scanner sc = new Scanner(new FileReader("rhymes.in"));
		//		PrintWriter out = new PrintWriter("bomb.out");
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		memo1 = new BigInteger[501][2];
		memo2 = new BigInteger[501];
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++ t)
		{
			int m = sc.nextInt();
			out.printf("Case %d: %d\n", t, dp1(m, 0));
		}
		
		out.flush();
		out.close();


	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}

		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}


