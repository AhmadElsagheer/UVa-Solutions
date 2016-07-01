package v122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * Implementation with Deque, TreeMap and TreeSet : O(C log min(C, P))
 * Implementation with Deque only (add and remove): O(C * min(C, P))
 */
public class ThatIsYourQueue_UVa12207 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int P = sc.nextInt(), C = sc.nextInt(), x;
			if(P == 0)
				break;
			TreeMap<Integer, Integer> skipCount = new TreeMap<Integer, Integer>();
			TreeSet<Integer> isEmitted = new TreeSet<Integer>();
			Deque<Integer> dq = new LinkedList<Integer>();
			for(int i = 1; i <= C && i <= P; ++i)
				dq.addLast(i);
			out.printf("Case %d:\n", tc++);
			while(C-->0)
			{
				char c = sc.next().charAt(0);
				if(c == 'E')
				{
					x = sc.nextInt();
					dq.addFirst(x);
					Integer f = skipCount.get(x);
					if(f == null)
						f = 0;
					skipCount.put(x, f + 1);
					isEmitted.add(x);
				}
				else
				{
					while(true)
					{
						x = dq.removeFirst();
						if(isEmitted.remove(x))
							break;
						Integer f = skipCount.get(x);
						if(f == null || f == 0)
							break;
						skipCount.put(x, f - 1);
					}
					out.println(x);
					dq.addLast(x);
				}
			}
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