package cp5_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TheCatInTheHat_UVa107 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int H = sc.nextInt(), W = sc.nextInt();
			if(H + W == 0)
				break;

			int N = 2;
			if(W == 1)
				N = 1;
			else
			{
				while(true)
				{
					int x = powerOf(N, W), y = powerOf(N + 1, H);
					if(x != -1 && x == y)
						break;
					++N;
				}
			}

			int sumH = 0, c = 1, NW = 0;
			while(H > 0)
			{	
				sumH += H * c;
				if(H > 1)
					NW += c;
				c *= N;
				H /= N + 1;
			}
			out.println(NW + " " + sumH);
		}
		out.flush();
		out.close();
	}

	static int powerOf(int a, int b)
	{
		int ret = 1;
		long c = a;
		while(c < b)
		{
			c *= a;
			++ret;
		}
		return c == b ? ret : -1;
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
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}