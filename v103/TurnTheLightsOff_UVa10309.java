package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TurnTheLightsOff_UVa10309 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			String s = sc.next();
			if(s.equals("end"))
				break;
			int[][] gridOrg = new int[10][10];
			for(int i = 0; i < 10; ++i)
			{
				char[] t = sc.next().toCharArray();
				for(int j = 0; j < 10; ++j)
						gridOrg[i][j] = t[j] == '#' ? 0 : 1;
			}
			
			int min = 100000;
			for(int k = 0; k < 1024; ++k)
			{
				int[][] grid = new int[10][10];
				for(int i = 0; i < 10; ++i)
					grid[i] = Arrays.copyOf(gridOrg[i], 10);
				
				for(int j = 0; j < 10; ++j)
					if((k & 1<<j) != 0)
					{

						grid[0][j] ^= 1;
						grid[1][j] ^= 1;
						if(j > 0)
							grid[0][j-1] ^= 1;
						if(j < 9)
							grid[0][j+1] ^= 1;
					}
				int ans = Integer.bitCount(k);
				for(int i = 0; i < 9; ++i)
					for(int j = 0; j < 10; ++j)
						if(grid[i][j] == 1)
						{
							++ans;
							grid[i+1][j] ^= 1;
							if(j > 0)
								grid[i+1][j-1] ^= 1;
							if(j < 9)
								grid[i+1][j+1] ^= 1;
							if(i < 8)
								grid[i+2][j] ^= 1;
						}
				for(int j = 0; j < 10; ++j)
					if(grid[9][j] == 1)
					{
						ans = -1;
						break;
					}
				if(ans != -1)
					min = Math.min(ans, min);
			}
			if(min == 100000)
				min = -1;
			out.printf("%s %d\n", s, min);
			
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