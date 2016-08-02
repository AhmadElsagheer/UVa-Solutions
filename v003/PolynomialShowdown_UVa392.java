package v003;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class PolynomialShowdown_UVa392 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			boolean first = true;
			String res = "";
			for(int i = 8; i >= 0; --i)
			{
				res += format(i, sc.nextInt(), first);
				if(!res.isEmpty())
					first = false;
			}
			if(res.isEmpty())
				res = "0";
			out.println(res);
		}
		out.flush();
		out.close();
	}
	
	static String format(int deg, int coeff, boolean first)
	{
		String term = "";
		char sign = '+';
		if(coeff != 0)
		{
			sign = coeff < 0 ? '-' : '+';
			if(deg != 0)
				term = deg == 1 ? "x" : "x^" + deg;
			if(Math.abs(coeff) == 1)
				term = (deg != 0 ? "" : Math.abs(coeff)) + term;
			else
				term = Math.abs(coeff) + term;
			
			if(first)
			{
				if(sign == '-')
					term = sign + term;
			}
			else
				term = " " + sign + " " + term;
			
		}
		
		return term;
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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