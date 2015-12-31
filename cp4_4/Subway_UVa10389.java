//package cp4_4;
//
//import java.util.*;
//import java.io.*;
//
//public class Subway_UVa10389 {
//
//	static double[][] adjMatrix;
//	static int N;
//	static final long INF = 1000000000000L;
//	
//	public static long dijkstra(int S, int T)
//	{
//		double[] time = new double[N];
//		Arrays.fill(time, INF);
//		time[S] = 0;
//		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
//		q.add(new Pair(S,0));
//		while(!q.isEmpty())
//		{
//			Pair cur = q.remove();
//			if(cur.time > time[cur.node])
//				continue;
//			for(int i = 0; i < N; i++)
//				if(cur.node!=i)
//					if(cur.time + adjMatrix[cur.node][i] < time[i])
//					{
//						time[i] = cur.time + adjMatrix[cur.node][i];
//						q.add(new Pair(i,time[i]));
//					}	
//		}
//		
//		return Math.round(time[T]/60);
//	}
//	
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(System.out);
//		
//		int TC = Integer.parseInt(br.readLine());
//		br.readLine();
//		while(TC-->0)
//		{
//			TreeMap<Point,Integer> points  = new TreeMap<Point,Integer>();
//			Point[] nums = new Point[250];
//			adjMatrix = new double[204][204];
//			for(int i = 0; i < 204; i++)
//				Arrays.fill(adjMatrix[i], -10);
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			Point home = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
//			Point school = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
//			nums[0] = home; nums[1] = school;
//			points.put(home,0);
//			points.put(school,1);
//			adjMatrix[0][1] = adjMatrix[1][0] = Math.hypot(home.x - school.x, home.y - school.y)/50*18;
//			N = 2;
//			String line;
//			while(br.ready() && !(line=br.readLine()).equals(""))
//			{
//				st = new StringTokenizer(line);
//				Point last = null;	int u = -1;
//				while(true)
//				{
//					if(!st.hasMoreTokens())
//						st = new StringTokenizer(br.readLine());
//					int x = Integer.parseInt(st.nextToken());
//					int y = Integer.parseInt(st.nextToken());
//					if(x==-1 && y==-1)
//						break;
//					Point cur = new Point(x,y);int v;
//					if(points.containsKey(cur))
//						v = points.get(cur);
//					else
//					{
//						v = N++;
//						nums[v] = cur;
//						points.put(cur, v);
//					}
//					if(last!=null)
//						adjMatrix[u][v] = adjMatrix[v][u] = Math.hypot(cur.x - last.x, cur.y - last.y)/200*18;
//					adjMatrix[0][v] = adjMatrix[v][0] = Math.hypot(cur.x - home.x, cur.y - home.y)/50*18;
//					adjMatrix[1][v] = adjMatrix[v][1] = Math.hypot(cur.x - school.x, cur.y - school.y)/50*18;
//					last = cur;
//					u = v;
//						
//				}
//			}
//			for(int i = 0; i < N; i++)
//				for(int j = i + 1; j < N; j++)
//					if(adjMatrix[i][j]<-1)
//						adjMatrix[i][j] = adjMatrix[j][i] = Math.hypot(nums[i].x - nums[j].x, nums[i].y - nums[j].y)/50*18;
//					
//			long minTime = dijkstra(0,1);
//			out.println(minTime);
//			if(TC!=0)
//				out.println();
//			
//			
//		}
//		
//		out.flush();
//		out.close();
//	}
//}
//
//class Point implements Comparable<Point>
//{
//	int x,y;
//	Point(int a, int b) { x = a; y = b;}
//	
//	public int compareTo(Point p) {
//		if(this.x!=p.x)
//			return this.x - p.x;
//		return this.y - p.y;
//	}
//}
//
//class Pair implements Comparable<Pair>
//{
//	int node; double time;
//	Pair(int x, double t) {node = x; time = t;}
//	
//	public int compareTo(Pair p) {
//		if(this.time <= p.time)
//			return -1;
//		return 1;
//	}
//	
//}
