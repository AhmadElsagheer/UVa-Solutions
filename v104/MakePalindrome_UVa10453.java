package v104;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class MakePalindrome_UVa10453 {
	
	static char[] s;
	static int[][] memo;
	
	static int dp(int i, int j)
	{
		if(i >= j)
			return 0;
		if(memo[i][j] != -1)
			return memo[i][j];
		if(s[i] == s[j])
			return dp(i + 1, j - 1);
		return memo[i][j] = 1 + Math.min(dp(i + 1, j), dp(i, j - 1));
	}
	
	static StringBuilder sb = new StringBuilder();
	static Stack<Character> end = new Stack<Character>();
	
	static void print(int i, int j)
	{
		if(i > j)
			return;
		if(i == j)
		{
			sb.append(s[i]);
			return;
		}
		if(s[i] == s[j])
		{
			sb.append(s[i]);
			end.push(s[i]);
			print(i + 1, j - 1);
			return;
		}
		if(dp(i, j) == 1 + dp(i + 1, j))
		{
			sb.append(s[i]);
			end.push(s[i]);
			print(i + 1, j);
		}
		else
		{
			sb.append(s[j]);
			end.push(s[j]);
			print(i, j - 1);
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			s = sc.next().toCharArray();
			memo = new int[s.length][s.length];
			for(int i = 0; i < s.length; ++i)
				Arrays.fill(memo[i], -1);
			sb.append(dp(0, s.length - 1) + " ");
			print(0, s.length - 1);
			while(!end.isEmpty())
				sb.append(end.pop());
			sb.append("\n");
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