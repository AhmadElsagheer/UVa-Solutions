package v118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Formula1_UVa11835 {
		
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int G = sc.nextInt(), P = sc.nextInt();
			if(G == 0)
				break;
			int[][] pos = new int[P][G];
			for(int i = 0; i < G; ++i)
				for(int j = 0; j < P; ++j)
					pos[j][i] = sc.nextInt() - 1;
			
			int S = sc.nextInt();
			for(int i = 0; i < S; ++i)
			{
				int K = sc.nextInt();
				int[] score = new int[K];
				for(int j = 0; j < K; ++j)
					score[j] = sc.nextInt();
				int[] pilotScore = new int[P];
				int max = 0;
				for(int j = 0; j < P; ++j)
				{
					int sum = 0;
					for(int k = 0; k < G; ++k)
					{
						int p = pos[j][k];
						if(p < K)
							sum += score[p];
					}
					pilotScore[j] = sum;
					max = Math.max(max, sum);
				}
				
				boolean first = true;
				for(int j = 0; j < P; ++j)
					if(pilotScore[j] == max)
					{
						if(first)
							first = false;
						else
							out.print(" ");
						out.print(j + 1);
					}
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