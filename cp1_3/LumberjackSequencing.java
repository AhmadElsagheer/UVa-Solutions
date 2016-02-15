package cp1_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LumberjackSequencing{

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("Lumberjacks:\n");
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			boolean increasing;
			if(first<second)
				increasing = true;
			else
				increasing = false;
			int j;int previous = second;
			for(j = 2; j < 10; j++)
			{
				int current = Integer.parseInt(st.nextToken());
				if(current>previous)
				{
					if(!increasing) break;
				}
				else
					if(increasing) break;
			}
			if(j==10)
				sb.append("Ordered\n");
			else
				sb.append("Unordered\n");
		}
		
		System.out.print(sb);
		
		
	}
}
