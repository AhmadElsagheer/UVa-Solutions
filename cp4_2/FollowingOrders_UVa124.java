package cp4_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class FollowingOrders_UVa124 {

	static boolean[][] adjMatrix;
	
	static int[] num;
	static int N;
	static char[] variables;
	static ArrayList<String> orders;
	
	public static void toposort(String x, LinkedList<Integer> roots, boolean[][] adjMatrix)
	{
		if(roots.isEmpty())
			orders.add(x);
		for(int i = 0, size = roots.size(); i < size; i++)
		{
			LinkedList<Integer> next = (LinkedList<Integer>) roots.clone();
			boolean[][] y = copyMatrix(adjMatrix);
			int cur = next.remove(i);
			for(int j = 0; j < N; j++)
				if(y[cur][j])
				{
					y[cur][j] = false;
					if(!hasParents(y,j))
						next.add(j);
				}
			toposort(x+variables[cur],next,y);
		}
			
	
	}
	
	public static boolean hasParents(boolean[][] matrix, int x)
	{
		for(int i = 0; i < N; i++)
			if(matrix[i][x])
				return true;
		return false;
	}
	
	public static boolean[][] copyMatrix(boolean[][] x)
	{
		boolean[][] y = new boolean[x.length][x.length];
		for(int i = 0; i < x.length; i++)
			for(int  j = 0; j < x.length; j++)
				y[i][j] = x[i][j];
		return y;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		boolean first = true;
		
		while(br.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			TreeMap<Character,Integer> h = new TreeMap<Character,Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = st.countTokens();
			variables = new char[N];
			for(int i = 0; i < N; i++)
			{
				variables[i] = st.nextToken().charAt(0);
				h.put(variables[i], i);
			}
			adjMatrix = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			int pairs  = st.countTokens()/2;
			int[] parents = new int[N];
			while(pairs-->0)
			{
				int u = h.get(st.nextToken().charAt(0));
				int v = h.get(st.nextToken().charAt(0));
				adjMatrix[u][v] = true;
				parents[v]++;
			}
			
			LinkedList<Integer> roots = new LinkedList<Integer>();
			for(int i = 0; i < N; i++)
				if(parents[i]==0)
					roots.add(i);
			orders= new ArrayList<String>(300);
			toposort("",roots,adjMatrix);
			Collections.sort(orders);
			for(int i = 0, size= orders.size(); i < size; i++)
				out.println(orders.get(i));
			
		}
		
		out.flush();
		out.close();
	}
	
	
}
