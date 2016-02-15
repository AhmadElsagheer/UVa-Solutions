package cp1_3;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class LaserSculpture{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			if(h==0)
				break;
			int w = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int turns = 0;int pre = 0;
			for(int i = 0; i < w; i++)
			{
				int cur = h - Integer.parseInt(st.nextToken());
				if(cur>pre)
				{
					turns = turns + cur - pre;
				}
				pre = cur;
			}
			sb.append(turns+"\n");
			
		}
		System.out.print(sb);
	
	
	}
}	