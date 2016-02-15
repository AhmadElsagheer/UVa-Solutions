//package cp2_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class SKYLINE_UVa1232 {
//
//	public static void main(String[] args) throws NumberFormatException, IOException 
//	{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
//		while(TC-->0)
//		{
//			int s = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
//			SegmentTree ST = new SegmentTree();
//			int overlap = 0;
//			while(s-->0)
//			{
//				StringTokenizer st = new StringTokenizer(br.readLine());
//				int L = Integer.parseInt(st.nextToken());
//				int R = Integer.parseInt(st.nextToken());
//				int H = Integer.parseInt(st.nextToken());
//				overlap += ST.query(L, R, H);
//			
//				ST.update_range(L, R, H);
//			}
//			sb.append(overlap).append("\n");
//		}
//		System.out.print(sb);
//	}
//}
//
//
//class SegmentTree {		// 1-based DS, OOP
//	
//	int N; 			//the number of elements in the array as a multipls of 2 (i.e. after padding)
//	int[] sTree[], lazy;
//	
//	SegmentTree()		
//	{
//		N = (int) Math.pow(2, Math.ceil(Math.log(100000)/Math.log(2)));	//padding
//		sTree = new int[N<<1][2];		//no. of nodes = 2*N - 1, we add one to cross out index zero
//		lazy = new int[N<<1];
//	}
//	
//	
//
//	
//	void update_range(int i, int j, int val)		// O(log n) 
//	{
//		update_range(1,1,N,i,j,val);
//	}
//	
//	void update_range(int node, int b, int e, int i, int j, int val)
//	{
//		
//		if(i > e || j < b)		
//			return;
//		if(b >= i && e <= j)		
//		{
//			
//			if(val <= sTree[node][0])
//				return;
//			
//			if(val >= sTree[node][1])
//			{
//				sTree[node][1] = sTree[node][0] = val;	
//				lazy[node] = val;
//				return;
//			}
//		}							
//		
//		propagate(node,b,e);
//		update_range(node<<1,b,(b+e)/2,i,j,val);
//		update_range((node<<1)+1,(b+e)/2+1,e,i,j,val);
//		sTree[node][0] = Math.min(sTree[node<<1][0], sTree[(node<<1)+1][0]);
//		sTree[node][1] = Math.max(sTree[node<<1][1], sTree[(node<<1)+1][1]);
//			
//		
//		
//	}
//	void propagate(int node, int b, int e)		
//	{
//		update_range(node<<1,b,(b+e)/2,b,e,lazy[node]);
//		update_range((node<<1)+1,(b+e)/2+1,e,b,e,lazy[node]);
//		lazy[node] = 0;
//	}
//	
//	int query(int i, int j, int val)
//	{
//		return query(1,1,N,i,j, val)[2];
//	}
//	
//	int[] query(int node, int b, int e, int i, int j, int val)	// O(log n)
//	{
//		
//		if(i>e || j <b)
//			return new int[]{0,0,0};		
//		if(b>= i && e <= j)
//		{
//			
//			if(val < sTree[node][0])
//				return new int[]{0,0,0};
//			if(val > sTree[node][1] || val == sTree[node][0] && val == sTree[node][1])
//				return new int[]{1,1,e-b};
//		}
//		
//		propagate(node, b, e);
//		int[] p1 = query(node<<1,b,(b+e)/2,i,j,val);
//		int[] p2 = query((node<<1)+1,(b+e)/2+1,e,i,j,val);
//		
//		return new int[]{p1[0],p2[1],p1[2]+p2[2]+((b+e)/2>=i && (b+e)/2<=j&&(b+e)/2+1>=i && (b+e)/2+1<=j&&(p1[1]==1 || p2[0]==1)?1:0)}; 
//				
//	}
//	
//	
//	
//	
//}
