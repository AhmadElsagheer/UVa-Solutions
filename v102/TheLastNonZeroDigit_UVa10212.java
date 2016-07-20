package v102;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TheLastNonZeroDigit_UVa10212 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int N = sc.nextInt(), M = sc.nextInt();
			int countFives = 0, countTwos = 0, res = 1;
			for(int i = 0; i < M; ++i)
			{
				int x = N - i;
				while(x%10 == 0)
					x /= 10;
				while(x%5 == 0)
				{
					x /= 5;
					++countFives;
				}
				while(x%2 == 0)
				{
					x /= 2;
					++countTwos;
				}
				res = (res * x)%10;
			}
			
			while(countFives > countTwos)
			{
				res = (res * 5)%10;
				--countFives;
			}
			
			while(countTwos > countFives)
			{
				res = (res * 2)%10;
				--countTwos;
			}
			out.println(res);
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