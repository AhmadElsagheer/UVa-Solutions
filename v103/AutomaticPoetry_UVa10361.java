package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AutomaticPoetry_UVa10361 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n = sc.nextInt();
		while(n-->0)
		{
			String s1 = sc.nextLine(), s2 = sc.nextLine();
			String[] s = new String[5];
			Arrays.fill(s, "");
			for(int i = 0, j = 0; i < s1.length(); ++i)
				if(s1.charAt(i) == '<' || s1.charAt(i) == '>')
					++j;
				else
					s[j] += s1.charAt(i);
			out.println(s[0] + s[1] + s[2] + s[3] + s[4]);
			out.println(s2.substring(0, s2.length() - 3) + s[3] + s[2] + s[1] + s[4]);
		}
		out.flush();
		out.close();
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