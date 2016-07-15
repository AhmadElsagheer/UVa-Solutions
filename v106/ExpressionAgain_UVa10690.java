package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ExpressionAgain_UVa10690 {

	static final int OFFSET = 2500, INF = Integer.MAX_VALUE;

	static int v(int x) { return x + OFFSET; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);


		while(sc.ready())
		{
			String ss = sc.nextLine();
			if(ss.isEmpty())
				continue;
			StringTokenizer st = new StringTokenizer(ss);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int total = 0;
			int[] a = new int[N + M];
			for(int i = 0; i < N + M; ++i)
				total += a[i] = sc.nextInt();

			boolean[][] can = new boolean[N + 1][OFFSET<<1|1];
			can[0][v(0)] = true;
			for(int i = 0; i < N + M; ++i)
			{
				int x = a[i];
				for(int j = Math.min(i, N - 1); j >= 0; --j)
					for(int s = -OFFSET; s <= OFFSET; ++s)
						if(can[j][v(s)])
							can[j+1][v(s + x)] = true;
			}

			int min = INF, max = -INF;
			for(int s = -OFFSET; s <= OFFSET; ++s)
				if(can[N][v(s)])
				{
					min = Math.min(min, s * (total - s));
					max = Math.max(max, s * (total - s));
				}
			out.println(max + " " + min);
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

	}
}