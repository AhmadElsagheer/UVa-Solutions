package cp5_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class MaximumGCD_UVa11827 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String[] in = sc.nextLine().split(" ");
			int[] a = new int[in.length];
			for(int i = 0; i < a.length; i++)
				a[i] = Integer.parseInt(in[i]);
			int max = 1;
			for(int i = 0; i < a.length; i++)
				for(int j = i + 1; j < a.length; j++)
					max = Math.max(max, gcd(a[i], a[j]));
			System.out.println(max);
		}
		
	}
	
	static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); }
	
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
