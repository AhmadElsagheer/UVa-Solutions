package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Decoding_UVa11541 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			String s = sc.next();
			StringBuilder ans = new StringBuilder();
			for(int i = 0; i < s.length(); )
			{
				int j = i + 1;
				while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9')
					++j;
				int cnt = Integer.parseInt(s.substring(i + 1, j));
				while(cnt-->0)
					ans.append(s.charAt(i));
				i = j;
			}
			out.printf("Case %d: %s\n", t, ans.toString());
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