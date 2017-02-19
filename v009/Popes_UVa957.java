package v009;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Popes_UVa957 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		while(sc.ready())
		{
			int Y = sc.nextInt(), p = sc.nextInt();
			
			int[]a = new int[p];
			for(int i = 0; i < p; i++) a[i] = sc.nextInt();
			
			int L = a[p-1], max = 0, start = -1, end = -1, cur = 0;
			for(int i = 0, j = 0, y = 1; y <= L; y++)
			{
				while(i < p && a[i] == y) {cur++; i++;}
				while(j < p && a[j] < y - Y + 1) {cur--; j++;}
				
				if(cur > max)
				{
					max = cur;
					start = a[j];
					end = y;
				}
			}
			System.out.println(max+" "+start+" "+end);
			
		}
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
