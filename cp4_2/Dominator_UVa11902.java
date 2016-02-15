<<<<<<< HEAD
package cp4_2;

import java.io.PrintWriter;
import java.util.Arrays;



public class Dominator_UVa11902 {


	static PrintWriter out = new PrintWriter(System.out);
	static int index, size;
	static byte[] b = new byte[1024];

	static byte next_byte() throws Exception {
		if (index == size) {
			index = 0;
			size = System.in.read(b);
		}
		return b[index++];
	}

	static int next_int() throws Exception {
		int n = 0;
		byte c = next_byte();
		while (!('0' <= c && c <= '9')) {
			c = next_byte();
		}
		while ('0' <= c && c <= '9') {
			n = n * 10 + c - '0';
			c = next_byte();
		}
		return n;
	}
	
	static boolean[][] adjMatrix = new boolean[105][105];
	static boolean[] dfs_num = new boolean[105];
	static int N;
	
	
	
	
	public static void dfs(int u, int x)	//O(V+E) for adjList and O(V^2) for adjList
	{
		
		if(u==x)
			return;
		dfs_num[u] = true;
		for(int i = 0; i < N; i++)
		{
			
			if(adjMatrix[u][i] && !dfs_num[i])
				dfs(i,x);
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		//create the adjacency list
		
		
		
		int tc = next_int();
		for(int k = 1; k <= tc; k++)
		{
			N = next_int();
			for(int i = 0; i < N; i++)
				Arrays.fill(adjMatrix[i], false);
			
			boolean[][] domination = new boolean[N][N];
			
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < N; j++)
					adjMatrix[i][j] = next_int()==1?true:false;
			}
			Arrays.fill(dfs_num, false);
			
			dfs(0,-1);
			boolean[] reachable = new boolean[N];
			for(int i = 0; i < N; i++)
				reachable[i] = dfs_num[i];
			
			
			out.println("Case "+k+":");
			
			for(int i = 0; i < N; i++)
			{
				line();
				out.println();
				Arrays.fill(dfs_num, false);
				dfs(0,i);
				
				for(int j = 0; j < N; j++)
					if(j==i && reachable[j])
						out.print("|Y");
					else
					{
						
						
						if(reachable[j] && !dfs_num[j])
							out.print("|Y");
						else
							out.print("|N");
					}
						
				out.println("|");	
			}
			line();
			out.println();

		}
				
		out.flush();
		out.close();
		
	}
	
	public static void line()
	{
		out.print("+");
		for(int i = 0; i < (N << 1) - 1; i++)
			out.print("-");
		out.print("+");
	}

}
=======
package cp4_2;

import java.io.PrintWriter;
import java.util.Arrays;



public class Dominator_UVa11902 {


	static PrintWriter out = new PrintWriter(System.out);
	static int index, size;
	static byte[] b = new byte[1024];

	static byte next_byte() throws Exception {
		if (index == size) {
			index = 0;
			size = System.in.read(b);
		}
		return b[index++];
	}

	static int next_int() throws Exception {
		int n = 0;
		byte c = next_byte();
		while (!('0' <= c && c <= '9')) {
			c = next_byte();
		}
		while ('0' <= c && c <= '9') {
			n = n * 10 + c - '0';
			c = next_byte();
		}
		return n;
	}
	
	static boolean[][] adjMatrix = new boolean[105][105];
	static boolean[] dfs_num = new boolean[105];
	static int N;
	
	
	
	
	public static void dfs(int u, int x)	//O(V+E) for adjList and O(V^2) for adjList
	{
		
		if(u==x)
			return;
		dfs_num[u] = true;
		for(int i = 0; i < N; i++)
		{
			
			if(adjMatrix[u][i] && !dfs_num[i])
				dfs(i,x);
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		//create the adjacency list
		
		
		
		int tc = next_int();
		for(int k = 1; k <= tc; k++)
		{
			N = next_int();
			for(int i = 0; i < N; i++)
				Arrays.fill(adjMatrix[i], false);
			
			boolean[][] domination = new boolean[N][N];
			
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < N; j++)
					adjMatrix[i][j] = next_int()==1?true:false;
			}
			Arrays.fill(dfs_num, false);
			
			dfs(0,-1);
			boolean[] reachable = new boolean[N];
			for(int i = 0; i < N; i++)
				reachable[i] = dfs_num[i];
			
			
			out.println("Case "+k+":");
			
			for(int i = 0; i < N; i++)
			{
				line();
				out.println();
				Arrays.fill(dfs_num, false);
				dfs(0,i);
				
				for(int j = 0; j < N; j++)
					if(j==i && reachable[j])
						out.print("|Y");
					else
					{
						
						
						if(reachable[j] && !dfs_num[j])
							out.print("|Y");
						else
							out.print("|N");
					}
						
				out.println("|");	
			}
			line();
			out.println();

		}
				
		out.flush();
		out.close();
		
	}
	
	public static void line()
	{
		out.print("+");
		for(int i = 0; i < (N << 1) - 1; i++)
			out.print("-");
		out.print("+");
	}

}
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
