package v107;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
 
public class PrimeFrequency_UVa10789 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean[] isPrime = new boolean[2001];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i * i <= 2000; ++i)
			if(isPrime[i])
				for(int j = i * i; j <= 2000; j += i)
					isPrime[j] = false;
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			String s = sc.next();
			int[] f = new int[128];
			for(int i = 0; i < s.length(); ++i)
				f[s.charAt(i)]++;
			StringBuilder ans = new StringBuilder();
			for(int i = 0; i < 128; ++i)
				if(isPrime[f[i]])
					ans.append((char)i);
			if(ans.length() == 0)
				ans.append("empty");
			out.printf("Case %d: %s\n", t, ans.toString());
		}
		out.flush();
		out.close();
	}
		
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;
 
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader s){	br = new BufferedReader(s);}
 
		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
 
		public int nextInt() throws IOException {return Integer.parseInt(next());}
 
		public long nextLong() throws IOException {return Long.parseLong(next());}
 
		public String nextLine() throws IOException {return br.readLine();}
 
		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}
 
		public boolean ready() throws IOException {return br.ready();}
 
 
	}
} 