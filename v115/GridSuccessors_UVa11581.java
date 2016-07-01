package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class GridSuccessors_UVa11581 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int grid = 0;
			for(int i = 0, k = 0; i < 3; ++i)
			{
				String s = sc.next();
				for(int j = 0; j < 3; ++j, ++k)
					grid |= (s.charAt(j) - '0')<<k;
			}
			
			boolean[] visited = new boolean[1<<9];
			int idx = -1;
			visited[grid] = true;
			while(true)
			{
				int nxt = 0;
				for(int k = 0; k < 9; ++k)
				{
					int s = 0;
					if(k > 2)
						s ^= grid>>k-3 & 1;
					if(k < 6)
						s ^= grid>>k+3 & 1;
					if(k%3 != 0)
						s ^= grid>>k-1 & 1;
					if(k%3 != 2)
						s ^= grid>>k+1 & 1;
					
					nxt |= s<<k;
				}
				if(visited[nxt])
					break;
				visited[grid = nxt] = true;
				++idx;
			}
			out.println(idx);
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