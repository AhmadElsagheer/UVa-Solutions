package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class UniqueFactorization_UVa10858 {

	static StringBuilder sb;
	static int[] divs;
	static int[] cur;
	static int count;
	
	static void generate(int divIdx, int curIdx, int N)
	{
		if(divIdx == divs.length)
		{
			if(N == 1)
			{
				++count;
				for(int i = 0; i < curIdx - 1; ++i)
					sb.append(cur[i] + " ");
				sb.append(cur[curIdx-1] + "\n");
			}
			return;
		}
		
		//take divisor
		int d = divs[divIdx];
		if(N % d == 0)
		{
			cur[curIdx] = d;
			generate(divIdx, curIdx + 1, N / d);
		}
		//leave divisor
		generate(divIdx + 1, curIdx, N);
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			if(N == 1)
			{
				out.println(0);
				continue;
			}
			Stack<Integer> s1 = new Stack<Integer>();
			Stack<Integer> s2 = new Stack<Integer>();
			for(int i = 2; i * i <= N; ++i)
				if(N % i == 0)
				{
					s1.add(i);
					if(i != N / i)
						s2.add(N / i);
				}
			divs = new int[s1.size() + s2.size()];
			int i = 0;
			while(!s1.isEmpty())
				s2.push(s1.pop());
			while(!s2.isEmpty())
				divs[i++] = s2.pop();
			cur = new int[divs.length];
			sb = new StringBuilder();
			count = 0;
			generate(0, 0, N);
			out.println(count);
			out.print(sb);
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

		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {return br.ready();}


	}
}