package v111;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class HoaxOrWhat_UVa11136 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			long ans = 0;
			while(N-- > 0)
			{
				int k = sc.nextInt();
				while(k-->0)
				{
					int x = sc.nextInt();
					Integer f = map.get(x);
					if(f == null)
						f = 0;
					map.put(x, f + 1);
				}					

				Entry<Integer, Integer> min = map.firstEntry(), max = map.lastEntry();
				ans += max.getKey() - min.getKey();
				if(max.getValue() == 1)
					map.remove(max.getKey());
				else
					map.put(max.getKey(), max.getValue() - 1);

				if(min.getValue() == 1)
					map.remove(min.getKey());
				else
					map.put(min.getKey(), min.getValue() - 1);
			}

			out.println(ans);
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
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}