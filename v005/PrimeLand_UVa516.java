package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PrimeLand_UVa516 {

	static ArrayList<Integer> primes;
	
	static void sieve(int N)
	{
		boolean[] isComposite = new boolean[N];
		primes = new ArrayList<Integer>(N / 10);
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				if(1l * i * i < N)
					for(int j = i * i; j < N; j += i)
						isComposite[j] = true;
			}
	}
	
	static ArrayList<Pair> primePowers(int N)
	{
		ArrayList<Pair> powers = new ArrayList<Pair>();
		
		int idx = 0, p = primes.get(idx);
		while(p * p <= N)
		{
			int e = 0;
			while(N % p == 0) { N /= p; ++e; }
			if(e != 0)
				powers.add(new Pair(p, e));
			p = primes.get(++idx);
		}
		if(N != 1)
			powers.add(new Pair(N, 1));
		return powers;
	}
			
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		sieve(10000);
		while(true)
		{
			String line = sc.nextLine();
			
			if(line.equals("0"))
				break;
			StringTokenizer st = new StringTokenizer(line);
			int N = 1;
			while(st.hasMoreTokens())
			{
				int p = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				while(e-- > 0)
					N *= p;
			}
			ArrayList<Pair> ans = primePowers(N - 1);
			for(int i = ans.size() - 1; i >= 0; --i)
			{
				Pair f = ans.get(i);
				sb.append(f.p + " " + f.e + (i == 0 ? "\n": " "));
			}
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Pair
	{
		int p, e;
		
		Pair(int x, int y) { p = x; e = y; }
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
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}