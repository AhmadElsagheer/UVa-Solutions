package cp8_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AnagramDivision_UVa11084 {

	static int d, s, c;
	static int[] digits;
	
	static void bt(int idx, long sum)
	{
		if(idx == s)
		{
			if(sum%d == 0)
				++c;
			return;
		}
		
		for(int i = 0; i < 10; ++i)
			if(digits[i] > 0)
			{
				digits[i]--;
				bt(idx + 1, sum * 10 + i);
				digits[i]++;
			}
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String line = sc.next();
			d = sc.nextInt();
			s = line.length();
			digits = new int[10];
			for(int i = 0; i < s; ++i)
				digits[line.charAt(i) - '0']++;
			c = 0;
			bt(0, 0);
			out.println(c);
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
