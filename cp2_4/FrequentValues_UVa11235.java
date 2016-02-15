package cp2_4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FrequentValues_UVa11235 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			int q = sc.nextInt();
			int[] A = new int[n];
			for(int i = 0; i < n; ++i)
				A[i] = sc.nextInt();
			int[][] intervals = new int[3][n];
			int[] findInterval = new int[n];
			int nxt = 0;
			for(int i = 0, j = 0, k = 1; i < n; ++i)
			{
				findInterval[i] = nxt;
				if(i < n - 1 && A[i] == A[i + 1])
					k++;
				else
				{
					intervals[0][nxt] = j;
					intervals[1][nxt] = i;
					intervals[2][nxt++] = k;
					k = 1;
					j = i + 1;
				}
			}
			
	
			RMQ rmq = new RMQ(Arrays.copyOf(intervals[2], nxt));
			while(q-->0)
			{
				int a, b;
				int i = findInterval[a = sc.nextInt() - 1], j = findInterval[b = sc.nextInt() - 1];
				int idx = rmq.query(i + 1, j - 1);
				int ans = idx == -1 ? 0  : intervals[2][idx];
				if(i != j)
					ans = Math.max(ans, Math.max(intervals[1][i] - a + 1, b - intervals[0][j] + 1));
				else
					ans = b - a + 1;
				out.println(ans);
			}
				
		}
		out.flush();

	}
	
	static class RMQ {                                          
		  
		int A[], SpT[][];
		
		RMQ(int[] A) 
		{
			if(log2 == null)
				computeLog();
			int n = A.length;	this.A = A;
			int k = log2[n] + 1;
			SpT = new int[n][k];
			
			for (int i = 0; i < n; i++) 
				SpT[i][0] = i; 
		

			for (int j = 1; (1<<j) <= n; j++) 				
				for (int i = 0; i + (1<<j) - 1 < n; i++)    
					if (A[SpT[i][j-1]] >= A[SpT[i+(1<<(j-1))][j-1]])
						SpT[i][j] = SpT[i][j-1];
					else
						SpT[i][j] = SpT[i+(1<<(j-1))][j-1];
		 }

		  int query(int i, int j) 
		  {
			  if(i > j)
				  return -1;
			  int k = log2[j - i + 1];		 // 2^k <= (j-i+1)

			  if (A[SpT[i][k]] >= A[SpT[j-(1<<k)+1][k]]) 
				  return SpT[i][k];
			  else
				  return SpT[j-(1<<k)+1][k];
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
