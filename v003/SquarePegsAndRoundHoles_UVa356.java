package v003;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SquarePegsAndRoundHoles_UVa356 {

	static int[] dx = new int[] {0, 0, 1, 1};
	static int[] dy = new int[] {0, 1, 0, 1};
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			int n = sc.nextInt(), m = n<<1, d = m - 1, fullIn = 0, segIn = 0;
			for(int i = 0; i < m; ++i)
				for(int j = 0; j < m; ++j)
				{
					int in = 0, on = 0;
					for(int k = 0; k < 4; ++k)
					{
						int p = posToCircle(n, d, i + dx[k], j + dy[k]);
						if(p == 1)
							++in;
						else if(p == 0)
							++on;
					}
					if(in + on == 4)
						++fullIn;
					else if(in != 0)
						++segIn;
				}
			out.printf("In the case n = %d, %d cells contain segments of the circle.\n", n, segIn);
			out.printf("There are %d cells completely contained in the circle.\n", fullIn);
					
		}
		out.flush();
		out.close();
	}
	
	static int posToCircle(int n, int d, int x, int y)
	{
		int s = 4 * (sq(x - n) + sq(y - n));
		if(s < d * d)
			return 1;
		if(s > d * d)
			return -1;
		return 0;
	}
	
	static int sq(int x) { return x * x; }
	
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