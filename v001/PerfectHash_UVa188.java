package v001;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PerfectHash_UVa188 {

	static int brute(int[] w)
	{
		int n = w.length;
		int cur = 1;
		while(true)
		{
			int nxt = -1;
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
					if((cur/w[i])%n == (cur/w[j])%n)
						nxt = Math.max(nxt, Math.min((cur/w[i] + 1)*w[i], (cur/w[j] + 1)*w[j]));
			if(nxt == -1)
				break;
			else
				cur = nxt;
		}
		return cur;
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			String line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			
			int[] w = new int[st.countTokens()];
			for(int i = 0; i < w.length; i++)
			{
				String word = st.nextToken();
				int ww = 0;
				for(int j = 0; j < word.length(); j++)
					ww = (ww << 5) + word.charAt(j) - 'a' + 1;
				w[i] = ww;
			}
			
			out.format("%s\n%d\n\n", line, brute(w));
		}
		out.flush();
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
