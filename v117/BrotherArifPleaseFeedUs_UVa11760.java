package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BrotherArifPleaseFeedUs_UVa11760 {

	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int R, C;
	static boolean[] validRow, validCol;

	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != R && y != C && validRow[x] && validCol[y];
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			R = sc.nextInt(); C = sc.nextInt();
			int N = sc.nextInt();
			if(R == 0)
				break;
			validRow = new boolean[R];
			validCol = new boolean[C];
			Arrays.fill(validRow, true);
			Arrays.fill(validCol, true);

			while(N-->0)
			{
				validRow[sc.nextInt()] = false;
				validCol[sc.nextInt()] = false;
			}
			int x = sc.nextInt(), y = sc.nextInt();
			boolean canEscape = valid(x, y);
			for(int k = 0; k < 4; ++k)
				canEscape |= valid(x + dx[k], y + dy[k]);
			if(canEscape)
				out.printf("Case %d: Escaped again! More 2D grid problems!\n", tc++);
			else
				out.printf("Case %d: Party time! Let's find a restaurant!\n", tc++);


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