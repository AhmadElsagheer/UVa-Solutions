package cp5_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class IrreducableBasicFractions_UVa10179 {

	public static void main(String[] args) throws IOException {
		
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) break;
			sb.append(phi(n)+"\n");
		}
		System.out.print(sb);
	}
	
	static int phi(int n)
	{
		int res = n;
		for(long i = 2; i * i <= n; i++ )
			if(n%i == 0)
			{
				res -= res / i;
				while(n%i == 0) n /= i;
			}
		if(n > 1)
			res -= res / n;
		return res;
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
