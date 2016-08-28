package v005;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class NestingABunchOfBrackets_UVa551 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			char[] t = sc.nextLine().toCharArray();
			Stack<Character> s = new Stack<Character>();
			int i = 0, j = 1;
			boolean ok = true;
			while(i < t.length)
			{
				char c = t[i];
				if(c == '[' || c == '{' || c == '<' || c == '(')
				{
					if(c == '(' && i < t.length - 1 && t[i+1] == '*')
					{
						c = '*'; 
						++i;
					}
					s.push(c);
				}
				else if(c == ']' || c == '}' || c == ')' || c == '>' || (c == '*' && i < t.length - 1 && t[i+1] == ')'))
				{
					if(s.isEmpty()) { ok = false; break; }
					
					if(c == '*')
						++i;
					char cc = s.pop();
					if(c == ']' && cc != '[' || c == '}' && cc != '{' || c == ')' && cc != '(' || c == '>' && cc != '<' || c == '*' && cc != '*')
					{
						ok = false;
						break;
					}
					
				}
				
				++i; ++j;
			}
			if(!s.isEmpty())
				ok = false;
			
			out.println(ok ? "YES" : "NO " + j);
		}
		
		out.flush();
		out.close();
	}
	

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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