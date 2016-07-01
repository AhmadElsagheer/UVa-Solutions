package v122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OverlappingScenes_UVa12249 {

	static String[] scenes;
	static int N, order[], minLength;
	
	static void bt(int idx, int used)
	{
		if(idx == N)
		{
			String res = "";
			for(int x: order)
				res = merge(res, scenes[x]);
			minLength = Math.min(minLength, res.length());
			return;
		}
		for(int i = 0; i < N; ++i)
			if((used & 1<<i) == 0)
			{
				order[idx] = i;
				bt(idx + 1, used | 1<<i);
			}
	}
	
	static String merge(String x, String y)
	{
		int maxMatch = 0;
		for(int i = 0; i < x.length(); ++i)
		{
			int j = 0;
			while(i + j < x.length() && j < y.length() && x.charAt(i + j) == y.charAt(j))
				++j;
			if(i + j == x.length())
				maxMatch = Math.max(maxMatch, j);
		}
		return x.substring(0, x.length() - maxMatch) + y;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			scenes = new String[N = sc.nextInt()];
			minLength = 0;
			for(int i = 0; i < N; ++i)
			{
				scenes[i] = sc.next();
				minLength += scenes[i].length();
			}
			order = new int[N];
			bt(0, 0);
			out.printf("Case %d: %d\n", t, minLength);
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