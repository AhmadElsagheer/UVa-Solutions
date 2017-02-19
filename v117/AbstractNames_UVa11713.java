package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AbstractNames_UVa11713 {

	static final String vows = "aeiuo";
	
	static boolean isVowel(char c) { return vows.indexOf(c) != -1; }
	
	static boolean equ(String x, String y)
	{
		if(x.length() != y.length())
			return false;
		for(int i = 0; i < x.length(); ++i)
			if((!isVowel(x.charAt(i)) || !isVowel(y.charAt(i))) && x.charAt(i) != y.charAt(i))
				return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
			out.println(equ(sc.next(), sc.next()) ? "Yes" : "No");
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