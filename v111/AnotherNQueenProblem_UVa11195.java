package v111;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AnotherNQueenProblem_UVa11195 {
	
	static int OK, bad[];
	
	static int f(int col, int rw, int d1, int d2)
	{
		if(rw == OK)
			return 1;
		
		int pos = OK & (~(bad[col] | rw | d1 | d2)), c = 0;
		while(pos != 0)
		{
			int p = pos & -pos;
			pos ^= p;
			
			c += f(col + 1, rw | p, (d1 | p) >> 1, (d2 | p) << 1);
		}
		return c;
	}
	
	public static void main(String[] args) throws IOException {


		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			bad = new int[n];
			
			for(int i = 0; i < n; ++i)
			{
				String line = sc.next();
				for(int j = 0; j < n; ++j)
					
					if(line.charAt(j) == '*')
						bad[j] |= 1<<i;
			}
			OK = (1<<n) - 1;
			out.printf("Case %d: %d\n", tc++, f(0, 0, 0, 0));
		}

		out.flush();
		out.close();


	}



	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(FileReader fileReader) throws FileNotFoundException{	br = new BufferedReader(fileReader);}

		public Scanner(InputStream s) throws FileNotFoundException{	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

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


		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}


