package cp2_4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class IntervalProduct_UVa12532 {

	static char[] sym = new char[] { '-', '0', '+' };
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int N = sc.nextInt(), Q = sc.nextInt();
			int n = 1;
			while(n < N) n <<= 1;
			
			int[] arr = new int[n + 1];
			Arrays.fill(arr, 1);
			for(int i = 1; i <= N; ++i)
			{
				int x = sc.nextInt();
				if(x != 0)
					x /= Math.abs(x);
				arr[i] = x;
			}

			StringBuilder sb = new StringBuilder();
			SegmentTree st = new SegmentTree(arr);
			while(Q-->0)
			{
				char c = sc.next().charAt(0);
				if(c == 'C')
				{	
					int idx = sc.nextInt(), val = sc.nextInt();
					if(val != 0)
						val /= Math.abs(val);
					st.update(idx, val);
				}
				else
				{
					int r = st.query(sc.nextInt(), sc.nextInt());
					sb.append(sym[r + 1]);
				}
			}
			out.println(sb);
		}
		out.flush();
		out.close();
	}

	static class SegmentTree
	{
		int N, sTree[], array[];
		
		SegmentTree(int[] in)
		{
			N = in.length - 1;
			array = in;
			sTree = new int[N<<1];
			build(1, 1, N);
		}
		
		void build(int node, int b, int e)
		{
			if(b == e)
				sTree[node] = array[b];
			else
			{
				build(node<<1, b, (b + e) / 2);
				build((node<<1) + 1, (b + e) / 2 + 1, e);
				sTree[node] = sTree[node<<1] * sTree[(node<<1) + 1];
			}
		}
		
		void update(int idx, int val)
		{
			idx += N - 1;
			sTree[idx] = val;
			while(idx > 1)
			{
				idx >>= 1;
				sTree[idx] = sTree[idx<<1] * sTree[(idx<<1) + 1];
			}
		}
		
		int query(int i, int j)
		{
			return query(1, 1, N, i, j);
		}
		
		int query(int node, int b, int e, int i, int j)
		{
			if(j < b || e < i)
				return 1;
			if(i <= b && e <= j)
				return sTree[node];
			return query(node<<1, b, (b + e) / 2, i, j) * query((node<<1) + 1, (b + e) / 2 + 1, e, i, j);
		}
		
	}
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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