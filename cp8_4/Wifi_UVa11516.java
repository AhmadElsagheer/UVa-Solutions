package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Wifi_UVa11516 {

	static final double EPS = 1e-9;
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int k = sc.nextInt(), m = sc.nextInt();
			int[] pos = new int[m];
			for(int i = 0; i < m; ++i)
				pos[i] = sc.nextInt();
			Arrays.sort(pos);
			double ans = -1, lo = 0, hi = 1e6;
			for(int i = 0; i < 50; ++i)
			{
				double mid = (lo + hi) / 2;
				if(possible(pos, m, k, mid))
				{
					ans = mid;
					hi = mid;
				}
				else
					lo = mid;
			}
			out.printf("%.1f\n", ans);
						
		}
		out.flush();
		out.close();
	}
	
	static boolean possible(int[] pos, int m, int k, double dist)
	{
		double lst = pos[0] + dist;
		--k;
		for(int i = 1; i < m; ++i)
			if(Math.abs(pos[i] - lst) > dist + EPS)
			{
				--k;
				lst = pos[i] + dist;
			}
		return k >= 0;
	}	
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), ",| ");
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
