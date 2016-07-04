package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class DDF_UVa547 {

	static int[] next = new int[6000];
	static int[] len = new int[6000];
	
	static int getLen(int x)
	{
		if(next[x] != 0)
			return len[x];
		int sum = 0;
		for(int i = 1; i * i <= x; ++i)
			if(x % i == 0)
			{
				sum += sumDigits(i);
				if(i != x / i)
					sum += sumDigits(x / i);
			}
		next[x] = sum;
		if(sum != x)
			return len[x] = 1 + getLen(sum);
		return 1;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		
		while(sc.ready())
		{
			int l = sc.nextInt(), r = sc.nextInt();
			boolean swapped = false;
			if(l > r)
			{
				swapped = true;
				int tmp = l; l = r; r = tmp;
			}

			int maxLen = -1, s = -1;
			for(int i = l; i <= r; ++i)
			{
				int len = getLen(i);
				if(len > maxLen)
				{
					s = i;
					maxLen = len;
				}
			}
			if(swapped) { int tmp = l; l = r; r = tmp; }

			out.printf("Input%d: %d %d\nOutput%d:", tc, l, r, tc++);
			
			int lst = -1, cur = s;
			while(lst != cur)
			{
				out.print(" " + cur);
				cur = next[lst = cur];
			}
			
			out.println();
		}
		out.flush();
		out.close();
	}
	
	static int sumDigits(int x)
	{
		int sum = 0;
		while(x > 0)
		{
			sum += x%10;
			x /= 10;
		}
		return sum;
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