package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AMidSummernightsDream_UVa10057 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			int n = sc.nextInt(), a[] = new int[n], f[] = new int[65536];
			long sum = 0;
			for(int i = 0; i < n; ++i)
			{
				sum += a[i] = sc.nextInt();
				f[a[i]]++;
			}
			long minDiff = sum;
			int minA = 0, freqA = f[0], count = 1, before = f[0], after = n - f[0];
			for(int i = 1; i < 65536; ++i)
			{
				sum -= after;
				sum += before;
				if(sum < minDiff)
				{
					minDiff = sum; minA = i; count = 1; freqA = f[i];
				}
				else if(sum == minDiff)
				{
					++count;
					freqA += f[i];
				}
				
				int x = f[i];
				after -= x;
				before += x;
			}
			out.println(minA + " " + freqA + " " + count);
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}