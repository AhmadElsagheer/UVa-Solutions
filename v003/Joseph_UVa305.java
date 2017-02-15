package v003;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Joseph_UVa305 {

	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int[] ans = {-1, 2, 7, 5, 30, 169, 441, 1872, 7632, 1740, 93313, 459901, 1358657, 2504881};
		while(true)
		{
			int k = sc.nextInt();
			if(k == 0)
				break;
			out.println(ans[k]);
//			main: for(int m = k; true; ++m)
//			{
//				ArrayList<Integer> arr = new ArrayList<>();
//				for(int i = 0; i < k; ++i)
//					arr.add(0);
//				for(int i = 0; i < k; ++i)
//					arr.add(1);
//				int  idx = 0;
//				while(true)
//				{
//					
//					idx = (idx + m) % (arr.size());
//					if(arr.get(idx) == 0)
//						break;
//					arr.remove(idx);
//					if(arr.size() == k)
//					{
//						out.print(m + 1 + ", ");
//						break main;
//					}
//				}
//			}
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