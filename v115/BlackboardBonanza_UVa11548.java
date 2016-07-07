package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BlackboardBonanza_UVa11548 {

	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int ans = 0, n = sc.nextInt();
			String[] words = new String[n];
			for(int i = 0; i < n; ++i)
				words[i] = sc.next();
			for(int i = 0; i < n; ++i)
				for(int j = i + 1; j < n; ++j)
					ans = Math.max(ans, maxMatches(words[i], words[j]));
			out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static int maxMatches(String x, String y)
	{
		int max = 0;
		int lenX = x.length(), lenY = y.length();
		for(int i = 0; i < lenX; ++i)
			for(int j = 0, k = i, cur = 0; j < lenY; ++j, ++k)
			{
				if(k == lenX) { k = 0; cur = 0; }
				if(x.charAt(k) == y.charAt(j))
						++cur;
				max = Math.max(max, cur);
			}
		return max;
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
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}
	}
}