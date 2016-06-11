package cp2_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class AhoyPirates_UVa11402 {

	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			sb.append("Case ").append(k).append(":\n");
			int M = Integer.parseInt(br.readLine());
			Pair[] patterns = new Pair[M];
			int n = 0;
			for(int i = 0; i < M; i++)
			{
				int T = Integer.parseInt(br.readLine());
				String pattern = br.readLine();
				n += T*pattern.length();
				patterns[i] = new Pair(T,pattern);
			}
			int[] pirates = new int[(int)(Math.pow(2, Math.ceil(Math.log(n)/Math.log(2))))+1];
			int start = 0;
			for(int i = 0; i < M; i++)
			{
				Pair cur = patterns[i];
				for(int j = 0, length = cur.pattern.length(); j < length; j++)
					if(cur.pattern.charAt(j)=='1')
						for(int c = 0; c < cur.T; c++)
							pirates[start+j+c*length+1] = 1;
				start += cur.T * cur.pattern.length();				
			}
			SegmentTree s = new SegmentTree(pirates);
			int Q = Integer.parseInt(br.readLine());
			int q = 1;
			while(Q-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int a = Integer.parseInt(st.nextToken()) + 1;
				int b = Integer.parseInt(st.nextToken()) + 1;
				if(c=='S')
					sb.append("Q"+q+++": ").append(s.query(a,b)).append("\n");
				else
					s.update_range(a,b,c);
			}
			
		}
		System.out.print(sb);
	}
	
	
}

class SegmentTree
{
	int N;
	int[] array, sTree;
	char[] lazy;
	
	SegmentTree(int[] in)
	{
		array = in; N = in.length - 1;
		sTree = new int[N<<1];
		lazy = new char[N<<1];
		build(1,1,N);
	}
	
	void build(int node, int b, int e)
	{
		if(b==e)
			sTree[node] = array[b];
		else
		{
			int mid = b + e >> 1;
			build(node<<1,b,mid);
			build(node<<1|1,mid+1,e);
			sTree[node] = sTree[node<<1] + sTree[node<<1|1];
		}
	}
	
	void update_lazy(int node, char opr)
	{
		if(opr==0)
			return;
		if(opr=='I')
		{
			switch(lazy[node])
			{
			case 'F':lazy[node] = 'E';break;
			case 'E':lazy[node] = 'F';break;
			case 'I':lazy[node] = 0;break;
				default:lazy[node] = 'I';
			}
		}
		else
			lazy[node] = opr;
	}
	
	void update_opr(int node, int b, int e, int opr)
	{
		if(opr==0)
			return;
		switch(opr)
		{
		case 'E': sTree[node] = 0;break; 
		case 'F': sTree[node] = (e-b+1);break;
			default: sTree[node] = (e-b+1) - sTree[node];
			
		}
	}
	void update_range(int i, int j, char opr)
	{
		update_range(1,1,N,i,j,opr);
	}
	
	void update_range(int node, int b, int e, int i, int j, char opr)
	{
		if(i > e || j < b)
			return;
		if(i <= b && j >= e)
		{
			update_lazy(node,opr);
			update_opr(node,b,e,opr);
			
		}
		else
		{
			int mid = b + e >> 1;
			propagate(node,b,e);
			update_range(node<<1,b,mid,i,j,opr);
			update_range(node<<1|1,mid+1,e,i,j,opr);
			sTree[node] = sTree[node<<1] + sTree[node<<1|1];
		}
	}
	
	void propagate(int node, int b, int e)
	{
		int mid = b + e >> 1;
		update_lazy(node<<1,lazy[node]);
		update_lazy((node<<1)+1,lazy[node]);
		update_opr(node<<1,b,mid,lazy[node]);
		update_opr(node<<1|1,mid+1,e,lazy[node]);
		lazy[node] = 0;
	}
	
	int query(int i, int j)
	{
		return query(1,1,N,i,j);
	}
	
	int query(int node, int b, int e, int i, int j)
	{
		if(i > e || j < b)
			return 0;
		if(i <= b && j >= e)
			return sTree[node];
		int mid = b + e >> 1;
		propagate(node,b,e);
		return query(node<<1,b,mid,i,j) + query(node<<1|1,mid+1,e,i,j);
	}
	
}

class Pair
{
	int T; String pattern;
	Pair(int x, String y) {T = x; pattern = y;}
}