package cp3_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Bars_UVa12455 {

	static int p, len[], memo[][];
	
	static int dp(int idx, int remL)
	{
		if(remL == 0)
			return 1;
		if(remL < 0 || idx == p)
			return 0;
		if(memo[idx][remL] != -1)
			return memo[idx][remL];
		
		int nxt = dp(idx + 1, remL - len[idx]);
		if(nxt == 0)
			nxt = dp(idx + 1, remL);
		
		return memo[idx][remL] = nxt;
	}
	
	public static void main(String[] args) throws IOException {


		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int L = sc.nextInt();
			p = sc.nextInt();
			len = new int[p];
			memo = new int[p][L + 1];
			for(int i = 0; i < p; ++i)
			{
				len[i] = sc.nextInt();
				Arrays.fill(memo[i], -1);
			}
			
			out.println(dp(0, L) == 1 ? "YES" : "NO");
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
