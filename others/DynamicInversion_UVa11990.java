package others;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class DynamicInversion_UVa11990 {

	static final int INF = (int)1e9;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int n = sc.nextInt(), m = sc.nextInt();
			int N = 1, lvls = 1;
			while(N < n)
			{
				N <<= 1;
				lvls++;
			}
			int[] arr = new int[N + 1];
			for(int i = 1; i <= n; ++i)
				arr[i] = sc.nextInt();
			for(int i = n + 1; i <= N; ++i)
				arr[i] = i;
			SegmentTree st = new SegmentTree(arr, lvls);
			while(m-->0)
			{
				out.println(st.inversionIndex);
				int x = sc.nextInt();
				st.remove(x);
			}
		}
		out.flush();
		out.close();

	}

	static class SegmentTree
	{
		int N, levels;
		long inversionIndex;
		int[][] arr, indexOf, inv;
		
		SegmentTree(int[] in, int l)
		{
			N = in.length - 1;
			levels = l;
			
			inv = new int[levels][N + 1];
			indexOf = new int[levels][N + 1];
			arr = new int[levels][N + 1];

			arr[levels-1] = in;
			build(0, 1, N);
		}
		
		void build(int lvl, int b, int e)
		{
			if(lvl == levels - 1)
			{
				indexOf[lvl][arr[lvl][b]] = b;
				return;
			}
			int mid = b + e >> 1;
			build(lvl + 1, b, mid);
			build(lvl + 1, mid + 1, e);
			int xInv = 0;
			for(int k = b, i = b, j = mid + 1; k <= e; ++k)
			{
				int x = i <= mid ? arr[lvl+1][i] : INF, y = j <= e ? arr[lvl+1][j] : INF;
				if(x < y)
				{
					arr[lvl][k] = x;
					indexOf[lvl][x] = k;
					increment(lvl, k, xInv);
					if(k < N)
						increment(lvl, k + 1, -xInv);
					++i;
				}
				else
				{
					arr[lvl][k] = y;
					indexOf[lvl][y] = k;
					increment(lvl, k, mid - i + 1);
					if(k < N)
						increment(lvl, k + 1, -(mid - i + 1));
					++xInv;
					inversionIndex += mid - i + 1;
					++j;
				}
			}
		}
		
		void update(int l, int r, int val, boolean updateSmaller)
		{
			update(0, 1, N, l, r, val, updateSmaller);
		}
		
		void update(int lvl, int b, int e, int l, int r, int val, boolean updateSmaller)
		{
			if(r < b || e < l)
				return;
			if(l <= b && e <= r)
			{
				if(updateSmaller)
				{
					int ans = -1, lo = b, hi = e;
					while(lo <= hi)
					{
						int mid = lo + hi >> 1;
						if(arr[lvl][mid] < val)
						{
							ans = mid;
							lo = mid + 1;
						}
						else
							hi = mid - 1;
					}
					if(ans != -1)
					{
						increment(lvl, b, -1);
						if(ans < N)
							increment(lvl, ans + 1, 1);
					}
				}
				else
				{
					int ans = -1, lo = b, hi = e;
					while(lo <= hi)
					{
						int mid = lo + hi >> 1;
						if(arr[lvl][mid] > val)
						{
							ans = mid;
							hi = mid - 1;
						}
						else
							lo = mid + 1;
					}
					if(ans != -1)
					{
						increment(lvl, ans, -1);
						if(e < N)
							increment(lvl, e + 1, 1);
					}
					
				}
				
			}
			else
			{
				int mid = b + e >> 1;
				update(lvl + 1, b, mid, l, r, val, updateSmaller);
				update(lvl + 1, mid + 1, e, l, r, val, updateSmaller);
			}	
		}
		
		void remove(int val)
		{
			int sum = 0, lvl = levels;
			while(lvl-- > 0)
				sum += query(lvl, indexOf[lvl][val]);
			inversionIndex -= sum;
			int idx = indexOf[levels-1][val];
			update(1, idx - 1, val, false);
			update(idx + 1, N, val, true);
		}
		
		void increment(int lvl, int idx, int val)
		{
			while(idx <= N)
			{
				inv[lvl][idx] += val;
				idx += idx & -idx;
			}
		}
		
		int query(int lvl, int idx)
		{
			int sum = 0;
			while(idx > 0)
			{
				sum += inv[lvl][idx];
				idx ^= idx & -idx;
			}
			return sum;
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