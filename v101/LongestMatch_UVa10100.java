package v101;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class LongestMatch_UVa10100 {
	
	
	static ArrayList<String> getWords(String s)
	{
		ArrayList<String> ret = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); ++i)
			if(valid(s.charAt(i)))
				sb.append(s.charAt(i));
			else if(sb.length() != 0)
			{ 
				ret.add(sb.toString());
				sb = new StringBuilder();
			}
		if(sb.length() != 0)
			ret.add(sb.toString());
		return ret;
	}
	
	static boolean valid(char c)
	{
		return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9';
 	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		
		while(sc.ready())
		{
			String ss = sc.nextLine(), tt = sc.nextLine();
			if(ss.isEmpty() || tt.isEmpty())
				out.printf("%2d. Blank!\n", tc++);
			else
			{
				ArrayList<String> s = getWords(ss), t = getWords(tt);
				
				int[][] dp = new int[s.size() + 1][t.size() + 1];
				for(int i = s.size() - 1; i >= 0; --i)
					for(int j = t.size() - 1; j >= 0; --j)
						if(s.get(i).equals(t.get(j)))
							dp[i][j] = dp[i + 1][j + 1] + 1;
						else
							dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				out.printf("%2d. Length of longest match: %d\n", tc++, dp[0][0]);
			}
		}
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