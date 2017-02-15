package regionals.latinAmerica2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BloggerLanguage {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int Q = sc.nextInt();
			String pattern = sc.next(), text = sc.next();
			int p = pattern.length();
			SegmentTree st = new SegmentTree(mskArray(text, pattern), p);
			int[] mismatches = new int[1<<p];
			for(int i = 0; i < 1<<p; ++i)
				for(int j = 0; j < p; ++j)
				{
					int x = i & (1<<j);
					int y = Character.isLowerCase(pattern.charAt(j)) ? 0 : 1;
					if(x == 0 && y == 1 || x != 0 && y == 0)
						++mismatches[i];
				}
			while(Q-->0)
			{
				int i = sc.nextInt(), j = sc.nextInt();
				long msk = j - i + 1 >= p ? st.query(i, j - p + 1) : 0;
				int ans = -1;
				for(int k = 0; k < 1<<p; ++k)
					if((msk & ((long)1<<k)) != 0)
						ans = Math.max(ans, mismatches[k]);
				out.println(ans);
				
				if(j - p + 1 >= 1)
					st.updateRange(1, j - p + 1);
				if(i - p >= 1)
					st.updateRange(1, i - p);
				st.updatePoint(Math.max(1, j - p + 2), j);
				if(i > 1)
					st.updatePoint(Math.max(1, i - p + 1), i - 1);
			}
		}
		out.flush();
	}
	
	static long[] mskArray(String txt, String patt)
	{
		int t = txt.length(), p = patt.length();
		String txt2 = txt.toLowerCase();
		patt = patt.toLowerCase();
		int N = (int) Math.pow(2, Math.ceil(Math.log(t)/Math.log(2)));
		long[] ret = new long[N + 1];
		for(int i = 0; i + p <= t; ++i)
		{
			int msk = 0;
			for(int j = 0; j < p; ++j)
				if(txt2.charAt(i + j) == patt.charAt(j))
				{
					if(Character.isUpperCase(txt.charAt(i + j)))
						msk |= 1<<j;
				}
				else
				{
					msk = -1;
					break;
				}
			if(msk != -1)
				ret[i + 1] = (long)1<<msk;
		}
		
		return ret;
	}
	
	static class SegmentTree
	{
		int N, p, lazy[];
		long[] sTree, array;
		
		SegmentTree(long[] in, int p)
		{
			array = in; N = in.length - 1; this.p = p;
			sTree = new long[N<<1];
			lazy = new int[N<<1];
			build(1, 1, N);
		}
		
		void build(int node, int b, int e)
		{
			if(b == e)
				sTree[node] = array[b];
			else
			{
				build(node<<1, b, (b + e)>>1);
				build((node<<1) + 1, ((b + e)>>1) + 1, e);
				sTree[node] = sTree[node<<1] | sTree[(node<<1) + 1];
			}
		}
		
		void updateRange(int i, int j)
		{
			updateRange(1, 1, N, i, j);
		}
		
		void updateRange(int node, int b, int e, int i, int j)
		{
			if(b > j || e < i)
				return;
			if(i <= b && e <= j)
			{
				long cur = sTree[node], nxt = 0;
				for(int k = (1<<p) - 1; k >= 0; --k)
					if((cur & ((long)1<<k)) != 0)
						nxt |= (long)1<<(k ^ ((1<<p) - 1));
				sTree[node] = nxt;
				if(b != e)
					lazy[node] = lazy[node] ^ 1;
			}
			else
			{
				propagate(node, b, e);
				updateRange(node<<1, b, b+e>>1, i, j);
				updateRange((node<<1) + 1, (b+e>>1) + 1, e, i, j);
				sTree[node] = sTree[node<<1] | sTree[(node<<1) + 1];
			}
		}
		
		void updatePoint(int i, int j)
		{
			updatePoint(1, 1, N, i, j);
		}
		
		void updatePoint(int node, int b, int e, int i, int j)
		{
			if(b > j || e < i)
				return;
			propagate(node, b, e);
			if(b == e)
			{
				long msk = sTree[node];
				if(msk == 0)
					return;
				int flip = j - b + 1;
				int state = 0;
				while(msk != 1)
				{
					++state;
					msk >>= 1;
				}
				sTree[node] = (long)1<<(state ^ ((1<<flip) - 1));

			}
			else
			{
				updatePoint(node<<1, b, b+e>>1, i, j);
				updatePoint((node<<1) + 1, (b+e>>1)+1, e, i, j);
				sTree[node] = sTree[node<<1] | sTree[(node<<1) + 1];
			}
		}
		
		void propagate(int node, int b, int e)
		{
			if(lazy[node] == 1)
			{
				updateRange(node<<1, b, b+e>>1, b, b+e>>1);
				updateRange((node<<1) + 1, (b+e>>1) + 1, e, (b+e>>1) + 1, e);
				lazy[node] = 0;
			}
		}
		
		long query(int i, int j)
		{
			return query(1, 1, N, i, j);
		}
		
		long query(int node, int b, int e, int i, int j)
		{
			if(i > e || j < b)
				return 0;
			if(i <= b && e <= j)
				return sTree[node];
			propagate(node, b, e);
			return query(node<<1, b, b+e>>1, i, j) | query((node<<1) + 1, (b+e>>1)+1, e, i, j);
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
		
		public boolean ready() throws IOException {return br.ready();}


	}

}