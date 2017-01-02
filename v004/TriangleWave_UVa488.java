package v004;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
 
public class TriangleWave_UVa488 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int A = sc.nextInt(), F = sc.nextInt();
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i <= A; ++i)
			{
				for(int j = 0; j < i; ++j)
					sb.append(i);
				sb.append("\n");
			}
			for(int i = A - 1; i > 0; --i)
			{
				for(int j = 0; j < i; ++j)
					sb.append(i);
				sb.append("\n");
			}
			String s = sb.toString();
			while(F-->0)
			{
				out.printf(s);
				if(F != 0)
					out.println();
			}
			if(tc != 0)
				out.println();
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