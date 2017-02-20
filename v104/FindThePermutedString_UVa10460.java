package v104;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FindThePermutedString_UVa10460 {

	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		BigInteger[] fac = new BigInteger[27];
		fac[0] = fac[1] = BigInteger.ONE;
		for(int i = 2; i < 27; ++i)
			fac[i] = fac[i - 1].multiply(BigInteger.valueOf(i));
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String s = sc.next();
			int i = 0;
			BigInteger idx = BigInteger.valueOf(sc.nextInt()), l = BigInteger.valueOf(2);
			BigInteger f = fac[s.length()];
			ArrayList<Character> ans = new ArrayList<Character>(s.length());
			ans.add(s.charAt(0));
			while(++i < s.length())
			{
				int pos = idx.multiply(l).add(f.subtract(BigInteger.ONE)).divide(f).intValue();
				ans.add(pos - 1, s.charAt(i));
				f = f.divide(l);
				idx = idx.subtract(f.multiply(BigInteger.valueOf(pos - 1)));
				l = l.add(BigInteger.ONE);
			}
			StringBuilder sb = new StringBuilder();
			for(i = 0; i < s.length(); ++i)
				sb.append(ans.get(i));
			out.println(sb);
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
