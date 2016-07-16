package regionals.dhaka2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Owllen {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int[] f = new int[26];
			char[] s = sc.next().toCharArray();
			for(char c: s)
				f[c-'a']++;
			int ans = s.length;
			for(int x: f)
				ans = Math.min(ans, x);
			out.printf("Case %d: %d\n", t, ans);
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
	}
}
