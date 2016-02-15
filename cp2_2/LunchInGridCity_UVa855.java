package cp2_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LunchInGridCity_UVa855 {
	
	public static void main(String[] args) throws IOException {
		
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{		
			int R = sc.nextInt(), C = sc.nextInt(), F = sc.nextInt();
			int[] streets = new int[R], avenues = new int[C];
			while(F-->0)
			{
				int r = sc.nextInt() - 1, c = sc.nextInt() - 1;
				++streets[r];
				++avenues[c];
			}
			int x = -1, y = -1, minD = (int)1e9;
			for(int i = 0; i < R; ++i)
			{
				int d = 0;
				for(int j = 0; j < R; ++j)
					d += Math.abs(j - i) * streets[j];
				if(d < minD)
				{
					minD = d;
					x = i;
				}
			}
			
			minD = (int)1e9;
			for(int i = 0; i < C; ++i)
			{
				int d = 0;
				for(int j = 0; j < C; ++j)
					d += Math.abs(j - i) * avenues[j];
				if(d < minD)
				{
					minD = d;
					y = i;
				}
			}
			out.format("(Street: %d, Avenue: %d)\n", x + 1, y + 1);
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
