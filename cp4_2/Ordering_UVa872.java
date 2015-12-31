package cp4_2;

import java.util.*;
import java.io.*;

public class Ordering_UVa872 {

	static final int UNVISITED = 0, EXPLORED = 1, VISITED = 2;
	static PrintWriter out = new PrintWriter(System.out);
	static boolean adjMatrix[][], valid[];
	static int[] dfs_num, parents, array;
	static int N;
	
	public static void print()
	{
		for(int i = 0; i < N - 1; i++)
			out.print((char)(array[i]+'A')+" ");
		out.println((char)(array[N-1]+'A'));
	}
	
	public static void toposort(int k)
	{
		if(k==N)
			print();
		else
			for(int i = 0; i < 26; i++)
				if(valid[i] && parents[i]==0)
				{
					array[k] = i;
					for(int j = 0; j < 26; j++)
						if(adjMatrix[i][j])
							parents[j]--;
					parents[i] = -1;
					toposort(k+1);
					for(int j = 0; j < 26; j++)
						if(adjMatrix[i][j])
							parents[j]++;
					parents[i] = 0;
				}
		
			
	}
	
	public static boolean haveCycles()
	{
		dfs_num = new int[26];
		for(int i = 0; i < 26; i++)
			if(valid[i] && dfs(i))
				return true;
		return false;
	}
	
	public static boolean dfs(int u)
	{
		dfs_num[u] = EXPLORED;
		for(int i = 0; i < 26; i++)
			if(adjMatrix[u][i])
				if(dfs_num[i]==UNVISITED)
					if(dfs(i))
						return true;
				else
					if(dfs_num[i]==EXPLORED)
						return true;	//back edge -- cycle
		dfs_num[u] = VISITED;
		return false;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			//take input
			adjMatrix = new boolean[26][26];
			valid = new boolean[26];
			parents = new int[26];
			
			st = new StringTokenizer(br.readLine());
			N = st.countTokens();
			for(int i = 0; i < N; i++)
				valid[st.nextToken().charAt(0)-'A'] = true;
			
			st = new StringTokenizer(br.readLine());
			int count = st.countTokens();
			while(count-->0)
			{
				String edge = st.nextToken();
				adjMatrix[edge.charAt(0)-'A'][edge.charAt(2)-'A'] = true;
				parents[edge.charAt(2)-'A']++;
			}
				
			//check for bidirectional/back edges (cycles)
	
			if(haveCycles())
				out.println("NO");
			else
			{
				array = new int[N];
				toposort(0);
			}
			
			if(TC!=0)
				out.println();
		}
		out.flush();
		out.close();
	}
}
