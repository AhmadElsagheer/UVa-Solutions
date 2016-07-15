package v008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Flooded_UVa815 {
	
		
	static final int INF = (int)2e9;
	
	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			int m = sc.nextInt(), n = sc.nextInt();
			if(m == 0 && n == 0) break;
			
			int[] region = new int[m * n + 1];
			for(int i = 0; i < m * n; i++) region[i] = sc.nextInt();
			region[n*m] = INF;
			int v = sc.nextInt();
	
			Arrays.sort(region);
			double ans = 0.0;
			int under = 0;
			int lvl = region[0];
			for(int i = 1; i <= m * n && v > 0; i++)
				if(region[i] != lvl)
					if((long)i * (region[i] - lvl) * 100 <= v)
					{
						v -= i * (region[i] - lvl) * 100;
						lvl = region[i];
					}
					else
					{
						ans = lvl + ((double)v / (i * 100));
						under = i;
						v = 0;
					}
			out.format("Region %d\nWater level is %.2f meters.\n%.2f percent of the region is under water.\n\n", k++, ans, under * 100.0 / (n * m));
					
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
