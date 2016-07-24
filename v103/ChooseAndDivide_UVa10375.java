package v103;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ChooseAndDivide_UVa10375 {

	static double nCr(int n1, int k1, int n2, int k2)
	{
		if(n1 - k1 < k1)
			k1 = n1 - k1;
		if(n2 - k2 < k2)
			k2 = n2 - k2;
		double ret = 1.0;
		for(int i = 1, j = 1; i <= k1 || j <= k2; ++i, ++j)
		{
			if(i <= k1)
				ret = ret * (n1 - i + 1) / i;
			if(j <= k2)
				ret /= 1.0 * (n2 - j + 1) / j;
			
		}
		
		return ret;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int n1 = sc.nextInt(), k1 = sc.nextInt(), n2 = sc.nextInt(), k2 = sc.nextInt();
			out.format("%.5f\n", nCr(n1, k1, n2, k2));
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
