package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HowBigIsIt_UVa10012 {
	

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), order[] = new int[n];
			double[] r = new double[n];
			double min = 0;
			for(int i = 0; i < n; ++i)
			{
				order[i] = i;
				min += r[i] = sc.nextDouble();
			}
			min *= 2.0;
			double[] pos = new double[n];
			do
			{
				pos[order[0]] = r[order[0]];
				for(int i = 1; i < n; ++i)
				{
					double curPos = 0;
					for(int j = 0; j < i; ++j)
						curPos = Math.max(curPos, pos[order[j]] + 2.0 * Math.sqrt(r[order[i]] * r[order[j]]));
					pos[order[i]] = curPos;
				}
				double left = 0, right = 0;
				for(int i = 0; i < n; ++i)
				{
					left = Math.min(left, pos[order[i]] - r[order[i]]);
					right = Math.max(right, pos[order[i]] + r[order[i]]);
				}
				min = Math.min(right - left, min);

			}while(nextPermutation(order));
			out.printf("%.3f\n", min);
		}			
		out.flush();
		out.close();
	}
	
	
	static boolean nextPermutation(int[] c) 
	{
		// 1. finds the largest k, that c[k] < c[k+1]
		int first = getFirst(c);
		if(first == -1)
			return false;
		
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.length - 1;
		while (c[first] >= c[toSwap])
			--toSwap;
		
		// 3. swap elements with indexes first and last
		swap(c, first++, toSwap);
		
		// 4. reverse sequence from k+1 to n (inclusive) 
		toSwap = c.length - 1;
		while(first < toSwap)
			swap(c, first++, toSwap--);
		return true;
	}

	// finds the largest k, that c[k] < c[k+1]
	// if no such k exists (there is not greater permutation), return -1
	static int getFirst(int[] c) 
	{
		for ( int i = c.length - 2; i >= 0; i--)
			if (c[i] < c[i + 1])
				return i;
		return -1;
	}

	
	static void swap(int[] c,int i, int j) 
	{
		int tmp = c[i];
		c[i] = c[j];
		c[j] = tmp;
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine(), ",| ");
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
