package v001;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class StringComputer_UVa164 {

	static char[] s, t;
	static int[][] memo;
	
	static int dp(int i, int j)
	{
		if(i == s.length)
			return t.length - j;
		if(j == t.length)
			return s.length - i;
		if(memo[i][j] != -1)
			return memo[i][j];
		int ret;
		if(s[i] == t[j])
			ret = dp(i + 1, j + 1);
		else
			ret = 1 + Math.min(dp(i + 1, j + 1), Math.min(dp(i, j + 1), dp(i + 1, j)));
		return memo[i][j] = ret;
	}
	
	static StringBuilder sb;
	
	static void print(int i, int j)
	{
		if(j == t.length)
		{
			while(i < s.length)
				sb.append(String.format("D%c%02d", s[i++], j + 1));
			return;
		}
		if(i >= s.length)
		{
			sb.append(String.format("I%c%02d", t[j], j + 1));
			print(i + 1, j + 1);
			return;
		}
		if(s[i] == t[j])
			print(i + 1, j + 1);
		else
		{
			int opt = dp(i, j);
			if(dp(i + 1, j + 1) + 1 == opt)
			{	
				sb.append(String.format("C%c%02d", t[j], j + 1));
				print(i + 1, j + 1);
			}
			else if(dp(i, j + 1) + 1 == opt)
			{
				sb.append(String.format("I%c%02d", t[j], j + 1));
				print(i, j + 1);
			}
			else
			{
				sb.append(String.format("D%c%02d", s[i], j + 1));
				print(i + 1, j);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		sb = new StringBuilder();
		while(true)
		{
			s = sc.next().toCharArray();
			if(s[0] == '#')
				break;
			t = sc.next().toCharArray();
			memo = new int[s.length][t.length];
			for(int i = 0; i < s.length; ++i)
				Arrays.fill(memo[i], -1);
			print(0, 0);
			sb.append("E\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

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

		public boolean ready() throws IOException {return br.ready();}


	}
}