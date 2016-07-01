package v106;
//package cp8_3;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.text.DecimalFormat;
//import java.util.StringTokenizer;
//
//
//public class Menu_UVa10645 {
//
//	static final int INF = 100000000;
//	static int N, M, K;
//	static int[] v, c;
//	static Pair[][][][] memo;
//	static StringBuilder sb = new StringBuilder();
//	
//	static Pair dp(int last_dish, int f, int day, int budget)
//	{
//		if(budget < 0) return new Pair(0, INF);
//		if(day == K) return new Pair(0,M - budget);
//		if(memo[last_dish][f][day][budget] != null) return memo[last_dish][f][day][budget];
//		
//		Pair best = new Pair(0,INF);
//		for(int i = 1; i <= N; i++)
//		{
//			
//			Pair nxt = dp(i,i == last_dish?1:0, day + 1, budget - c[i]);
//			if(nxt.cost == INF)
//				continue;
//			double x = (i == last_dish?(f == 0?0.5:0):1)*v[i];
//			if(nxt.ben + x> best.ben || Math.abs(nxt.ben + x - best.ben) <= 10e-9)
//			{
//				if(Math.abs(nxt.ben + x - best.ben) <= 10e-9)
//					best.cost = Math.min(best.cost, nxt.cost);
//				else
//				{
//					best.ben = nxt.ben + x;
//					best.cost = nxt.cost;
//				}
//			}
//		}
//		return memo[last_dish][f][day][budget] = best;
//			
//	}
//	
//	static void print(int last_dish, int f, int day, int budget)
//	{
//		if(budget < 0) return;
//		if(day == K) return;
//		
//		Pair optimal = dp(last_dish, f, day, budget);
//		for(int i = 1; i <= N; i++)
//		{
//			
//			Pair nxt = dp(i,i == last_dish?1:0, day + 1, budget - c[i]);
//			if(nxt.cost == INF)
//				continue;
//			double x = (i == last_dish?(f == 0?0.5:0):1)*v[i];
//			if(Math.abs(nxt.ben + x - optimal.ben) <= 10e-9 && optimal.cost == nxt.cost)
//			{
//				sb.append(i).append(day==K-1?"\n":" ");
//				print(i,i == last_dish?1:0, day + 1, budget - c[i]);
//				return;
//			}
//		}
//		
//			
//	}
//	
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		while(true)
//		{
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			K = Integer.parseInt(st.nextToken());
//			if(K == 0)
//				break;
//			N = Integer.parseInt(st.nextToken());
//			M = Integer.parseInt(st.nextToken());
//			
//			v = new int[N+1];
//			c = new int[N+1];
//			
//			for(int i = 1; i <= N; i++)
//			{
//				st = new StringTokenizer(br.readLine());
//				c[i] = Integer.parseInt(st.nextToken());
//				v[i] = Integer.parseInt(st.nextToken());				
//			}
//			
//			memo = new Pair[N+1][2][K+1][M+1];
//			
//			//check if cost > budget
//			Pair ans = dp(0,0,0,M);
//			
//
//			if(ans.cost > M)
//				sb.append("0.0\n\n");	
//			else
//			{
//				sb.append(new DecimalFormat("0.0").format(ans.ben)+"\n");
//				print(0,0,0,M);
//			}
//			
//		}
//		System.out.print(sb);
//	}
//}
//
//class Pair
//{
//	double ben;
//	int cost;
//	Pair(double x, int y)
//	{
//		ben = x;
//		cost = y;
//	}
//}
