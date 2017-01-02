package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
 
public class RotatingSentences_UVa490 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		ArrayList<String> lines = new ArrayList<>();
		int len = 0;
		String s;
		while(sc.ready())
		{
			lines.add(s = sc.nextLine());
			len = Math.max(len, s.length());
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < len; ++i)
		{
			for(int j = lines.size() - 1; j >= 0; --j)
			{
				s = lines.get(j);
				if(i >= s.length())
					sb.append(" ");
				else
					sb.append(s.charAt(i));
			}
			sb.append("\n");
		}
		out.print(sb);
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