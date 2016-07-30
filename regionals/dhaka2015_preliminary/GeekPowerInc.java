package regionals.dhaka2015_preliminary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GeekPowerInc {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int n = sc.nextInt();
			Pair[] powers = new Pair[n];
			for(int i = 0; i < n; ++i)
				powers[i] = new Pair(sc.nextInt(), sc.nextInt());
			Arrays.sort(powers);
			long ans = 0, count = 0, cur = 0;
			for(int i = n - 1; i >= 0; --i)
			{
				count += powers[i].k;
				cur = count * powers[i].p;
				ans = Math.max(ans, cur);
			}
			out.printf("Case %d: %d\n",t, ans);	
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>
	{
		int k, p;
		
		Pair(int a, int b) {k = a; p = b; }
		
		public int compareTo(Pair p) { return this.p - p.p; }
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
