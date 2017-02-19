package v112;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Census_UVa11297 {

	
	
	public static void main(String[] args) throws IOException {
		
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = in.nextInt();
		int N = (int) Math.pow(2, Math.ceil(Math.log(n)/Math.log(2)));
		
		int[][] mat = new int[N+1][N+1];
		for(int i = 1; i <= n; i++)
			for(int j = 1; j <= n; j++)
				mat[i][j] = in.nextInt();
		
		SegmentTree st = new SegmentTree(mat,n);
		
		int q = in.nextInt();
		while(q-->0)
		{
			char comm = in.next().charAt(0);
			if(comm == 'q')
			{
				int a1 = in.nextInt(), b1 = in.nextInt(), a2 = in.nextInt(), b2 = in.nextInt();
				int[] query = st.query(a1,b1,a2,b2);
				sb.append(query[0] + " "+query[1]+"\n");
			}
			else
			{
				int a = in.nextInt(), b = in.nextInt(), val = in.nextInt();
				st.updatePoint(a, b, val);
			}
				
		}
		System.out.print(sb);
	}
	
	static class InputReader {
		StringTokenizer st;
		BufferedReader br;

		public InputReader(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}


	}
	
	
	
	//QuadTree parent at k has 4 children: 4*k - 2, 4*k - 1, 4*k, 4*k + 1
	//The parent of node k is located at (k+2)/4 
	//The input array must be a square matrix with its dimension a power of 2
	//Total number of nodes for a matrix N * N = (N * N * 4 - 1) / 4
	
	static class SegmentTree		// 1-based
	{
		static final int INF = 200000000;
		int N, maxTree[], minTree[], mat[][];
		int n;
		
		SegmentTree(int[][] in, int n)
		{
			N = in.length - 1; this.n = n;
			maxTree = new int[350000];
			minTree = new int[350000];
			Arrays.fill(minTree, INF);
			mat = in;
			build(1,1,1,N);
		}
		
		void build(int node, int i, int j, int len)
		{
			if(len == 1)
			{
				maxTree[node] = mat[i][j];
				minTree[node] = i > n || j > n?INF:mat[i][j];		
			}
			else
			{
				build((node<<2) - 2, i, j, len/2);
				build((node<<2) - 1, i, j + len/2, len/2);
				build(node<<2, i + len/2, j, len/2);
				build((node<<2) + 1, i + len/2, j + len/2, len/2);
				maxTree[node] = 0; minTree[node] = INF;
				for(int k = -2; k <= 1; k++)
				{
					maxTree[node] = Math.max(maxTree[node], maxTree[(node<<2)+k]);
					minTree[node] = Math.min(minTree[node], minTree[(node<<2)+k]);
				}
			}
			
		}
		
		int findNode(int node, int i, int j, int len, int a, int b)
		{
			if(len == 1) return node;
			if(a >= i + len/2)
				if(b >= j + len/2)
					return findNode((node<<2) + 1, i + len/2, j + len/2, len/2, a, b);
				else
					return findNode(node<<2, i + len/2, j, len/2, a, b);
			else
				if(b >= j + len/2)
					return findNode((node<<2) - 1, i, j + len/2, len/2, a, b);	
			return findNode((node<<2) - 2, i, j, len/2, a, b);
			
		}
		
		void updatePoint(int a, int b, int val)
		{
			int node = findNode(1,1,1,N,a,b);
			maxTree[node] = val;
			minTree[node] = val;
			while(node > 1)
			{
				node = (node + 2)>>2;
				maxTree[node] = 0; minTree[node] = INF;
				for(int k = -2; k <= 1; k++)
				{
					maxTree[node] = Math.max(maxTree[node], maxTree[(node<<2)+k]);
					minTree[node] = Math.min(minTree[node], minTree[(node<<2)+k]);
				}
				
			}
		}
		
		int[] query(int a1, int b1, int a2, int b2)
		{
			return query(1,1,1,N,a1,b1,a2,b2);
		}
		
		int[] query(int node, int i, int j, int len, int a_start, int b_start, int a_end, int b_end)
		{
			if(a_start >= i + len || b_start >= j + len || a_end < i || b_end < j) return new int[]{0,INF};
			
			if(a_start <= i && b_start <= j && a_end >= i + len - 1 && b_end >= j + len - 1) return new int[]{maxTree[node],minTree[node]};
			

			int[] q1 = query((node<<2) - 2, i, j, len/2, a_start, b_start, a_end, b_end);
			int[] q2 = query((node<<2) - 1, i, j + len/2, len/2, a_start, b_start, a_end, b_end);
			int[] q3 = query(node<<2, i + len/2, j, len/2, a_start, b_start, a_end, b_end);
			int[] q4 = query((node<<2) + 1, i + len/2, j + len/2, len/2, a_start, b_start, a_end, b_end);
			 
			return new int[]{Math.max(Math.max(q1[0], q2[0]), Math.max(q3[0], q4[0])), Math.min(Math.min(q1[1], q2[1]), Math.min(q3[1], q4[1]))};
		}
	}
}
