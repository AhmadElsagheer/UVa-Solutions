package v110;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class AndysSecondDicitionary_UVa11062 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		StringBuilder sb = new StringBuilder();
	
		while(sc.ready())
		{
			String s = sc.nextLine().toLowerCase();
			if(s.isEmpty())
				continue;
			if(s.charAt(s.length() - 1) == '-')
				s = s.substring(0, s.length() - 1);
			else
				s += " ";
			sb.append(s);
		}
		
		TreeSet<String> dic = new TreeSet<String>();
		StringBuilder w = new StringBuilder();
		for(int i = 0; i < sb.length(); ++i)
		{
			char c = sb.charAt(i);
			if(c >= 'a' && c <= 'z' || c == '-')
				w.append(c);
			else
			{
				if(w.length() != 0)
					dic.add(w.toString());
				w = new StringBuilder();
			}
		}
		
		if(w.length() != 0)
			dic.add(w.toString());
		for(String s: dic)
			out.println(s);
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