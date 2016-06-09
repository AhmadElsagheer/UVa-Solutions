package cp2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SKYLINE_UVa1232 {

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(TC-->0)
		{
			int s = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			SegmentTree ST = new SegmentTree();
			int overlap = 0;
			while(s-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int L = Integer.parseInt(st.nextToken());
				int R = Integer.parseInt(st.nextToken());
				int H = Integer.parseInt(st.nextToken());
				overlap += ST.query(L, R, H);

				ST.update_range(L, R, H);
			}
			sb.append(overlap).append("\n");
		}
		System.out.print(sb);
	}
	
	static class Node { int maxH, minH; Node(int a, int b) { maxH = a; minH = b; } }
	
	static class Query 
	{ 
		int overlap;
		boolean leftTaken, rightTaken; 
		
		Query(int a, boolean b, boolean c) { overlap = a; leftTaken = b; rightTaken = c; } 
	}

	static class SegmentTree {

		int N, lazy[];
		Node[] sTree;

		SegmentTree()		
		{
			N = 1;
			while(N < 100000) N <<= 1;
			sTree = new Node[N<<1];
			for(int i = 0; i < N<<1; ++i)
				sTree[i] = new Node(0, 0);
			lazy = new int[N<<1];
		}

		void update_range(int i, int j, int val) 
		{
			update_range(1,1,N,i,j,val);
		}

		void update_range(int node, int b, int e, int i, int j, int val)
		{

			if(i > e || j < b)		
				return;
			if(b >= i && e <= j)		
			{
				Node cur = sTree[node];
				cur.maxH = Math.max(cur.maxH, val);
				cur.minH = Math.max(cur.minH, val);
				lazy[node] = Math.max(lazy[node], val);
				return;	
			}							

			propagate(node,b,e);
			update_range(node<<1,b,(b+e)/2,i,j,val);
			update_range((node<<1)+1,(b+e)/2+1,e,i,j,val);
			sTree[node] = new Node( Math.max(sTree[node<<1].maxH, sTree[node<<1|1].maxH), 
									Math.min(sTree[node<<1].minH, sTree[node<<1|1].minH));
		}
		void propagate(int node, int b, int e)		
		{
			update_range(node<<1,b,(b+e)/2,b,e,lazy[node]);
			update_range((node<<1)+1,(b+e)/2+1,e,b,e,lazy[node]);
			lazy[node] = 0;
		}

		int query(int i, int j, int val)
		{
			return query(1,1,N,i,j, val).overlap;
		}

		Query query(int node, int b, int e, int i, int j, int val)	// O(log n)
		{

			if(i > e || j < b)
				return new Query(0, false, false);
			if(b>= i && e <= j)
			{
				if(val < sTree[node].minH)
					return new Query(0, false, false);
				if(val >= sTree[node].maxH)
					return new Query(e - b, true, true);
			}

			propagate(node, b, e);
			int mid = b + e >> 1;
			Query leftQuery = query(node<<1,b,mid,i,j,val);
			Query rightQuery = query(node<<1|1,mid+1,e,i,j,val);
			int cross = 0;
			if(i <= mid && j > mid && (leftQuery.rightTaken || rightQuery.leftTaken))		//missing 1 unit distance
				cross = 1;
			return new Query(leftQuery.overlap + rightQuery.overlap + cross, leftQuery.leftTaken, rightQuery.rightTaken);
		}
	}
}
