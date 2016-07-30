package regionals.dhaka2015_preliminary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SearchTheKhoj {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt();
			String[] d = new String[n];
			for(int i = 0; i < n; ++i)
				d[i] = sc.next();
			String s = sc.next();
			StringBuilder ans = new StringBuilder();
			for(String ss: d)
				if(getDiff(ss, s) <= 1)
					ans.append(ss).append("\n");
			out.printf("Case %d:\n%s", t, ans.toString());
		}
		
		out.flush();
		out.close();
	}
	
	static int getDiff(String s, String t)
	{
		int ans = 0;
		for(int i = 0; i < s.length(); ++i)
			if(s.charAt(i) != t.charAt(i))
				++ans;
		return ans;
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
