package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KibblesnBitsnBitsnBits_UVa446 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int n = sc.nextInt();
		while(n-->0)
		{
			int x, y;
			String b1 = Integer.toBinaryString(x = Integer.parseInt(sc.next(), 16));
			char c = sc.next().charAt(0);
			String b2 = Integer.toBinaryString(y = Integer.parseInt(sc.next(), 16));
			while(b1.length() < 13)
				b1 = "0" + b1;
			while(b2.length() < 13)
				b2 = "0" + b2;
			out.printf("%s %c %s = %d\n", b1, c, b2, c == '+' ? x + y : x - y);
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