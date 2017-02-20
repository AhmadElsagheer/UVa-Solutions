package v008;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LargestSubmatrix_UVa836 {

	static int[][] matrix, prefix;
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String line = sc.next();
			int N = line.length();
			matrix = new int[N][N];
			for(int i = 0; i < N; ++i)
			{
				for(int j = 0; j < N; ++j)
					matrix[i][j] = line.charAt(j) - '0';
				if(i != N - 1)
					line = sc.next();
			}
			prefix = new int[N][N];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
				{
					prefix[i][j] = matrix[i][j];
					if(i > 0)
						prefix[i][j] += prefix[i-1][j];
					if(j > 0)
						prefix[i][j] += prefix[i][j-1];
					if(i > 0 && j > 0)
						prefix[i][j] -= prefix[i-1][j-1];
				}
			
			int max = 0;
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					for(int r = i; r < N; ++r)
						for(int c = j; c < N; ++c)
						{
							int cur = prefix[r][c];
							if(i > 0)
								cur -= prefix[i-1][c];
							if(j > 0)
								cur -= prefix[r][j-1];
							if(i > 0 && j > 0)
								cur += prefix[i-1][j-1];
							if(cur == (r - i + 1) * (c - j + 1))
								max = Math.max(max, cur);
						}
			out.println(max);
			if(tc != 0)
				out.println();
			
		}
		out.flush();

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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
