package cp5_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class AntOnAChessBoard_UVa10161 {

	static int[] dx = new int[] { 0, 1, 0, -1};
	static int[] dy = new int[] { 1, 0, -1, 0};

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int M = sc.nextInt() - 1;
			if(M == -1)
				break;
			int x  = 1, y = 1, cur = 0;
			int[] dir = {0, 1, 2, 1, 0, 3};
			int[] steps = {1, 1, 1, 1, 2, 2};
			while(steps[cur] <= M)
			{
				x += steps[cur] * dx[dir[cur]];
				y += steps[cur] * dy[dir[cur]];
				M -= steps[cur];
				if(cur != 0 && cur != 3)
					steps[cur] += 2;
				cur = (cur + 1)%6;
			}
			
			x += M * dx[dir[cur]];
			y += M * dy[dir[cur]];
			out.println(x + " " + y);
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
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}