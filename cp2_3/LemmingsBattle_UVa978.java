package cp2_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LemmingsBattle_UVa978 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{	
			StringTokenizer st = new StringTokenizer(br.readLine());
			int B = Integer.parseInt(st.nextToken());
			int SG = Integer.parseInt(st.nextToken());
			int SB = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Integer> g = new PriorityQueue<Integer>();
			PriorityQueue<Integer> b = new PriorityQueue<Integer>();
			
			while(SG-->0)
				g.add(-Integer.parseInt(br.readLine()));
			
			while(SB-->0)
				b.add(-Integer.parseInt(br.readLine()));
			
			while(!g.isEmpty() && !b.isEmpty())
			{
				int battles = Math.min(B, Math.min(g.size(),b.size()));
				
				int[] green = new int[battles];
				for(int i = 0; i < battles; i++)
					green[i] = -g.remove();
				
				int[] blue = new int[battles];
				for(int i = 0; i < battles; i++)
					blue[i] = -b.remove();
				
				for(int i = 0; i < battles; i++)
					if(blue[i]!=green[i])
						if(blue[i] > green[i])
							b.add(green[i]-blue[i]);
						else
							g.add(blue[i]-green[i]);
			}
			if(g.isEmpty() && b.isEmpty())
				sb.append("green and blue died\n");
			else
				if(g.isEmpty())
				{
					sb.append("blue wins\n");
					while(!b.isEmpty())
						sb.append(-b.remove()).append("\n");
				}
				else
				{
					sb.append("green wins\n");
					while(!g.isEmpty())
						sb.append(-g.remove()).append("\n");
				}
			
			if(tc!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	
	}
}
