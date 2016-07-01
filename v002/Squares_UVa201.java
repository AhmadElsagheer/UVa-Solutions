package v002;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Squares_UVa201 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(sc.ready())
		{
			if(tc != 1)
				out.println("\n**********************************\n");
			int n = sc.nextInt(), m = sc.nextInt();
			int[] squares = new int[n];
			boolean[][] H = new boolean[n][n], V = new boolean[n][n];
			while(m-->0)
			{
				char c = sc.next().charAt(0);
				int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
				if(c == 'H')
					H[x][y] = true;
				else
					V[x][y] = true;
			}
			boolean found = false;
			for(int k = 1; k < n; ++k)
			{
				int count = 0;
				for(int i = 0; i + k < n; ++i)
					for(int j = 0; j + k < n; ++j)
					{
						boolean possible = true;
						for(int d = i; d < i + k; ++d)
							possible &= V[j][d] && V[j+k][d];
						for(int d = j; d < j + k; ++d)
							possible &= H[i][d] && H[i+k][d];
						if(possible)
							++count;
					}
				if(count > 0)
					found = true;
				squares[k] = count;
			}
			out.printf("Problem #%d\n\n", tc++);
			if(!found)
				out.println("No completed squares can be found.");
			else
				for(int k = 1; k < n; ++k)
					if(squares[k] != 0)
						out.printf("%d square (s) of size %d\n", squares[k], k);
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