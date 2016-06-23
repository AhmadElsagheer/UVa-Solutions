package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LocalizedSummingForBlurring_UVa98 {

	
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while(in.ready())
		{
			if(first) first = false; else sb.append("\n");
			
			int n = in.nextInt(), m = in.nextInt();
			int[][] mat = new int[n][n];
			int[][] prefix_col = new int[n][n];
			int[][] prefix_row = new int[n][n];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
				{
					prefix_row[i][j] = prefix_col[i][j] = mat[i][j] = in.nextInt();
					if(j > 0) prefix_col[i][j] += prefix_col[i][j-1];
					if(i > 0) prefix_row[i][j] += prefix_row[i-1][j];
				}
			
			int x = n - m + 1;
			int[][] out = new int[x][x];
			
			int sum = 0;
			for(int i = 0; i < m; i++)
				sum += prefix_col[i][m-1];
		
			out[0][0] = sum;
			int start = out[0][0];
			for(int i = 0; i < x; i++)
			{
				int sub = start;
				if(i > 0)
						sub += prefix_col[m + i - 1][m - 1] - prefix_col[i - 1][m - 1];
				start = sub;
				for(int j = 0; j < x; j++)
				{
					if(j > 0)
						sub += prefix_row[i + m - 1][m + j - 1] - prefix_row[i + m - 1][j - 1];
					if(j > 0 && i > 0)
						sub -= prefix_row[i - 1][m + j - 1] - prefix_row[i - 1][j - 1];
					out[i][j] = sub;
				}
			}
			
			long total = 0;
			for(int i = 0; i < x; i++)
				for(int j = 0; j < x; j++)
				{
					total += out[i][j];
					sb.append(out[i][j]+"\n");
				}
			sb.append(total+"\n");
		}
		System.out.print(sb);
	
	}
	
	
	static class InputReader {
		StringTokenizer st;
		BufferedReader br;

		public InputReader(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}


	}

}
