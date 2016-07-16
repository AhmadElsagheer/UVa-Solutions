package regionals.dhaka2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UniqueParty {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			int R = sc.nextInt(), C = sc.nextInt(), apt[][] = new int[R][C];
			for(int i = 0; i < R; ++i)
				for(int j = 0; j < C; ++j)
					apt[i][j] = sc.nextInt();
			out.printf("Case %d:\n", t);
			int Q = sc.nextInt();
			while(Q-->0)
			{
				int h = sc.nextInt();
				int[][] mat = new int[R][C];
				for(int i = 0; i < R; ++i)
					for(int j = 0; j < C; ++j)
					{
						mat[i][j] = apt[i][j] >= h ? 1 : -1;
						if(j > 0)
							mat[i][j] += mat[i][j-1];
					}
				int ans = 0;
				for(int c1 = 0; c1 < C; ++c1)
					for(int c2 = c1; c2 < C; ++c2)
					{
						int[] arr = new int[R + 2];
						for(int r = 0; r < R; ++r)
							arr[r + 2] = mat[r][c2] - (c1 == 0 ? 0 : mat[r][c1 - 1]) + arr[r + 1];
						SegmentTree st = new SegmentTree(arr);
						for(int r = 0; r < R; ++r)
						{
							int q = st.query(arr[r + 2], r + 2);
							
							if(q != INF)
								ans = Math.max(ans, (c2 - c1 + 1) * (r - (q - 1) + 1));
						}
					}
				out.println(ans);			
			}
		}
		out.flush();
		out.close();
	}
	
	static final int INF = (int)1e9;
	
	static class SegmentTree	//smaller than current index
	{
		int N, array[], sTree[];
		
		SegmentTree(int[] in)
		{
			int n = in.length - 1;
			array = in;
			N = 1;
			while(N < n) N <<= 1;
			sTree = new int[N<<1];
			build(1, 1, N);
		}
		
		void build(int node, int b, int e)
		{
			if(b == e)
			{	
				sTree[node] =  b < array.length ? array[b] : INF;
				return;
			}
			int mid = b + e >> 1;
			build(node<<1, b, mid);
			build(node<<1|1, mid + 1, e);
			sTree[node] = Math.min(sTree[node<<1], sTree[node<<1|1]);
		}
		
		int query(int val, int r)
		{
			if(sTree[1] > val)
				return INF;
			return query(1, 1, N, val, r);
		}
		
		int query(int node, int b, int e, int val, int r)
		{
			if(b == e)
				return b;
			int mid = b + e >> 1;
			if(sTree[node<<1] <= val)
				return query(node<<1, b, mid, val, r);
			if(b < r && sTree[node<<1|1] <= val)
				return query(node<<1|1, mid + 1, e, val, r);
			return INF;
		}
	}

	static class Scanner
	{
		BufferedReader br;
		StringTokenizer st;

		Scanner(InputStream s) { br = new BufferedReader(new InputStreamReader(s)); }

		String next() throws IOException
		{
			while(st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();	
		}

		int nextInt() throws NumberFormatException, IOException { return Integer.parseInt(next()); }
	}
}
