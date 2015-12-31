package cp3_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class KnuthsPermutation_UVa10063 {
	
	static String s;
	static StringBuilder sb;
	
	static void generate(int idx, String ans)
	{
	
		if(idx == s.length())
			sb.append(ans).append("\n");
		else
			for(int i = 0; i <= ans.length(); ++i)
				generate(idx + 1, ans.substring(0, i) + s.charAt(idx) + ans.substring(i));			
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			s = sc.next();
			sb = new StringBuilder();
			generate(0, "");
			out.print(sb);
		}
		out.flush();
		
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
