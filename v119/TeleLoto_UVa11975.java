package v119;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TeleLoto_UVa11975 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int N = sc.nextInt(), L = sc.nextInt();
			int[] indexOf = new int[76];
			Arrays.fill(indexOf, 100);
			for(int i = 1; i <= N; ++i)
				indexOf[sc.nextInt()] = i;
			int[] V = new int[4];
			for(int i = 0; i < 4; ++i)
				V[i] = sc.nextInt();
			out.printf("Case %d:\n", t);
			while(L-->0)
			{
				boolean[] win = new boolean[4];
				Arrays.fill(win, true);
				for(int i = 0; i < 5; ++i)
					for(int j = 0; j < 5; ++j)
					{
						int idx = indexOf[sc.nextInt()];

						if(i == 2)
							win[1] &= idx <= 40;
						if(i == j || i + j == 4)
						{
							win[2] &= idx <= 45;
							if(i == 0 || i == 4)
								win[0] &= idx <= 35;
						}
						win[3] &= idx <= N;
					}
				int ans = 0;
				for(int i = 0; i < 4; ++i)
					if(win[i])
						ans += V[i];
				out.println(ans);
			}
			if(t != tc)
				out.println();
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