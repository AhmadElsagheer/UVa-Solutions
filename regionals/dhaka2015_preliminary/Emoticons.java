package regionals.dhaka2015_preliminary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Emoticons {

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			char[] s = sc.next().toCharArray();
		
			int ans = 0, first = 0, pair = 0, unmatch_ = 0;
			for(char c: s)
				if(c == '^')
				{
					if(pair > 0) { --pair; ++ans; }
					else if(unmatch_ > 0 && ans > 0)
						{
							--unmatch_;
							++pair;
						}
					else
						++first;
				}
				else
				{
					if(first > 0) { ++pair; --first; }
					else if(unmatch_ < ans)
						++unmatch_;
				}
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

		String nextLine() throws IOException { return br.readLine(); }
	}
}
