package cp1_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class EventPlanning {

	public static void main(String[] args) throws IOException 
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			String r = "stay home";
			for(int i = 0; i < h; i++)
			{
				boolean affordable = false;
				int cost = Integer.parseInt(br.readLine())*n;
				if(cost<=b)
					affordable = true;
				boolean enoughBeds = false;
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++)
				{
					int beds = Integer.parseInt(st.nextToken());
					if(beds>=n)
						enoughBeds = true;
				}
				if(enoughBeds&&affordable)
				{
					if(r.equals("stay home")||Integer.parseInt(r)>cost)
						r = ""+cost;
				}
			}
			sb.append(r+"\n");
			
		}
		System.out.print(sb);
		
		
	
	}
	
	
	
}