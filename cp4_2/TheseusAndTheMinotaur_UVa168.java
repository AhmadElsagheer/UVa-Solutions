package cp4_2;
import java.util.*;
import java.io.*;

public class TheseusAndTheMinotaur_UVa168 {

	static LinkedList<Integer>[] adjList;
	static int trapped;
	static LinkedList<Integer> orderedLit;
	static boolean[] lit;
	static int k;
	
	public static void traverse(int cur, int last, int num)
	{
		
		
		while(true)
		{
			int next = -1;
			for(int i = 0, size = adjList[cur].size(); i < size; i++)
			{
				next = adjList[cur].get(i);
				if(next != last && !lit[next])
					break;
				next = -1;
			}
			if(next==-1)
			{
				trapped = cur;
				break;
			}
			else
			{
				if(num%k==0)
				{
					lit[cur] = true;
					orderedLit.add(cur);
				}
				last = cur;
				cur = next;
				num++;
			}
		}
		
			
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			if(line.equals("#"))
				break;
			adjList = new LinkedList[26];
			for(int i = 0; i < 26; i++)
				adjList[i] = new LinkedList<Integer>();
			int i = 0;
			while(i!=line.length())
			{
				int u = line.charAt(i++) - 'A';
				if(line.charAt(i)==':')
					i++;
				while(line.charAt(i)!=';' && line.charAt(i)!='.')
				{
					int v = line.charAt(i) - 'A';
					adjList[u].add(v);
					i++;
				}
				i++;
			}
			int cur = st.nextToken().charAt(0) - 'A';
			int last = st.nextToken().charAt(0) - 'A';
			k = Integer.parseInt(st.nextToken());
			lit = new boolean[26];
			orderedLit = new LinkedList<Integer>();
			traverse(cur,last,1);
			
			for(int j = 0, size = orderedLit.size(); j < size; j++)
				out.print((char)(orderedLit.get(j)+'A')+" ");
			out.println("/"+(char)(trapped+'A'));
		}
		out.flush();
	}
}
