package v005;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BinomialShutdown_UVa530 {
	
	static TreeMap<State, Long> memo = new TreeMap<State, Long>();
	
	static long nCk(long n, long k)
	{
		if(n < k)
			return 0;
		if(k == 0 || k == n)
			return 1;
		State s = new State(n, k);
		Long ans = memo.get(s);
		if(ans != null)
			return ans;
		ans = nCk(n - 1, k - 1) + nCk(n - 1, k);
		memo.put(s, ans);
		return ans;
	}
	
	public static void sol() throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			long n = sc.nextLong(), k = sc.nextLong();
			if(n == 0 && k == 0)
				break;
			if(n - k < k)
				k = n - k;
			long ans;
			if(k == 1)
				ans = n;
			else
				ans = nCk(n, k);
			out.format("%d\n", ans);
		}
		out.flush();
	}
	
	public static void main(String[] args) {new Thread(null, new Runnable() { public void run() {try {
		sol();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}}, "2",1<<30).start();}
	
	static class State implements Comparable<State>
	{
		long n, k;
		
		State(long x, long y) {n = x; k = y; }
		
		public int compareTo(State s)
		{
			if(n != s.n)
				return n > s.n ? 1 : -1;
			if(k != s.k)
				return k > s.k ? 1 : -1;
			return 0;
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


	}
}
