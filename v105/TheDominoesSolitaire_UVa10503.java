package v105;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheDominoesSolitaire_UVa10503 {

	static int N, M, t, d[][];
	static Boolean[][] memo;
	
	static boolean dp(int lst, int used)
	{
		if(Integer.bitCount(used) == N)
			return lst == t;
		if(memo[lst][used] != null)
			return memo[lst][used];
		boolean ret = false;
		for(int i = 0; !ret && i < M; ++i)
			if((used & 1<<i) == 0)
			{
				if(lst == d[0][i])
					ret |= dp(d[1][i], used | 1<<i);
				if(lst == d[1][i])
					ret |= dp(d[0][i], used | 1<<i);
			}
		return memo[lst][used] = ret;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			M = sc.nextInt(); sc.next();
			d = new int[2][M];
			int s = sc.nextInt();
			t = sc.nextInt(); sc.next();
			for(int i = 0; i < M; ++i)
			{
				d[0][i] = sc.nextInt();
				d[1][i] = sc.nextInt();
			}
			
			memo = new Boolean[7][1<<M];
			out.println(dp(s, 0) ? "YES" : "NO");
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
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}