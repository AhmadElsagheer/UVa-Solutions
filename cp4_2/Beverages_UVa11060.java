package cp4_2;

import java.util.*;
import java.io.*;

public class Beverages_UVa11060 {

	static int N;
	static boolean[][] adjMatrix;
	static LinkedList<Integer> array;
	static int[] parents;
	
	public static void toposort()
	{
	
		PriorityQueue<Integer> roots = new PriorityQueue<Integer>();
		for(int  i = 0; i < N; i++)
			if(parents[i]==0)
				roots.add(i);
		
		while(!roots.isEmpty())
		{
			int cur = roots.remove();
			array.add(cur);
			for(int i = 0; i < N; i++)
				if(adjMatrix[cur][i])
				{
					adjMatrix[cur][i] = false;
					parents[i]--;
					if(parents[i]==0)
						roots.add(i);
				}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(br.ready())
		{
			N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			adjMatrix = new boolean[N][N];
			
			
			String[] beverages = new String[N];
			TreeMap<String, Integer> nums = new TreeMap<String, Integer>();
			for(int i = 0; i < N; i++)
			{
				beverages[i] = br.readLine();
				nums.put(beverages[i], i);
			}
			int M = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			parents = new int[N];
			
			while(M-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = nums.get(st.nextToken());
				int v = nums.get(st.nextToken());
				if(u==v || adjMatrix[u][v])
					continue;
				adjMatrix[u][v] = true;
				parents[v]++;
			}
			
			
			//Topsort and output
			array = new LinkedList<Integer>();
			toposort();
			
			out.printf("Case #%d: Dilbert should drink beverages in this order: ",tc++);
			
		
			for(int i = 0; i < N - 1; i++)
				out.print(beverages[array.get(i)]+" ");
			out.println(beverages[array.get(N-1)]+".\n");	
		
			
			
			br.readLine();
			
		}
		out.flush();
		out.close();
	}
}
