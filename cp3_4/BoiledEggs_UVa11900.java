package cp3_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BoiledEggs_UVa11900 {

	

	public static void main(String[] args) throws IOException {
		
		
		InputReader in = new InputReader(System.in);
		
		int tc = in.nextInt();
		for(int k = 1; k <= tc; k++)
		{
			int n = in.nextInt(), p = in.nextInt(), q = in.nextInt();
			int[] a = new int[n];
			for(int i = 0; i < n; i++)
				a[i] = in.nextInt();
			Arrays.sort(a);
			int max = 0, w = 0;
			for(int i = 0; i < n && max < p; i++)
				if(a[i] + w <= q)
				{
					w += a[i];
					max++;
				}
				else
					break;
			System.out.printf("Case %d: %d\n",k,max);
			
		}
		
	}
	
	static class InputReader {
		StringTokenizer st;
		BufferedReader br;
		
		public InputReader(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public String nextLine() throws IOException {return br.readLine();}
		
		public boolean ready() throws IOException {return br.ready();}
		
	}
	
}
