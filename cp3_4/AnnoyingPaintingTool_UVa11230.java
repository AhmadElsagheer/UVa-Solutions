package cp3_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AnnoyingPaintingTool_UVa11230 {

	static int R, C;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			R = sc.nextInt();
			C = sc.nextInt();
			int r = sc.nextInt(), c = sc.nextInt();
			if(R == 0)
				break;
			int[][] patt = new int[R][C];
			for(int i = 0; i < R; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < C; ++j)
					patt[i][j] = line.charAt(j) - '0';
			}
			int ans = 0;
			int[][] pic = new int[R][C];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					if(pic[i][j] != patt[i][j])
						if(i + r > R || j + c > C)
						{
							ans = -1;
							break;
						}
						else
						{
							++ans;
							adjust(pic, r, c, i, j);
						}
			out.println(ans);
		}
		out.flush();
		
	}
	
	static void adjust(int[][] pic, int r, int c, int i, int j)
	{
		for(int ii = i; ii < i + r; ++ii)
			for(int jj = j; jj < j + c; ++jj)
				pic[ii][jj] ^= 1;
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
		
		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
