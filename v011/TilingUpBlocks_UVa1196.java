package v011;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class TilingUpBlocks_UVa1196 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
			{
				out.println("*");
				break;
			}
			
			Block[] a = new Block[n], b = new Block[n];
			for(int i = 0; i < n; ++i)
				a[i] = b[i] = new Block(sc.nextInt(), sc.nextInt(), i);
			
			// 1. Index blocks for fenwick tree from small m to large m
			Arrays.sort(b, new SorterM());
			int[] indexOf = new int[n];
			for(int i = 0; i < n; ++i)
				indexOf[b[i].i] = i + 1;

			// 2. Loop on blocks from small l to large l 
			Arrays.sort(a, new SorterL());
			FenwickTree ft = new FenwickTree(n);
			int ans = 0;
			for(Block block: a)
			{
				int idx = indexOf[block.i];
				int cur = ft.query(idx - 1) + 1;
				
				ft.update(idx, cur);
				ans = Math.max(ans, cur);
			}
			out.println(ans);
		}

		out.flush();
		out.close();
	}

	static class Block { int l, m, i; Block(int a, int b, int c) { l = a; m = b; i = c; } }

	static class SorterL implements Comparator<Block>
	{
		public int compare(Block b1, Block b2)
		{
			if(b1.l != b2.l)
				return b1.l - b2.l;
			return b1.m - b2.m;
		}
	}

	static class SorterM implements Comparator<Block>
	{
		public int compare(Block b1, Block b2)
		{
			if(b1.m != b2.m)
				return b1.m - b2.m;
			return b1.l - b2.l;
		}
	}
	
	static class FenwickTree
	{
		int[] ft;
		
		FenwickTree(int n) { ft = new int[n]; }
		
		void update(int k, int val)
		{
			while(k < ft.length)
			{
				ft[k] = Math.max(ft[k], val);
				k += k & -k;
			}
		}
		
		int query(int k)
		{
			int ans = 0;
			while(k > 0)
			{
				ans = Math.max(ans, ft[k]);
				k ^= k & -k;
			}
			return ans;
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}