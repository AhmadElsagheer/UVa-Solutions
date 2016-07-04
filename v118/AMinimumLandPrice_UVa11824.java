package v118;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class AMinimumLandPrice_UVa11824 {

	static final int limit = 5000000;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			ArrayList<Integer> L = new ArrayList<Integer>();
			while(true)
			{
				int x = sc.nextInt();
				if(x == 0)
					break;
				L.add(x);
			}
			Collections.sort(L);
			int cost = 0;
			for(int i = L.size() - 1, j = 1; i >= 0; --i, ++j)
			{
				int cur = pow(L.get(i), j);
				if(cur == -1)
				{
					cost = -1;
					break;
				}
				cost += cur<<1;
			}
			
			if(cost == -1 || cost > limit)
				out.println("Too expensive");
			else
				out.println(cost);
		}
		out.flush();
		out.close();
	}
	
	static int pow(int b, int e)
	{
		int res = 1;
		
		while(e > 0)
		{
			if((e & 1) == 1)
			{
				if(res > limit / b)
					return -1;
				res *= b;
			}
			
			e >>= 1;
			if(b <= limit / b)
				b *= b;
			else if(e > 0)
				return -1;		
		}
		return res;
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