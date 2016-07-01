package v108;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Maze_UVa10890 {

	static int[] x, y;
	static int N, T, collected, min;
	
	static void bt(int i, int j, int S, int dist)
	{
		int cur = dist + (N - 1 - i) + (N - 1 - j);
		if(cur >= min)
			return;
		if(S == 0)
		{
			if(cur < min)
				min = cur;
			return;
		}
		for(int k = 0; k < T; ++k)
			if((collected & 1<<k) == 0)
			{
				collected |= 1<<k;
				bt(x[k], y[k], S - 1, dist + Math.abs(x[k] - i) + Math.abs(y[k] - j));
				collected &= ~(1<<k);
			}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			N = sc.nextInt(); T = sc.nextInt();
			int S = sc.nextInt();
			if(N == 0)
				break;
			x = new int[T];
			y = new int[T];
			for(int i = 0; i < T; ++i)
			{
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
			}
			min = Integer.MAX_VALUE;
			collected = 0;
			bt(0, 0, S, 0);
			out.printf("Case %d: %d\n", tc++, min);
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