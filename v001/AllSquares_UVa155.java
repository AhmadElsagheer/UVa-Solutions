package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AllSquares_UVa155 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int k = sc.nextInt(), i = sc.nextInt(), j = sc.nextInt();
			if(k == 0)
				break;
			out.printf("%3d\n", bt(k, 1024, 1024, i, j));
		}
		out.flush();
	}
	
	static int bt(int k, int x, int y, int i, int j)
	{
		int ret = contains(k, x, y, i, j) ? 1 : 0;
		if(k == 1)
			return ret;
		if(i > x)
			if(j > y) return ret + bt(k>>1, x + k, y + k, i, j);
			else return ret + bt(k>>1, x + k, y - k, i, j);
		else
			if(j > y) return ret + bt(k>>1, x - k, y + k, i, j);
		return ret + bt(k>>1, x - k, y - k, i, j);
	
	}
	
	static boolean contains(int k, int x, int y, int i, int j)
	{
		if(i <= x + k && i >= x - k && j <= y + k && j >= y - k)
			return true;
		return false;
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
