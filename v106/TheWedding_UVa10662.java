package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheWedding_UVa10662 {
		
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.hasNext())
		{
			int T = sc.nextInt(), R = sc.nextInt(), H = sc.nextInt();
			boolean[][] TR = new boolean[T][R];
			boolean[][] RH = new boolean[R][H];
			boolean[][] HT = new boolean[H][T];
			int[] costT = new int[T], costR = new int[R], costH = new int[H];
			
			for(int i = 0; i < T; ++i)
			{
				costT[i] = sc.nextInt();
				for(int j = 0; j < R; ++j)
					if(sc.nextInt() == 0)
						TR[i][j] = true;
			}
			
			for(int i = 0; i < R; ++i)
			{
				costR[i] = sc.nextInt();
				for(int j = 0; j < H; ++j)
					if(sc.nextInt() == 0)
						RH[i][j] = true;
			}
			
			for(int i = 0; i < H; ++i)
			{
				costH[i] = sc.nextInt();
				for(int j = 0; j < T; ++j)
					if(sc.nextInt() == 0)
						HT[i][j] = true;
			}
			int ans = (int)1e9, t = -1, r = -1, h = -1;
			for(int i = 0; i < T; ++i)
				for(int j = 0; j < R; ++j)
					for(int k = 0; k < H; ++k)
						if(TR[i][j] && RH[j][k] && HT[k][i])
						{
							int cur = costT[i] + costR[j] + costH[k];
							if(cur < ans)
							{
								ans = cur;
								t = i;
								r = j;
								h = k;
							}
						}
			if(t == -1)
				out.println("Don't get married!");
			else
				out.printf("%d %d %d:%d\n", t, r, h, ans);
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
		
		public boolean hasNext() throws IOException
		{
			String line = br.readLine();
			if(line == null)
				return false;
			st = new StringTokenizer(line);
			if(line.isEmpty())
				return false;
			return true;
		}
	}
}