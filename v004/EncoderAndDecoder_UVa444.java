package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EncoderAndDecoder_UVa444 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	

		while(sc.ready())
		{
			char[] s = sc.nextLine().toCharArray();
			if(s.length == 0)
			{
				out.println();
				continue;
			}
			StringBuilder sb = new StringBuilder();
			if(s[0] >= '0' && s[0] <= '9')
			{
				for(int i = s.length - 1; i >= 0; i -= 2)
					if(s[i] == '1')
						sb.append((char)((s[i] - '0') * 100 + (s[i - 1] - '0') * 10 + (s[i-- - 2] - '0')));
					else
						sb.append((char)((s[i] - '0') * 10 + (s[i - 1] - '0')));
			}
			else
			{
				for(char c: s)
					sb.append((int)c);
				sb.reverse();
			}
			out.println(sb);				
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