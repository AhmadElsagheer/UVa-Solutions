package v002;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class CountOnCantor_UVa264 {

	static final int upperBound = (int)1e7;
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int[] num = new int[upperBound], den = new int[upperBound];
		int di = -1, dj = 1;
		for(int k = 0, i = 1, j = 1; k < upperBound; ++k)
		{
			num[k] = i; den[k] = j;
			i += di;
			j += dj;
			if(i == 0) { i = 1; di *= -1; dj *= -1; }
			if(j == 0) { j = 1; di *= -1; dj *= -1; }
		}
		
		while(sc.ready())
		{
			int x = sc.nextInt();
			out.printf("TERM %d IS %d/%d\n", x, num[x-1], den[x-1]);
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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