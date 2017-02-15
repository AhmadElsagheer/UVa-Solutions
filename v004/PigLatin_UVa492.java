package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PigLatin_UVa492 {

	/*
	 * Presentation error occurs for ending last line (which is weird)
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		StringBuilder ans = new StringBuilder();

		String s = "";
		while(true)
		{
			int x = br.read();
			if(x == -1)
			{
				ans.append(get(s));
				break;
			}
			char c = (char)x;
			if(!Character.isLetter(c))
			{
				ans.append(get(s));
				ans.append(c);
				s = "";
			}
			else
				s += c;
		}
		out.print(ans);
		out.flush();
		out.close();
	}

	static String vows = "aeiuoAEIUO";
	
	static String get(String s)
	{
		if(!s.isEmpty())
		{
			if(isVowel(s.charAt(0)))
				return s + "ay";
			else
				return s.substring(1) + s.charAt(0) + "ay";
		}
		return "";
	}

	static boolean isVowel(char c)
	{
		for(int i = 0; i < vows.length(); ++i)
			if(vows.charAt(i) == c)
				return true;
		return false;
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();} 
	}
} 