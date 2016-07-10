package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PrimedSubsequence_UVa10871 {
	
	static boolean[] isComposite;
	
	static void sieve(int N)
	{
		isComposite = new boolean[N];
		isComposite[0] = isComposite[1] = true;
		for(int i = 2; i * i < N; ++i)
			if(!isComposite[i])
				for(int j = i * i; j < N; j += i)
					isComposite[j] = true;
				
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		sieve((int) 1e8 + 1);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), a[] = new int[n];
			for(int i = 0; i < n; ++i)
				a[i] = sc.nextInt();
			
			int minLen = -1, startIdx = -1;
			for(int len = 2; minLen == -1 && len <= n; ++len)
			{
				int sum = 0, i = 0, j = 0;
				while(i - j + 1 < len)
					sum += a[i++];
				while(i < n)
				{
					sum += a[i++];
					if(!isComposite[sum])
					{
						minLen = len;
						startIdx = j;
						break;
					}
					sum -= a[j++];
				}
			}
			if(minLen == -1)
				out.println("This sequence is anti-primed.");
			else
			{
				out.printf("Shortest primed subsequence is length %d:", minLen);
				for(int i = 0; i < minLen; ++i)
					out.print(" " + a[startIdx + i]);
				out.println();				
			}
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