<<<<<<< HEAD
package cp4_2;



import java.io.*;
import java.util.*;

//Strongly Connected Components of a directed graph

public class CallingCircles_UVa247 {

	static ArrayList<String> names;
	static int N;
	static ArrayList<Integer>[] adjMatrix;
	static int[] dfs_num;
	static int[] dfs_low;
	static LinkedList<Integer> stack;
	static boolean[] visited;
	static int counter;
	static StringBuilder sb = new StringBuilder();
	static LinkedList<LinkedList<Integer>> SCC;
	
	public static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		visited[u] = true;
		for(int i = 0; i <adjMatrix[u].size(); i++)
		{
			
			int v = adjMatrix[u].get(i);
			if(dfs_num[v]==0)
				tarjanSCC(v);
			if(visited[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_num[u]==dfs_low[u])
		{
			//Discovered an SCC
			LinkedList<Integer> x = new LinkedList<Integer>();
			while(true)		//vertices in the discovered SCC
			{	
				int v = stack.pop();
				visited[v] = false;
				x.push(v-1);
				if(v==u)
					break;
				
				
			}
			SCC.push(x);
			
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//initializing and taking input
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int k = 1;
		while(true)
		{
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0)
				break;
			adjMatrix = new ArrayList[N+1];
			for(int i = 1; i <= N; i++)
				adjMatrix[i] = new ArrayList<Integer>();
			
			names = new ArrayList<String>(N);
			int newIndex = 0;
			for(int i = 1; i <= M; i++)
			{
				st = new StringTokenizer(br.readLine());
				String first = st.nextToken();
				String second = st.nextToken();
				
				int index1 = names.indexOf(first);
				int index2 = names.indexOf(second);
				if(index1<0)
				{
					names.add(first);
					index1 = newIndex++;
				}
				if(index2<0)
				{
					names.add(second);
					index2 = newIndex++;
				}
				if(!adjMatrix[index1+1].contains(index2+1))
					adjMatrix[index1+1].add(index2+1);
			}
			
			if(k!=1)
				sb.append("\n");	
			sb.append("Calling circles for data set "+k+++":\n");
			
			stack = new LinkedList<Integer>();
			visited = new boolean[N+1];
			dfs_num = new int[N+1];
			dfs_low = new int[N+1];
			counter = 0;
			SCC = new LinkedList<LinkedList<Integer>>();
			
			for(int i = 1; i <= N; i++)
			{
				if(dfs_num[i]==0)
					tarjanSCC(i);
			}
			while(!SCC.isEmpty())
			{
				LinkedList<Integer> x = SCC.pop();
				
				while(!x.isEmpty())
				{
					String name = names.get(x.pop());
					sb.append(name);
					if(!x.isEmpty())
						sb.append(", ");
				}
				
				sb.append("\n");
			}
				
			
		}
		System.out.print(sb);
		
		
		
	}

}

=======
package cp4_2;



import java.io.*;
import java.util.*;

//Strongly Connected Components of a directed graph

public class CallingCircles_UVa247 {

	static ArrayList<String> names;
	static int N;
	static ArrayList<Integer>[] adjMatrix;
	static int[] dfs_num;
	static int[] dfs_low;
	static LinkedList<Integer> stack;
	static boolean[] visited;
	static int counter;
	static StringBuilder sb = new StringBuilder();
	static LinkedList<LinkedList<Integer>> SCC;
	
	public static void tarjanSCC(int u)
	{
		dfs_num[u] = dfs_low[u] = ++counter;
		stack.push(u);
		visited[u] = true;
		for(int i = 0; i <adjMatrix[u].size(); i++)
		{
			
			int v = adjMatrix[u].get(i);
			if(dfs_num[v]==0)
				tarjanSCC(v);
			if(visited[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		if(dfs_num[u]==dfs_low[u])
		{
			//Discovered an SCC
			LinkedList<Integer> x = new LinkedList<Integer>();
			while(true)		//vertices in the discovered SCC
			{	
				int v = stack.pop();
				visited[v] = false;
				x.push(v-1);
				if(v==u)
					break;
				
				
			}
			SCC.push(x);
			
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//initializing and taking input
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int k = 1;
		while(true)
		{
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0)
				break;
			adjMatrix = new ArrayList[N+1];
			for(int i = 1; i <= N; i++)
				adjMatrix[i] = new ArrayList<Integer>();
			
			names = new ArrayList<String>(N);
			int newIndex = 0;
			for(int i = 1; i <= M; i++)
			{
				st = new StringTokenizer(br.readLine());
				String first = st.nextToken();
				String second = st.nextToken();
				
				int index1 = names.indexOf(first);
				int index2 = names.indexOf(second);
				if(index1<0)
				{
					names.add(first);
					index1 = newIndex++;
				}
				if(index2<0)
				{
					names.add(second);
					index2 = newIndex++;
				}
				if(!adjMatrix[index1+1].contains(index2+1))
					adjMatrix[index1+1].add(index2+1);
			}
			
			if(k!=1)
				sb.append("\n");	
			sb.append("Calling circles for data set "+k+++":\n");
			
			stack = new LinkedList<Integer>();
			visited = new boolean[N+1];
			dfs_num = new int[N+1];
			dfs_low = new int[N+1];
			counter = 0;
			SCC = new LinkedList<LinkedList<Integer>>();
			
			for(int i = 1; i <= N; i++)
			{
				if(dfs_num[i]==0)
					tarjanSCC(i);
			}
			while(!SCC.isEmpty())
			{
				LinkedList<Integer> x = SCC.pop();
				
				while(!x.isEmpty())
				{
					String name = names.get(x.pop());
					sb.append(name);
					if(!x.isEmpty())
						sb.append(", ");
				}
				
				sb.append("\n");
			}
				
			
		}
		System.out.print(sb);
		
		
		
	}

}

>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
