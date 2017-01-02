package v108;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
 
public class BeatTheSpread_UVa10812 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			long s = sc.nextInt(), d = sc.nextInt();
			if(s < d || (s - d & 1) == 1)
				out.println("impossible");
			else
				out.printf("%d %d\n", (s - d) / 2 + d, (s - d) / 2);
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