package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EenyMeenyMoo_UVa440 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			for(int m = 1; true; ++m)
			{
				boolean[] used = new boolean[n];
				int idx = 0, cnt = 0, skip = 0;
				while(true)
				{
					if(!used[idx])
						if(skip != 0)
						{
							--skip;
						}
						else
						{
							if(idx == 1)
								break;
							++cnt;
							skip = m - 1;
							used[idx] = true;
						}
					idx = (idx + 1) % n;
				}
				if(cnt == n - 1)
				{
					out.println(m);
					break;
				}
			}
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
	}
} 