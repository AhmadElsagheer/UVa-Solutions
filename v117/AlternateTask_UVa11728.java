package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlternateTask_UVa11728 {

	static ArrayList<Integer> primes;
	
	static void sieve(int N)
	{
		primes = new ArrayList<Integer>();
		boolean[] isComposite = new boolean[N];
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				for(int j = i * i; j < N; j += i)
					isComposite[j] = true;
			}
	}
	
	static int sumDiv(int N)
	{
		int ans = 1, idx = 0, p = primes.get(0);
		while(p * p <= N)
		{
			int e = 1;
			while(N % p == 0) { N /= p; ++e; }
			ans *= (pow(p, e) - 1) / (p - 1);
			p = primes.get(++idx);
		}
		if(N != 1)
			ans *= (pow(N, 2) - 1) / (N - 1);
		return ans;
	}
	
	static int pow(int p, int e)
	{
		int ans = 1;
		while(e > 0)
		{
			if((e & 1) == 1)
				ans *= p;
			p *= p;
			e >>= 1;
		}
		return ans;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		sieve(100);
		int[] ans = new int[1001];
		Arrays.fill(ans, -1);
		ans[1] = 1;
		for(int i = 2; i <= 1000; ++i)
		{
			int x = sumDiv(i);
			if(x <= 1000)
				ans[x] = i;
		}
		
		int tc = 1;
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			out.printf("Case %d: %d\n", tc++, ans[N]);
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

		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}

		public boolean ready() throws IOException {return br.ready();}

		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}