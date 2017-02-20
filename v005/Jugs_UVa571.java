package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Jugs_UVa571 {

	static int A,B;
	static StringBuilder sb = new StringBuilder();
	static TreeMap<Integer,String> map;
	static Pair[][] parent;
	
	static Pair pour(Pair cur, int k)
	{
		int x = cur.a;
		int y = cur.b;
		switch(k)
		{
		case 1: x = A;break;
		case 2: y = B;break;
		case 3: x = 0;break;
		case 4: y = 0;break;
		case 5: y += x;x = 0;if(y>B) {x = y - B;y = B;}break;
		default:x += y;y = 0;if(x>A) {y = x - A;x = A;}
			
		}
		
		return new Pair(x,y);
	}
	static void bfs(int d)
	{
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visited = new boolean[A+1][B+1];	
		visited[0][0] = true;
		q.add(new Pair(0,0));
		Pair cur = null;
		while(!q.isEmpty())
		{
			cur  = q.remove();
			if(cur.b == d)
				break;
			for(int k = 1; k <= 6; k++)
			{
				Pair next = pour(cur,k);
				if(!visited[next.a][next.b])
				{
					parent[next.a][next.b] = cur;
					visited[next.a][next.b] = true;
					q.add(next);
				}
				
			}
		}
		print(cur);
		sb.append("success\n");
		
	}
	
	static void print(Pair cur)
	{
		if(parent[cur.a][cur.b]==null)
			return;
		Pair p = parent[cur.a][cur.b];
		print(p);
		for(int k = 1; k <= 6; k++)
			if(cur.equals(pour(p,k)))
			{
				sb.append(map.get(k)).append("\n");return;
			}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map =new TreeMap<Integer,String>();
		map.put(1, "fill A");map.put(2, "fill TheTravelingJudgesProblem_UVa1040");map.put(3, "empty A");
		map.put(4, "empty TheTravelingJudgesProblem_UVa1040");map.put(5, "pour A TheTravelingJudgesProblem_UVa1040");map.put(6, "pour TheTravelingJudgesProblem_UVa1040 A");
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			parent  = new Pair[A+1][B+1];
			bfs(d);
		}
		
		System.out.print(sb);
	}
	
}

class Pair
{
	int a,b;
	Pair(int x, int y){a  = x; b = y;}
	
	boolean equals(Pair x)
	{
		return x.a == a && x.b == b;
	}
}