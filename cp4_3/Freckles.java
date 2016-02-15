package cp4_3;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


public class Freckles {

	
	static double[][] adjMatrix;
	static boolean[] visited;

	public static double mst()
	{
		double MST = 0;
		PriorityQueue<Pair> x = new PriorityQueue<Pair>();
		x.add(new Pair(0,0));
		while(!x.isEmpty())
		{
			Pair cur = x.remove();
			if(visited[cur.v])
				continue;
			visited[cur.v] = true;
			MST += cur.d;
			for(int i = 0; i < adjMatrix.length; i++)
			{
				if(!visited[i])
					x.add(new Pair(i,adjMatrix[cur.v][i]));	
				
			}
		}
		
		
		
		return MST;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			int N = Integer.parseInt(br.readLine());
			double[][] points = new double[N][2];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				points[i][0] = x; points[i][1] = y;
			}
			adjMatrix = new double[N][N];
			for(int i = 0; i < N; i++)
				for(int j = i + 1; j < N; j++)
				{
					double x = points[i][0] - points[j][0];
					double y = points[i][1] - points[j][1];
					adjMatrix[i][j] = adjMatrix[j][i] =Math.hypot(x, y);
				}
			
			visited = new boolean[N];
			
			
			sb.append(new DecimalFormat("0.00").format(mst())+"\n");
			
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}

	

}
class Pair implements Comparable<Pair>
{
	int v; double d;
	Pair(int v, double d){this.v = v; this.d = d;}

	@Override
	public int compareTo(Pair x) {
		
		if(this.d <= x.d)
			return -1;
		else
			return 1;
	}
}
