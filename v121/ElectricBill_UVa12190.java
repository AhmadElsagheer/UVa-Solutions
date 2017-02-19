package v121;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ElectricBill_UVa12190 {

	static int[] p = new int[]{2, 3, 5};
	static int[] f = new int[]{100, 9900, 990000};
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int A = sc.nextInt(), B = sc.nextInt();
			if(A == 0 && B == 0)
				break;
			int totalWatts = moneyToWatt(A);
			int ans = -1, lo = 0, hi = totalWatts>>1;
			while(ans == -1)
			{
				int myWatts = lo + (hi - lo) / 2, hisWatts = totalWatts - myWatts;
			
				long billDiff = wattToMoney(hisWatts) - wattToMoney(myWatts);
				
				if(billDiff == B)
					ans = wattToMoney(myWatts);
				else
					if(billDiff > B)
						lo = myWatts + 1;
					else
						hi = myWatts - 1;
			}
			out.println(ans);
		}
		
		out.flush();
		out.close();
	}
	
	static int moneyToWatt(int money)
	{
		int watts = 0;
		for(int i = 0; i < 3; ++i)
		{
			int m = Math.min(money, f[i] * p[i]);
			money -= m;
			watts += m / p[i];
		}
		return watts + money / 7;
	}
	
	static int wattToMoney(int watts)
	{
		int money = 0;
		for(int i = 0; i < 3; ++i)
		{
			int w = Math.min(watts, f[i]);
			money +=  w * p[i];
			watts -= w;
		}
		return money + watts * 7;
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

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
