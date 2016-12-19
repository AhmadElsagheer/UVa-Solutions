package v111;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
 
public class BeEfficient_UVa11155 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int A = sc.nextInt(), B = sc.nextInt(), C = sc.nextInt(), M = sc.nextInt(), N = sc.nextInt();
			int[] x = new int[N];
			x[0] = A;
			for(int i = 1; i < N; ++i)
				x[i] = (x[i - 1] * B + C) % M + 1;
			int[] cnt = new int[M];
			int offset = 0, ans = 0;
			for(int i = N - 1; i >= 0; --i)
			{
				x[i] %= M;
				cnt[offset]++;
				offset = (offset - x[i] + M) % M;
				ans += cnt[offset];
			}
			out.printf("Case %d: %d\n", t, ans);
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