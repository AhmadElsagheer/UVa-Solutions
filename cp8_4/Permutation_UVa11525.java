package cp8_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Permutation_UVa11525 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int k = sc.nextInt();
			SegmentTree st = new SegmentTree(k);
			while(k-->0)
			{
				int idx = sc.nextInt() + 1, q = st.kthNumber(idx);
				sb.append(q).append(k == 0 ? '\n' : ' ');
				st.clear(q);
			}
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class SegmentTree
	{
		int[] sTree;
		int N;
		
		SegmentTree(int k)
		{
			N = 1;
			while(N < k)
				N <<= 1;
			sTree = new int[N<<1];
			build();
		}
		
		void build()
		{
			Arrays.fill(sTree, 1);
			for(int i = N - 1; i > 0; --i)
				sTree[i] = sTree[i<<1] + sTree[i<<1|1];
		}
		
		void clear(int idx)
		{
			idx += N - 1;
			sTree[idx] = 0;
			while(idx > 1)
			{
				idx >>= 1;
				sTree[idx] = sTree[idx<<1] + sTree[idx<<1|1];
			}
		}
		
		int kthNumber(int k)
		{
			return kthNumber(1, 1, N, k);
		}
		
		int kthNumber(int node, int b, int e, int k)
		{
			if(b == e)
				return b;
			int mid = b + e >> 1;
			if(sTree[node<<1] >= k)
				return kthNumber(node<<1, b, mid, k);
			return kthNumber(node<<1|1, mid + 1, e, k - sTree[node<<1]);
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