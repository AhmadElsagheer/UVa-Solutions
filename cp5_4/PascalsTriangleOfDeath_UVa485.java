package cp5_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class PascalsTriangleOfDeath_UVa485 {

	static final BigInteger limit = BigInteger.TEN.pow(60);
	
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		BigInteger[] cur = { BigInteger.ONE };
		sb.append("1\n");
		boolean done = false;
		while(true)
		{
			BigInteger[] nxt = new BigInteger[cur.length + 1];
			nxt[0] = nxt[nxt.length - 1] = BigInteger.ONE;
			sb.append("1");
			for(int i = 1; i < nxt.length - 1; ++i)
			{
				nxt[i] = cur[i-1].add(cur[i]);
				if(nxt[i].compareTo(limit) >= 0)
					done = true;
				sb.append(" ").append(nxt[i]);
			}
			sb.append(" 1\n");
			if(done)
				break;
			cur = nxt;
		}
		out.print(sb);
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
