package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BadCode_UVa10950 {


	static int N;
	static Code[] codes;
	static char[] enc, text;
	static TreeSet<String> set;

	static void bt(int idx, int len, boolean skipped)
	{
		if(idx == N)
		{
			if(!skipped)
			{
				text[len] = '\n';
				set.add(new String(Arrays.copyOf(text, len + 1)));				
			}
			return;
		}
		for(Code code: codes)
			if(enc[idx] == code.s[0])
				if(code.s.length == 1)
				{
					text[len] = code.c;
					bt(idx + 1, len + 1, false);					
				}
				else if(idx < N - 1 && enc[idx+1] == code.s[1])
				{
					text[len] = code.c;
					bt(idx + 2, len + 1, false);
				}
		if(!skipped && enc[idx] == '0')
			bt(idx + 1, len, true);
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while(true)
		{
			int C = sc.nextInt();
			if(C == 0)
				break;
			codes = new Code[C];
			for(int i = 0; i < C; ++i)
				codes[i] = new Code(sc.next().charAt(0), sc.next().toCharArray());
			enc = sc.next().toCharArray();
			N = enc.length;
			text = new char[N + 1];
			set = new TreeSet<String>();
			bt(0, 0, false);
			sb.append("Case #" + tc++ + "\n");
			int count = 0;
			for(String s: set)
				if(count++ == 100)
					break;
				else
					sb.append(s);
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Code implements Comparable<Code>
	{
		char c;
		char[] s;

		Code(char a, char[] b) { c = a; s = b; }

		public int compareTo(Code code) { return c - code.c; }
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