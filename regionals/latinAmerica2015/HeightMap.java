package regionals.latinAmerica2015;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class HeightMap {

	static int[] dx = new int[] {0, -1, 0, 1};
	static int[] dy = new int[] {-1, 0, 1, 0};
	static int[][] top, dfsNum;
	static int R, C, counter;

	static int count(int x, int y)
	{
		dfsNum[x][y] = ++counter;
		int h = top[x][y];

		int ret = 0;
		for(int k = 0; k < 4; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			if(valid(i, j))
			{
				int hn = top[i][j];
				if(h > hn)
					++ret;			//side face

				if(dfsNum[i][j] == 0)
					ret += h == hn ? count(i, j) : 0;
				else if(dfsNum[i][j] < dfsNum[x][y])
				{

					int minH = Math.min(h, hn);
					//minus overcounted
					for(int w = 0; w < 4; ++w)
						if(((w ^ k) & 1) == 1 && valid(x + dx[w], y + dy[w]))
						{
							int h1 = top[x + dx[w]][y + dy[w]];
							int h2 = top[i + dx[w]][j + dy[w]];

							if(h1 < minH && h2 < minH)
								--ret;
						}
				}
			}

		}

		return ret;
	}

	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != R && y != C; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			R = sc.nextInt();
			C = sc.nextInt();
			top = new int[R][C];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					top[i][j] = sc.nextInt();
			dfsNum = new int[R][C];
			counter = 0;
			int ans = 5;
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					if(dfsNum[i][j] == 0)
						ans += count(i, j) + 1;

			out.println(ans);
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