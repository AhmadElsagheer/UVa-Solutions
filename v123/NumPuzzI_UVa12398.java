package v123;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumPuzzI_UVa12398 {

	static final int[] dx = new int[] {0, 0, 0, -1, 1};
	static final int[] dy = new int[] {0, -1, 1, 0, 0};
	
	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != 3 && y != 3; }
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(sc.ready())
		{
			String s = sc.nextLine();
			int[][] mat = new int[3][3];
			for(int i = s.length() - 1; i >= 0; --i)
			{
				int c = s.charAt(i) - 'a';
				int x = c / 3, y = c % 3;
				for(int k = 0; k < 5; ++k)
				{
					int xx = x + dx[k], yy = y + dy[k];
					if(valid(xx, yy))
						mat[xx][yy] = (mat[xx][yy] + 1)%10;
				}
			}
			out.printf("Case #%d:\n", tc++);
			for(int i = 0; i < 3; ++i)
				out.printf("%d %d %d\n", mat[i][0], mat[i][1], mat[i][2]);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}