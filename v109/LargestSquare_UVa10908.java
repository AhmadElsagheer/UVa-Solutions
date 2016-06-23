package v109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class LargestSquare_UVa10908 {

	static int[] xLoc = new int[] {-1, -1, 1, 1};
	static int[] yLoc = new int[] {-1, 1, 1, -1};
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int R = sc.nextInt(), C = sc.nextInt(), Q = sc.nextInt();
			out.printf("%d %d %d\n", R, C, Q);
			char[][] grid = new char[R][];
			for(int i = 0; i < R; ++i)
				grid[i] = sc.next().toCharArray();
			while(Q-->0)
			{
				int r = sc.nextInt(), c = sc.nextInt();
				int len = 3;
				while(true)
				{
					int d = len >> 1;
					if(r - d < 0 || r + d >= R || c - d < 0 || c + d >= C)
						break;
					boolean possible = true;
					char b = grid[r][c];
					for(int i = 0; i < 4; ++i)
					{
						int x = r + xLoc[i] * d, y = c + yLoc[i] * d;
						for(int j = 0; j < len - 1; ++j)
						{
							int xx = x + dx[i] * j, yy = y + dy[i] *j;
							if(grid[xx][yy] != b)
							{
								possible = false;
								break;
							}
						}
					}
					if(!possible)
						break;
					len += 2;
				}
				out.println(len - 2);
			}
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}