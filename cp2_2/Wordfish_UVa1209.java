package cp2_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Wordfish_UVa1209 {
	

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			String[] cands = new String[21];
			cands[10] = sc.next();
			
			char[] perm = cands[10].toCharArray();
			for(int i = 11; i < 21; ++i)
			{
				permute(perm, 1);
				cands[i] = new String(perm);
			}
			
			perm = cands[10].toCharArray();
			for(int i = 9; i >= 0; --i)
			{
				permute(perm, -1);
				cands[i] = new String(perm);
			}
			int max = -1, idx = -1;
			for(int i = 0; i < 21; ++i)
			{
				int min = 30;
				for(int j = 0; j < cands[i].length() - 1; ++j)
					min = Math.min(min, Math.abs(cands[i].charAt(j) - cands[i].charAt(j + 1)));
				if(min > max)
				{
					max = min;
					idx = i;
				}
			}
			out.printf("%s%d\n", cands[idx], max);
		}
		out.flush();
		out.close();
	}
	
	static boolean permute(char[] c, int nxt) //nxt = 1 for next and = -1 for prev 
	{
		// 1. finds the largest k, that c[k] < c[k+1]
		int first = getFirst(c, nxt);
		if(first == -1)
			return false;
		
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.length - 1;
		while (nxt * (c[first] - c[toSwap]) >= 0)
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
	static int getFirst(char[] c, int nxt) 
	{
		for ( int i = c.length - 2; i >= 0; i--)
			if (nxt * (c[i] - c[i + 1]) < 0)
				return i;
		return -1;
	}

	
	static void swap(char[] c,int i, int j) 
	{
		char tmp = c[i];
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
