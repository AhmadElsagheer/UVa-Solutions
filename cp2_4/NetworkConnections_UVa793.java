//package cp2_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class NetworkConnections_UVa793 {
//
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int TC = Integer.parseInt(br.readLine());
//		br.readLine();
//		while(TC-->0)
//		{
//			int N = Integer.parseInt(br.readLine());
//			UnionFind x = new UnionFind(N);
//			int yes = 0, no = 0;
//			while(br.ready())
//			{
//				String line = br.readLine();
//				if(line.isEmpty())
//					break;
//				StringTokenizer st = new StringTokenizer(line);
//				char c = st.nextToken().charAt(0);
//				int i = Integer.parseInt(st.nextToken()) - 1;
//				int j = Integer.parseInt(st.nextToken()) - 1;
//				if(c=='c')
//					x.unionSet(i, j);
//				else
//					if(x.isSameSet(i, j))
//						yes++;
//					else
//						no++;
//			}
//			sb.append(yes+","+no+"\n");
//			if(TC!=0)
//				sb.append("\n");
//		}
//		System.out.print(sb);
//	}
//}
//
//class UnionFind {                                              
//	ArrayList<Integer> p, rank, setSize;
//	int numSets;
//	
//	public UnionFind(int N) 
//	{
//		 p = new ArrayList<Integer>(N);
//		 rank = new ArrayList<Integer>(N);
//		 setSize = new ArrayList<Integer>(N);
//		 numSets = N;
//		 for (int i = 0; i < N; i++) 
//		 {
//		   p.add(i);
//		   rank.add(0);
//		   setSize.add(1);
//		 }
//	}
//	
//	public int findSet(int i) 
//	{ 
//		if (p.get(i) == i) return i;
//		else 
//		{
//			int ret = findSet(p.get(i)); p.set(i, ret);	return ret; 
//		} 
//	 }
//	
//	public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
//	
//	public void unionSet(int i, int j) 
//	{ 
//		 if (!isSameSet(i, j)) 
//		 { 
//			 numSets--; 
//			 int x = findSet(i), y = findSet(j);
//			 // rank is used to keep the tree short
//			 if (rank.get(x) > rank.get(y)) 
//			 	{ p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
//			 else
//			 {	 p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
//			 	if (rank.get(x) == rank.get(y)) 
//			 		rank.set(y, rank.get(y) + 1); 
//			 } 
//		 } 
//	}
//	
//	public int numDisjointSets() { return numSets; }
//	
//	public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
//}