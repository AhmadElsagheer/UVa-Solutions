package cp3_4;
import java.util.*;
import java.io.*;

public class Packets_UVa311 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
	
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] packets = new int[7];
			packets[1] = Integer.parseInt(st.nextToken());
			packets[2] = Integer.parseInt(st.nextToken());
			packets[3] = Integer.parseInt(st.nextToken());
			packets[4] = Integer.parseInt(st.nextToken());
			packets[5] = Integer.parseInt(st.nextToken());
			packets[6] = Integer.parseInt(st.nextToken());
			int count = packets[6];
			if(packets[5]!=0)
			{
				count += packets[5];
				packets[1] -= packets[5]*11;
			}
			if(packets[4]!=0)
			{
				count += packets[4];
				packets[2] -= packets[4]*5;
			}
			if(packets[3]!=0)
			{
				count += packets[3]/4+(packets[3]%4==0?0:1);
				if(packets[3]%4==1)
				{
					packets[2] -= 5;
					packets[1] -= 7;
				}
				else
					if(packets[3]%4==2)
					{
						packets[2] -= 3;
						packets[1] -= 6;
					}
					else
						if(packets[3]%4==3)
						{
							packets[2] -= 1;
							packets[1] -= 5;
						}
			}
			if(packets[2]<0)
			{
				packets[1] += packets[2]*4;
				packets[2] = 0;
			}
			if(packets[2]!=0)
			{
				count += packets[2]/9;
				if(packets[2]%9!=0)
				{
					count += 1;
					packets[1] -= 36 - packets[2]%9 * 4;
				}
				
			}
			if(packets[1]>0)
			{
				count += packets[1]/36 + (packets[1]%36!=0?1:0);
			}
			if(count==0)
				break;
			out.println(count);
		}
		out.flush();
	}
}
