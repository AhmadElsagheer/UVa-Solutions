package v004;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
 
public class MarevlousMazes_UVa490 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean first = true;
		while(sc.ready())
		{
			if(sc.nextEmpty())
				continue;
			if(first)
				first = false;
			else
				out.println();
			StringBuilder ans = new StringBuilder();
			int f = 0;
			do
			{
				String s = sc.next();
				for(int i = 0; i < s.length(); ++i)
					if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
						f += s.charAt(i) - '0';
					else
					{
						add(ans, f, s.charAt(i));
						f = 0;
					}
				add(ans, 0, '!');
				f = 0;
			}while(!sc.nextEmpty());
			out.print(ans);
		}
		out.flush();
		out.close();
	}
	
	static void add(StringBuilder ans, int f, char c)
	{
		if(c == '!')
			ans.append('\n');
		while(f > 0)
		{
			--f;
			if(c == 'b')
				ans.append(" ");
			else
				ans.append(c);
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;
 
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader s){	br = new BufferedReader(s);}
 
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
			return Double.parseDouble(next());
		}
 
		public boolean ready() throws IOException {return br.ready();}
		
		boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			if(s == null)
				return true;
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
 
 
	}
} 