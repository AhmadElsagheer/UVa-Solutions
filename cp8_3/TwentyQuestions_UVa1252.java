package cp8_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwentyQuestions_UVa1252 {

	static int[][] memo;
	static int n, m, features[];
	
	static int dp(int questions, int answers)
	{
		if(memo[questions][answers] != -1)
			return memo[questions][answers];
		
		int remObjects = 0;
		for(int i = 0; i < n; ++i)
			if((questions & features[i]) == answers)
				remObjects++;
		if(remObjects <= 1)
			return memo[questions][answers] = 0;
		int min = m;
		for(int i = 0; i < m; ++i)
			if((questions & 1<<i) == 0)
				min = Math.min(min, 1 + Math.max(dp(questions | 1<<i, answers | 1<<i), dp(questions | 1<<i, answers)));
		return memo[questions][answers] = min;
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			m = sc.nextInt();
			n = sc.nextInt();
			if(n + m == 0)
				break;
			features = new int[n];
			for(int i = 0; i < n; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < m; ++j)
					features[i] |= line.charAt(j) - '0'<<j;
			}
			memo = new int[1<<m][1<<m];
			for(int i = 0; i < 1<<m; ++i)
				Arrays.fill(memo[i], -1);
			out.println(dp(0, 0));
		}
		
		out.flush();
		out.close();


	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}

		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}


