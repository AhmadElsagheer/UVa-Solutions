package v100;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class WheresWaldorf_UVa10010 {

	static int[] dx = new int[] {0, 0, -1, -1, -1, 1, 1, 1};
	static int[] dy = new int[] {-1, 1, 0, 1, -1, 0, 1, -1};
	static int R, C, x, y;
	static char[][] grid;
	static char[] t;

	static boolean bt(int i, int j, int idx, int dir)
	{
		if(idx == t.length)
			return true;
		if(!valid(i, j) || grid[i][j] != t[idx])
			return false;
		
		if(bt(i + dx[dir], j + dy[dir], idx + 1, dir))
		{
			if(idx == 0) { x = i + 1; y = j + 1; }
			return true;
		}
		return false;
	}

	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != R && y != C; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			R = sc.nextInt();
			C = sc.nextInt();
			grid = new char[R][];
			for(int i = 0; i < R; ++i)
				grid[i] = sc.next().toLowerCase().toCharArray();
			int q = sc.nextInt();
			while(q-->0)
			{
				t = sc.next().toLowerCase().toCharArray();

				boolean done = false;
				for(int i = 0; !done && i < R; ++i)
					for(int j = 0; !done && j < C; ++j)
						for(int k = 0; !done && k < 8; ++k)
							done |= bt(i, j, 0, k);

				out.println(x + " " + y);
			}
			if(tc != 0)
				out.println();
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