package v010;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BitCompressor_UVa1052 {

	static char[] s;
	
	static int bt(int idx, int len, int ones)
	{
		if(ones < 0 || len < 0 || ones > len)
			return 0;
		if(idx == s.length)
			return len == 0 ? 1 : 0;
		
		if(len == 0)
			return 0;
		if(s[idx] == '0')
			return bt(idx + 1, len - 1, ones);
		if(idx == s.length - 1)
			return bt(idx + 1, len - 1, ones - (s[idx] - '0'));
		
		int ret = 0;
		if(idx == s.length - 2 || s[idx+1] == '0' || s[idx+2] == '0')
		{
			if(s[idx+1] == '1')
				ret = bt(idx + 2, len - 2, ones - 2);
			else
				ret = bt(idx + 1, len - 1, ones - 1);
		}
		
		for(int k = 1, msk = 1, end = Math.min(18, s.length - idx); ret < 2 && k < end; ++k)
		{
			msk = msk<<1 | (s[idx+k] - '0');
			
			if(msk > 2 && (idx + k == s.length - 1 || s[idx+k+1] == '0'))
			{				
				ret += bt(idx + k + 1, len - msk, ones - msk);
				if(ret > 2)
					ret = 2;
			}
		}
			
				
		return ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int L = sc.nextInt();
			if(L == 0)
				break;
			int N = sc.nextInt();
			s = sc.next().toCharArray();
			int ans = bt(0, L, N);
			out.printf("Case #%d: ", tc++);
			if(ans == 0)
				out.println("NO");
			else if(ans == 1)
				out.println("YES");
			else
				out.println("NOT UNIQUE");
			 
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}