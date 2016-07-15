package v118;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Abnormal89s_UVa11888 {


	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[] s = sc.next().toCharArray();
			boolean[] prefixPal = prefixPalindromes(s);
			reverse(s);
			boolean[] suffixPal = prefixPalindromes(s);
			if(isAlindrome(prefixPal, suffixPal, s.length))
				out.println("alindrome");
			else if(prefixPal[s.length])
				out.println("palindrome");
			else
				out.println("simple");
		}
		
		out.flush();
		out.close();
	}
	
	static boolean isAlindrome(boolean[] x, boolean[] y, int n)
	{
		for(int i = 1; i < n; ++i)
			if(x[i] && y[n-i])
				return true;
		return false;
	}
	
	static void reverse(char[] s)
	{
		for(int i = 0; i < s.length / 2; ++i)
		{
			char tmp = s[i];
			s[i] = s[s.length - i - 1];
			s[s.length - i - 1] = tmp;
		}
	}
	
	static boolean[] prefixPalindromes(char[] t)
	{
		int n = t.length * 2 + 1;
		char[] s = new char[n];
		for(int i = 0; i < t.length; ++i)
			s[i] = s[n - i - 1] = t[i];
		s[t.length] = '$';
		
		int pi[] = new int[n];
		for(int i = 1, j = 0; i < n; ++i)
		{
			while(j > 0 && s[i] != s[j])
				j = pi[j-1];
			if(s[i] == s[j])
				++j;
			pi[i] = j;	
		}
		int j = n - 1;
		boolean[] ret = new boolean[t.length + 1];
		while(j > 0)
		{
			ret[pi[j]] = true;
			j = pi[j-1];
		}
		return ret;
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