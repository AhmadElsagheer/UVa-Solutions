package v007;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DividingUp_UVa711 {

	static Boolean[][] memo;
	static int[] marbles;
	
	static boolean bt(int idx, int sum)
	{
		if(idx == 7)
			return sum == 0;
		if(memo[idx][sum] != null)
			return memo[idx][sum];
		int x = marbles[idx];
		boolean ret = false;
		for(int k = 0; !ret && k <= x && sum - k * idx >= 0; ++k)
			ret |= bt(idx + 1, sum - k * idx);
		return memo[idx][sum] = ret;
	}
	
	public static void sol() throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			marbles = new int[7]; 
			int sum = 0;
			for(int i = 1; i <= 6; ++i)
			{
				int x = sc.nextInt();
				sum += x * i;
				marbles[i] = x;
			}
			if(sum == 0)
				break;
			out.printf("Collection #%d:\n", tc++);
			if(sum%2 == 1)
				out.println("Can't be divided.");
			else
			{
				sum >>= 1;
				memo = new Boolean[7][sum + 1];
				out.println(bt(1, sum)?"Can be divided." : "Can't be divided.");
			}
			out.println();
		}
		out.flush();
		out.close();
	}
	
	
	public static void main(String[] args) {new Thread(null, new Runnable() { public void run() {try {
		sol();
	} catch (IOException e) {
		
		e.printStackTrace();
	}}}, "2",1<<26).start();}
	
	
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