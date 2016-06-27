package v125;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MoviePolice_UVa12515 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		
		int M = sc.nextInt(), Q = sc.nextInt();
		String[] movies = new String[M];
		for(int i = 0; i < M; ++i)
			movies[i] = sc.next();
		while(Q-->0)
		{
			String t = sc.next();
			int ans = -1, len = t.length(), diff = len + 1;
			for(int i = 0; i < M; ++i)
				if(movies[i].length() >= len)
				{
					int cur = hammingDistance(movies[i], t);
					if(cur < diff)
					{
						ans = i + 1;
						diff = cur;
					}
				}
			out.println(ans);
		}
			
		
		out.flush();
		out.close();
	}
	
	static int hammingDistance(String x, String y)
	{
		int xLen = x.length(), yLen = y.length(), min = yLen;
		for(int i = 0; i + yLen <= xLen; ++i)
		{
			int cur = 0;
			for(int j = 0; j < yLen; ++j)
				if(x.charAt(i + j) != y.charAt(j))
					++cur;
			min = Math.min(min, cur);
		}
		return min;
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}