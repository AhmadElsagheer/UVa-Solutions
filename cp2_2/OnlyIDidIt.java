package cp2_2;

import java.io.*; 
import java.util.*;

public class OnlyIDidIt{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();StringTokenizer st;int n;
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++)
		{
			br.readLine();
			sb.append("Case #"+i+":\n");
			Friend[] friends = new Friend[3];
			for(int j = 0; j < 3; j++)
			{
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				friends[j] = new Friend(n);
				friends[j].addProblems(st);
				
			}
			
			int count1 = countUnique(friends[0],friends[1],friends[2]);
			int count2 = countUnique(friends[1],friends[0],friends[2]);		
			int count3 = countUnique(friends[2],friends[0],friends[1]);
			if(count1>=count2&&count1>=count3)
				appendOut(sb,friends[0],1);
			if(count2>=count1&&count2>=count3)
				appendOut(sb,friends[1],2);
			if(count3>=count1&&count3>=count2)
				appendOut(sb,friends[2],3);
		}
		System.out.print(sb);
	}
	
	public static void appendOut(StringBuilder sb, Friend x, int k)
	{
		sb.append(k+" "+x.unique.size());
		for(int i = 0; i < x.unique.size(); i++)
			sb.append(" "+x.unique.get(i));
		sb.append("\n");
	}
	
	public static int countUnique(Friend cur, Friend other1, Friend other2)
	{
		
		for(int i = 0; i < cur.all.length; i++)
		{
			if(!other1.h.contains(cur.all[i])&&!other2.h.contains(cur.all[i]))
				cur.unique.add(cur.all[i]);
				
		}
				
		
		Collections.sort(cur.unique);
		return cur.unique.size();	
	}
	
	private static class Friend
	{
		HashSet<Integer> h;
		int[] all;
		ArrayList<Integer> unique;
		
		public Friend(int n)
		{
			h = new HashSet<Integer>();
			all = new int[n];
			unique = new ArrayList<Integer>();
		}
		
		public void addProblems(StringTokenizer st)
		{
			for(int i = 0; i < all.length; i++)
			{
				all[i] = Integer.parseInt(st.nextToken());
				h.add(all[i]);
			}
		}
		
		
	}
	
	
	
}	
