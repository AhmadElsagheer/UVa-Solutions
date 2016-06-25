package v106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DeterminatePrime_UVa10650 {

	static final int upperBound = 32000;
	static ArrayList<Integer> primes;
	
	static void sieve(int N)
	{
		boolean[] isComposite = new boolean[N + 1];
		primes = new ArrayList<Integer>(N / 10);
		for(int i = 2; i <= N; ++i)
			if(!isComposite[i])
			{
				primes.add(i);
				for(int j = i * i; j <= N; j += i)
					isComposite[j] = true;
			}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		sieve(upperBound);
		int size = primes.size();
		
		while(true)
		{
			int l = sc.nextInt(), r = sc.nextInt();
			if(l == 0 && r == 0)
				break;
			
			if(r < l) {	int tmp = l; l = r; r = tmp; }
			
			for(int i = 0, j; i < size - 2; ++i)
			{
				int p1 = primes.get(i), p2 = primes.get(i + 1), d = p2 - p1;

				LinkedList<Integer> dp = new LinkedList<Integer>();
				dp.add(p1); dp.add(p2);

				for(j = i + 2; j < size; ++j)
				{
					int p = primes.get(j);
					if(p - dp.getLast() != d)
						break;
					dp.add(p);	
				}
				
				i = j - 2;
				if(dp.size() == 2 || dp.getLast() > r || p1 < l)
					continue;
				boolean first = true;
				for(int x: dp)
					if(first)
					{
						first = false;
						sb.append(x);
					}
					else
						sb.append(" " + x);
				sb.append("\n");
			}
		}
		out.print(sb);
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
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}