package v113;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class EnumeratingRationalNumbers_UVa11327 {

	static int phi(int n)
	{
		if(n == 1) return 2;
		
		int res = n;
		for(int i = 2; i * i <= n; i++)
			if(n%i == 0)
			{
				res -= res / i;
				while(n%i == 0) n /= i;
			}
		if(n > 1)
			res -= res / n;
		return res;
	}
	
	static int gcd(int a, int b) {return b == 0 ? a : gcd(b, a%b);}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int[] phi = new int[200001];
		for(int i = 1; i <= 200000; i++)
			phi[i] = phi(i);
		while(true)
		{
			long k = sc.nextLong();
			if(k == 0) break;
			for(int i = 1; i <= 200000; i++)
				if(phi[i] < k)
					k -= phi[i];
				else
				{
					for(int j = 0; j <= i; j++)
						if(gcd(i,j) == 1)
							if(k==1) { sb.append(j+"/"+i+"\n"); break; }
							else k--;
					break;
				}
		}
		System.out.print(sb);
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
