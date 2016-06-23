package v116;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class ReversePrime_UVa11610 {

	static final int upperBound = (int)1e6;
	static TreeSet<Integer> reversePrimes;
	static ArrayList<Integer> primes;
	static TreeMap<Integer, Integer> map;
	
	static void sieve(int N)
	{
		reversePrimes = new TreeSet<Integer>();
		primes = new ArrayList<Integer>(N / 10);
		boolean[] isComposite = new boolean[N];
		for(int i = 2; i < N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				reversePrimes.add(reverse(i));
				if(1l * i * i < N)
					for(int j = i * i; j < N; j += i)
						isComposite[j] = true;
			}		
	}
	
	static int reverse(int x)
	{
		int y = 0;
		for(int i = 0; i < 7; ++i, x /= 10)
			y = y * 10 + x % 10;
		return y;
	}
	
	static int primeFactors(int x)
	{
		int ret = 0;
		for(int p: primes)
		{
			if(p * p > x)
				break;
			while(x % p == 0)
			{
				++ret;
				x /= p;
			}
		}
		if(x > 1)
			++ret;
		return ret;
	}
	
	static int[] build()
	{
		sieve(upperBound);
		map = new TreeMap<Integer, Integer>();
		int[] a = new int[reversePrimes.size() + 1];
		int k = 1;
		for(int x: reversePrimes)
		{
			map.put(x, k);
			a[k++] = primeFactors(x);
		}
		return a;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		int[] a = build();
		FenwickTree cum = new FenwickTree(a);
		FenwickTree fix = new FenwickTree(a.length);
		
		while(sc.ready())
		{
			char c = sc.next().charAt(0);
			
			if(c == 'q')
			{
				int x = fix.fixRange(sc.nextInt() + 1);
				out.println(cum.query(x));
			}
			else
			{
				int x = map.get(sc.nextInt());
				cum.update(x, -a[x]);
				fix.update(x, 1);
			}
		}
		out.flush();
		out.close();
	}
	
	static class FenwickTree
	{
		int[] ft;
		
		FenwickTree(int n) { ft = new int[n]; }
		
		FenwickTree(int[] in)
		{
			ft = new int[in.length + 1];
			for(int i = 1; i < in.length; ++i)
				update(i, in[i]);
		}
		
		void update(int k, int val)
		{
			while(k < ft.length)
			{
				ft[k] += val;
				k += k & -k;
			}
		}
		
		int query(int k)
		{
			int sum = 0;
			while(k > 0)
			{
				sum += ft[k];
				k ^= k & -k;
			}
			return sum;
		}
		
		int fixRange(int k)
		{
			int lo = k, hi = ft.length - 1;
			while(lo <= hi)
			{
				int mid = lo + hi >> 1;
				int count = mid - query(mid);
				if(count == k)
					return mid;
				if(count > k)
					hi = mid - 1;
				else
					lo = mid + 1;
			}
			return -1;
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