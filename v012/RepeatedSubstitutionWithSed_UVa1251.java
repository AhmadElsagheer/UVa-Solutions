package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class RepeatedSubstitutionWithSed_UVa1251 {

	static final int INF = (int)1e9;
	static String t;
	static String[] alpha, beta;
	static int n;

	static int bt(String cur)
	{
		if(cur.equals(t))
			return 0;
		if(cur.length() > t.length())
			return INF;

		int ans = INF;
		for(int i = 0; i < n; ++i)
		{
			String nxt = replace(cur, i);
			if(nxt != null)
				ans = Math.min(ans, 1 + bt(nxt));
		}
		return ans;
	}

	static String replace(String x, int idx)
	{
		int reps = 0, len = alpha[idx].length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < x.length(); ++i)
			if(i + len <= x.length() && alpha[idx].equals(x.substring(i, i + len)))
			{
				++reps;
				sb.append(beta[idx]);
				i += alpha[idx].length() - 1;
			}
			else
				sb.append(x.charAt(i));

		if(reps == 0)
			return null;
		return sb.toString();
	}


	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			n = sc.nextInt();
			if(n == 0)
				break;
			alpha = new String[n];
			beta = new String[n];
			for(int i = 0; i < n; ++i)
			{
				alpha[i] = sc.next();
				beta[i] = sc.next();
			}
			String s = sc.next();
			t = sc.next();
			int ans = bt(s);
			if(ans == INF)
				ans = -1;
			out.println(ans);
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}