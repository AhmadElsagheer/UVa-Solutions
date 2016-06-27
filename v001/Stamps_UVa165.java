package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Stamps_UVa165 {

	static int[] cur, sol;
	static int h, k, max;
			
	static int findMaxNom(int lst)
	{
		int maxNom = cur[lst] * h;
		int[] dp = new int[maxNom + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 0; i <= maxNom; ++i)
		{
			if(dp[i] > h)
				return i - 1;
			for(int j = 0; j <= lst; ++j)
				if(cur[j] + i <= maxNom)
					dp[cur[j] + i] = Math.min(dp[cur[j] + i], dp[i] + 1);				
		}
		return maxNom;
	}
	
	static void go(int idx, int lstNom, int maxNom)
	{
		if(idx == k)
		{
			if(maxNom > max)
			{
				max = maxNom;
				for(int i = 0; i < k; ++i)
					sol[i] = cur[i];
			}
			return;
		}
		
		for(int i = lstNom + 1; i <= maxNom + 1; ++i)
		{
			cur[idx] = i;
			go(idx + 1, i, findMaxNom(idx));
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			h = sc.nextInt();
			if(h == 0)
				break;
			k = sc.nextInt();
			max = 0;
			sol = new int[k];
			cur = new int[k];
			go(0, 0, 0);
			for(int x: sol)
				out.printf("%3d", x);
			out.printf(" ->%3d\n", max);
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}