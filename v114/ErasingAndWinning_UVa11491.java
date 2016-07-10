package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ErasingAndWinning_UVa11491 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int N = sc.nextInt(), D = sc.nextInt();
			if(N == 0)
				break;
			String s = sc.next();
			Deque<Character> deque = new LinkedList<Character>();
			for(int i = 0; i < N; ++i)
			{
				char c = s.charAt(i);
				while(D > 0 && !deque.isEmpty() && deque.getLast() < c)
				{
					deque.removeLast();
					--D;
				}
				deque.addLast(c);
			}
			
			while(D-->0)	
				deque.removeLast();
			
			StringBuilder sb = new StringBuilder();
			while(!deque.isEmpty())
				sb.append(deque.remove());
			out.println(sb);
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