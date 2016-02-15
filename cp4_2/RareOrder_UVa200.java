package cp4_2;
import java.util.*;
import java.io.*;

public class RareOrder_UVa200 {

	static boolean[][] adjMatrix = new boolean[26][26];
	static boolean[] visited = new boolean[26];
	static boolean[] exist = new boolean[26];
	static LinkedList<Integer> array = new LinkedList<Integer>();
	public static void dfs(int u)
	{
		visited[u] = true;
		for(int i = 0; i < 26; i++)
			if(adjMatrix[u][i] && !visited[i])
				dfs(i);
		array.addFirst(u);
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String last=null,x;
		while(!(x=br.readLine()).equals("#"))
		{
			for(int i = 0; i < x.length(); i++)
				exist[x.charAt(i)-'A'] = true;
			if(last==null)
			{
				last = x;
				continue;
			}
			int start = 0;
			while(start < last.length() && start < x.length() && last.charAt(start)==x.charAt(start))
				start++;
			if(start==last.length() || start==x.length())
			{
				last = x;
				continue;
			}
			int u = last.charAt(start) - 'A';
			int v = x.charAt(start) - 'A';
			adjMatrix[u][v] = true;
			last = x;
		}
		
		
		for(int i = 0; i < 26; i++)
			if(exist[i] && !visited[i])
				dfs(i);
		while(!array.isEmpty())
			sb.append((char)('A'+array.remove()));
		System.out.println(sb);
	}
}
