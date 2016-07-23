package regionals.latinAmerica2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DAsinDaedalus {

	static final int[] C =  {1, 10, 100, 1000, 10000} ;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			int N = sc.nextInt(), M = sc.nextInt();
			int ans = 0;
			while(M-->0)
			{
				int B = sc.nextInt();
				int x = sc.nextInt(), y = 0;
				for(int i = 0; i < N - 1; ++i)
					y += sc.nextInt();
				int s1 = x + y <= B ? x : 0;
				int s2 = s1;
				for(int i = 0; i < 5; ++i)
					s2 = Math.max(s2, C[i] + y <= B ? C[i] : 0);
				ans += s2 - s1;
			}
			out.println(ans);
		}
		out.flush();
		out.close();
		
	}
	
	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;
		
		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }
		
		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
		
		boolean ready() throws IOException { return br.ready(); }
	}
}
