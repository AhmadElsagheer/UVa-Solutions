package v004;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheTowerOfBabylon_UVa437 {

	static Block[] blocks;
	static int[][] memo;
	static int N;
	
	static int dp(int idx, int lst)
	{
		if(idx == N)
			return 0;
		if(memo[idx][lst] != -1)
			return memo[idx][lst];
		int put = 0, leave = dp(idx + 1, lst);
		if(lst == N || blocks[idx].putOn(blocks[lst]))
			put = dp(idx + 1, idx) + blocks[idx].z;
		return memo[idx][lst] = Math.max(put, leave);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			N = n * 3;
			blocks = new Block[N];
			for(int i = 0; i < n; ++i)
			{
				int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
				blocks[i*3] = new Block(a, b, c);
				blocks[i*3 + 1] = new Block(c, a, b);
				blocks[i*3 + 2] = new Block(b, c, a);
			}
			Arrays.sort(blocks);
			memo = new int[N][N + 1];
			for(int i = 0; i < N; ++i)
				Arrays.fill(memo[i], -1);
			int ans = dp(0, N);
			out.format("Case %d: maximum height = %d\n", tc++, ans);
		}
		out.flush();

	}

	static class Block implements Comparable<Block>
	{
		int x, y, z;
		
		Block(int a, int b, int c) { x = Math.min(a, b); y = Math.max(a, b); z = c; }
		
		public int compareTo(Block b)
		{
			if(x > b.x && y > b.y)
				return -1;
			if(b.x > x && b.y > y)
				return 1;
			if(y != b.y)
				return b.y - y;
			return b.x - x;
		}
		
		public boolean putOn(Block b)
		{
			return x < b.x && y < b.y;
		}
		
		public String toString()
		{
			return x + " " + y + " " + z;
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
