package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Transportation_UVa301 {

	static int[][] orders;	
	static int MAX, K,Capacity;
	static int[] people;
	
	static void backtrack(int ticket, int money)
	{
		if(ticket==K)
		{
			MAX = Math.max(money, MAX);
			return;
		}
		boolean overflow = false;	
		for(int i = orders[ticket][0]; i < orders[ticket][1]; i++)
		{
			people[i] += orders[ticket][2];
			if(people[i]>Capacity)
				overflow = true;
		}
		if(!overflow)
			backtrack(ticket+1,money+orders[ticket][2]*(orders[ticket][1]-orders[ticket][0]));
				
		for(int i = orders[ticket][0]; i < orders[ticket][1]; i++)
			people[i] -= orders[ticket][2];
		
		backtrack(ticket+1,money);
			
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb  = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			Capacity = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			if(Capacity==0 && B==0 && K ==0)
				break;
			
			orders = new int[K][3];
			for(int i = 0; i < K; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				orders[i][0] = Integer.parseInt(st.nextToken());
				orders[i][1] = Integer.parseInt(st.nextToken());
				orders[i][2] = Integer.parseInt(st.nextToken());
				
			}
			
			MAX = 0;
			people = new int[B+1];
			backtrack(0,0);
			sb.append(MAX).append("\n");
				
		}
		
		System.out.print(sb);
	}
}
