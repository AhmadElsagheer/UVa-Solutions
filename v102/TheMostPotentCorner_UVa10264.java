package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class TheMostPotentCorner_UVa10264
{

	static int N , p[], w[];
		
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(sc.ready())
		{
			N = sc.nextInt();
			w = new int[1<<N];
			
			for(int i = 0; i < 1<<N; i++) w[i] = sc.nextInt();
			
			compute();
			
			sb.append(maxSum()+"\n");
		}
		System.out.print(sb);
	}
	
	static void compute()
	{
		p = new int[1<<N];
		for(int i = 0; i < 1<<N; i++)
			for(int j = 0; j < N; j++)
				p[i] += w[i ^ (1<<j)];
	}
	
	static int maxSum()
	{
		int max = 0;
		for(int i = 0; i < 1<<N; i++)
			for(int j = 0; j < N; j++)
				max = Math.max(max, p[i] + p[i ^ (1<<j)]);
		return max;
		
	}

	static class Scanner {
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

