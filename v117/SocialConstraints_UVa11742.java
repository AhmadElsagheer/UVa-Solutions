package v117;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SocialConstraints_UVa11742 {

	static int n, m, rules[][], pos[] = new int[10];
	
	static int f(int idx, int msk)
	{
		if(idx == n)
		{
			for(int i = 0; i < m; ++i)
				if(rules[i][0] > 0)
				{
					if(Math.abs(pos[rules[i][1]]- pos[rules[i][2]]) > rules[i][0])
						return 0;
				}
				else
				{
					if(Math.abs(pos[rules[i][1]] - pos[rules[i][2]]) < -rules[i][0])
						return 0;
				}
			return 1;
		}
		
		int c = 0;
		for(int i = 0; i < n; ++i)
			if((msk & (1<<i)) == 0)
			{
				pos[idx] = i;
				c += f(idx + 1, msk | (1<<i));
			}
		return c;
	}
	public static void main(String[] args) throws IOException {


		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			n = sc.nextInt(); m = sc.nextInt();
			if(n + m == 0)
				break;
			rules = new int[m][3];
			for(int i = 0; i < m; ++i)
			{
				rules[i][1] = sc.nextInt();
				rules[i][2] = sc.nextInt();
				rules[i][0] = sc.nextInt();
			}
			out.println(f(0, 0));
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


