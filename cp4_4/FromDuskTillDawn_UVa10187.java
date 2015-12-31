//package cp4_4;
//
//import java.util.*;
//import java.io.*;
//
//public class FromDuskTillDawn_UVa10187 {
//
//	static int N;
//	static LinkedList<Triple>[] adjList;
//	static final int INF =10000000;		//????????
//	public static int dijkstra(int S, int T)
//	{
//		
//		int[] time = new int[N];
//		Arrays.fill(time, INF);
//		time[S] = 0;
//		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
//		q.add(new Triple(S,0,-1));
//		while(!q.isEmpty())
//		{
//			
//			Triple cur = q.remove();
//			if(cur.curTime > time[cur.city])
//				continue;
//			int minDep = (cur.curTime%12 + 18)%24;
//			if(minDep <= 6)
//				minDep += 24;
//			for(int i = 0, size = adjList[cur.city].size(); i < size; i++)
//			{
//				Triple next = adjList[cur.city].get(i);
//				int nextDep = next.curTime;
//				if(nextDep <= 6)
//					nextDep += 24;
//				if(minDep > nextDep)
//					nextDep += 12;
//				if(cur.curTime + nextDep - minDep + next.duration < time[next.city])
//				{
//					time[next.city] = cur.curTime + nextDep - minDep + next.duration;
//					q.add(new Triple(next.city,time[next.city],-1));
//				}
//				
//			}
//			
//			
//		}
//		
//		if(time[T]==INF)
//			return -1;
//		return (time[T]-1)/12;
//		
//	}
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(System.out);
//		
//		int TC = Integer.parseInt(br.readLine());
//		for(int k = 1; k <= TC; k++)
//		{
//			N = 0;
//			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
//			adjList = new LinkedList[100];
//			int E = Integer.parseInt(br.readLine());
//			while(E-->0)
//			{
//				StringTokenizer st = new StringTokenizer(br.readLine());
//				String x = st.nextToken();
//				String y = st.nextToken();
//				int u,v;
//				if(map.containsKey(x))
//					u = map.get(x);
//				else
//				{
//					u = N;
//					adjList[u] = new LinkedList<Triple>();
//					map.put(x, N++);
//				}
//				if(map.containsKey(y))
//					v = map.get(y);
//				else
//				{
//					v = N;
//					adjList[v] = new LinkedList<Triple>();
//					map.put(y, N++);
//				}
//				int dep = Integer.parseInt(st.nextToken());
//				int dur = Integer.parseInt(st.nextToken());
//				int arr = (dep + dur)%24;
//				if(dep > 6 && dep < 18 || arr > 6 && arr < 18)
//					continue;
//				int a = dep<=6?dep+24:dep;
//				int b = arr<=6?arr+24:arr;
//				if(b-a < 0)
//					continue;
//				adjList[u].add(new Triple(v,dep%24,dur));
//			}
//			out.printf("Test Case %d.\n",k);
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			String source = st.nextToken();
//			String dest = st.nextToken();
//			if(!map.containsKey(source)|| !map.containsKey(dest))
//			{
//				if(source.equals(dest))
//					out.printf("Vladimir needs %d litre(s) of blood.\n",0);
//				else
//					out.println("There is no route Vladimir can take.");
//				continue;
//			}
//			
//			int S = map.get(source);
//			int T = map.get(dest);
//			int ans = dijkstra(S,T);
//			
//			if(ans==-1)
//				out.println("There is no route Vladimir can take.");
//			else
//				out.printf("Vladimir needs %d litre(s) of blood.\n",ans);
//		}
//		
//		out.flush();
//	}
//}
//
//class Triple implements Comparable<Triple>
//{
//	int city,curTime,duration;
//	Triple(int x, int y, int z) {city = x; duration = z; curTime = y;}
//	
//	public int compareTo(Triple t) {return curTime - t.curTime;}
//	
//	public String toString()
//	{
//		return city + " " + curTime + " " + duration;
//	}
//	
//}
//
