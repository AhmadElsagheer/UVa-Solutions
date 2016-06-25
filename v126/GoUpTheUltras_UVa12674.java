package v126;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GoUpTheUltras_UVa12674 {

	static final int lim = 150000;
	
	public static void main(String[] args) throws IOException {
		
		computeLog();
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		while(sc.ready())
		{
			int N = sc.nextInt();
			int[] height = new int[N];
			for(int i = 0; i < N; ++i)
				height[i] = sc.nextInt();
			int[] hi_left = new int[N], hi_right = new int[N];
			
			hi_left[0] = -1;
			for(int i = 1; i < N; ++i)
			{
				hi_left[i] = i - 1;
				while(hi_left[i] != -1 && height[hi_left[i]] <= height[i])
					hi_left[i] = hi_left[hi_left[i]];
			}
			
			hi_right[N - 1] = N;
			for(int i = N - 2; i >= 0; --i)
			{
				hi_right[i] = i + 1;
				while(hi_right[i] != N && height[hi_right[i]] <= height[i])
					hi_right[i] = hi_right[hi_right[i]];
			}

			RMinQ min = new RMinQ(height);
			
			int c = 0;
			for(int i = 1; i < N - 1; ++i)
			{
				int h = 0;
				if(hi_left[i] != -1)
					h = height[min.query(hi_left[i], i - 1)];
				if(hi_right[i] != N)
					h = Math.max(h, height[min.query(i + 1, hi_right[i])]);

				if(height[i] -  h >= lim)
				{
					sb.append(c != 0 ? " " : "").append(i + 1);
					++c;
				}
			}
			sb.append("\n");
			
		}
		
		out.print(sb);
		out.flush();
		out.close();
		
	}
	
	static int[] log2;
	
	static void computeLog()
	{
		log2 = new int[300000];
		log2[1] = 0;
		for(int i = 1; i <= 16; ++i)
			for(int j = 1<<i; j <= 1<<(i + 1); ++j)
				log2[j] = i;
		
	}
	static class RMinQ {                                          
		  
		int A[], SpT[][];
		
		RMinQ(int[] A) 
		{
			int n = A.length;	this.A = A;
			int k = log2[n] + 1;
			SpT = new int[n][k];
			
			for (int i = 0; i < n; i++) 
				SpT[i][0] = i; 					// RMQ of sub array starting at index i and of length 2^0=1
		
			//overall time complexity = O(n log n)
			for (int j = 1; (1<<j) <= n; ++j) 				
				for (int i = 0; i + (1<<j) - 1 < n; ++i)    
					if (A[SpT[i][j-1]] < A[SpT[i+(1<<(j-1))][j-1]])
						SpT[i][j] = SpT[i][j-1];    		// start at index i of length 2^(j-1)
					else                  					// start at index i+2^(j-1) of length 2^(j-1)
						SpT[i][j] = SpT[i+(1<<(j-1))][j-1];
		 }

		  int query(int i, int j) 
		  {
			  
		    int k = log2[j - i + 1]; // 2^k <= (j-i+1)
		    
		    if (A[SpT[i][k]] <= A[SpT[j-(1<<k)+1][k]]) 
		    	return SpT[i][k];
		    else
		    	return SpT[j-(1<<k)+1][k];
		  } 
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
