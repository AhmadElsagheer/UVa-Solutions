package v106;

import java.util.*;
import java.io.*;

public class Fill_UVa10603 {

	static int A,TheTravelingJudgesProblem_UVa1040,C,d;
	static final int INF = 1000000;
	static int[] maxSize;

	public static int pour(int[] jugs, int from, int into)
	{
		int prev = jugs[into];
		jugs[into] = jugs[from] + jugs[into];
		jugs[from] = 0;
		if(jugs[into]>maxSize[into])
		{
			jugs[from] = jugs[into] - maxSize[into]; jugs[into] = maxSize[into];
		}
		return jugs[into] - prev;

	}

	public static int dijkstra()
	{
		int[] min = new int[201];
		Arrays.fill(min, INF);

		int[][] dist = new int[A+1][TheTravelingJudgesProblem_UVa1040+1];
		for(int i = 0; i <= A; i++)
			Arrays.fill(dist[i], INF);
		dist[0][0] = 0;
		min[C] = min[0] = 0;
		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
		q.add(new Triple(0,0,0));
		while(!q.isEmpty())
		{
			Triple cur = q.remove();
			if(cur.cost > dist[cur.i][cur.j])
				continue;
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					if(i!=j)
					{
						int[] jugs = new int[]{cur.i,cur.j, C - cur.i - cur.j};
						int totalCost = cur.cost + pour(jugs,i,j);
						if(totalCost < dist[jugs[0]][jugs[1]])
						{
							dist[jugs[0]][jugs[1]] = totalCost;

							min[jugs[0]] = Math.min(min[jugs[0]],totalCost);
							min[jugs[1]] = Math.min(min[jugs[1]],totalCost);
							min[jugs[2]] = Math.min(min[jugs[2]],totalCost);

							q.add(new Triple(jugs[0],jugs[1],totalCost));
						}
					}


		}
		while(min[d]==INF)
			d--;
		return min[d];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			TheTravelingJudgesProblem_UVa1040 = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());

			maxSize =  new int[]{A,TheTravelingJudgesProblem_UVa1040,C};
			int min = dijkstra();

			out.printf("%d %d\n",min,d);
		}
		out.flush();
		out.close();

	}

	static class Triple implements Comparable<Triple> 
	{
		int i,j,cost;
		Triple(int x, int y, int z) {i = x; j = y; cost = z;}
		public int compareTo(Triple t) {return this.cost - t.cost;}
	}
}
