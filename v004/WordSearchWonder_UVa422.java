package v004;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class WordSearchWonder_UVa422 {

	static int[] dx = new int[] {0, 0, -1, -1, -1, 1, 1, 1};
	static int[] dy = new int[] {-1, 1, 0, 1, -1, 0, 1, -1};
	static int N, x1, y1, x2, y2;
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
			if(idx == 0) { x1 = i + 1; y1 = j + 1; }
			else if(idx == t.length -1) { x2 = i + 1; y2 = j + 1; }
			return true;
		}
		return false;
	}

	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != N && y != N; }

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();

		grid = new char[N][];
		for(int i = 0; i < N; ++i)
			grid[i] = sc.next().toCharArray();

		while(true)
		{
			t = sc.next().toCharArray();
			if(t[0] == '0')
				break;
			boolean done = false;
			for(int i = 0; !done && i < N; ++i)
				for(int j = 0; !done && j < N; ++j)
					for(int k = 0; !done && k < 8; ++k)
						done |= bt(i, j, 0, k);

			if(!done)
				out.println("Not found");
			else
				out.printf("%d,%d %d,%d\n", x1, y1, x2, y2);
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