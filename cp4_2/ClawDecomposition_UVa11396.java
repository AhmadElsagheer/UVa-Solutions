package cp4_2;
//
//
//import java.util.*;
//import java.io.*;
//
//public class Claw {
//
//	static boolean[][] adjMatrix;
//	static int[] visited;
//	static int N;
//	
//	
//	public static boolean bfs(int u)
//	{
//		visited[u] = 1;
//		LinkedList<Integer> q = new LinkedList<Integer>();
//		q.add(u);
//		while(!q.isEmpty())
//		{
//			u = q.remove();
//			for(int i = 0; i < N; i++)
//			{
//				if(!adjMatrix[u][i])
//					continue;
//				if(visited[i]!=0)
//					if(visited[i]==visited[u])
//						return false;
//					else
//						continue;
//				else
//				{
//					visited[i] = -visited[u];
//					q.add(i);
//				}
//			}
//		}
//		return true;
//	}
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		while(true)
//		{
//			N = Integer.parseInt(br.readLine());
//			if(N==0)
//				break;
//			adjMatrix = new boolean[N][N];
//			while(true)
//			{
//				StringTokenizer st = new StringTokenizer(br.readLine());
//				int u = Integer.parseInt(st.nextToken()) - 1;
//				int v = Integer.parseInt(st.nextToken()) - 1;
//				if(u==-1)
//					break;
//				adjMatrix[u][v] = true;
//				adjMatrix[v][u] = true;
//			}
//			visited = new int[N];
//			
//			sb.append(bfs(0)?"YES":"NO").append("\n");
//				
//		}
//		System.out.print(sb);
//		
//	}
//}
