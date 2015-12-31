//package cp2_2;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.text.DecimalFormat;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//import java.util.TreeMap;
//
//public class FootballSort_UVa10698 {
//
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(System.out);
//		boolean first = true;
//		while(true)
//		{
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int T = Integer.parseInt(st.nextToken());
//			if(T==0)
//				break;
//			if(first)
//				first = false;
//			else
//				out.println();
//			int G = Integer.parseInt(st.nextToken());
//			Team[] teams = new Team[T];
//			TreeMap<String, Team> map = new TreeMap<String, Team>();
//			for(int i = 0; i < T; i++)
//			{
//				String name = br.readLine();
//				teams[i] = new Team(name);
//				map.put(name, teams[i]);
//			}
//			while(G-->0)
//			{
//				st = new StringTokenizer(br.readLine());
//				Team team1 = map.get(st.nextToken());
//				int goals1 = Integer.parseInt(st.nextToken());
//				st.nextToken();
//				int goals2 = Integer.parseInt(st.nextToken());
//				Team team2 = map.get(st.nextToken());
//				if(goals1==goals2)
//				{
//					team1.points++;
//					team2.points++;
//				}
//				else
//					if(goals1 > goals2)
//						team1.points += 3;
//					else
//						team2.points += 3;
//				team1.played++; team2.played++;
//				team1.out += goals1;team2.in += goals1;
//				team1.in += goals2;team2.out += goals2;
//			}
//			Arrays.sort(teams);
//			for(int i = 0; i < T; i++)
//			{
//				Team cur = teams[i];
//				String per = "N/A";
//				if(cur.played!=0)
//					per = new DecimalFormat("0.00").format(cur.points*1.0/(cur.played*3)*100);
//				String id = "   ";
//				if(i == 0 || teams[i].sameRank(teams[i-1])!=0)
//					id = new DecimalFormat("##.").format(i+1);
//
//				out.printf("%3s%16s%4d%4d%4d%4d%4d%7s\n",id,cur.name,cur.points,cur.played,cur.out,cur.in,cur.out-cur.in,per);
//			}
//		}
//		out.flush();
//		
//	
//	}
//}
//
//class Team implements Comparable<Team>
//{
//	int played, points, in, out;
//	String name;
//	
//	Team(String n) {name = n;}
//	
//	public int compareTo(Team x)
//	{
//		int rank = sameRank(x);
//		if(rank!=0)
//			return rank;
//		return name.toLowerCase().compareTo(x.name.toLowerCase());
//	}
//	
//	public int sameRank(Team x)
//	{
//		if(points != x.points)
//			return x.points - points;
//		if(out - in != x.out - x.in)
//			return (x.out - x.in) - (out - in);
//		return x.out - out;
//	}
//}