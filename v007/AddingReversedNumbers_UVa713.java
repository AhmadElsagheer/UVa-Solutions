package v007;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
 
public class AddingReversedNumbers_UVa713 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String s1 = sc.next(), s2 = sc.next();
			int[] x = new int[2001], y = new int[2001];
			for(int i = 0; i < s1.length(); ++i)
				x[i] = s1.charAt(i) - '0';
			for(int i = 0; i < s2.length(); ++i)
				y[i] = s2.charAt(i) - '0';
			StringBuilder ans = new StringBuilder();
			for(int i = 0, c = 0; i <= 2000; ++i)
			{
				int sum = x[i] + y[i] + c;
				ans.append(sum % 10);
				c = sum / 10;
			}
			int start = -1, end = ans.length();
			while(ans.charAt(++start) == '0');
			while(ans.charAt(--end) == '0');
			out.println(ans.substring(start, end + 1));
		}
		out.flush();
		out.close();
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
 
 
	}
} 