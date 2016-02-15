<<<<<<< HEAD
package cp1_3;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class TheSnail{
	
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
			double u = Integer.parseInt(st.nextToken());
			double d = Integer.parseInt(st.nextToken());
			double f = Integer.parseInt(st.nextToken())*1.0/100;
			int day = 0;double curU = 0;
			double curH = 0;boolean reached = false;
			while(!reached)
			{
				day++;
				curU = u-(day-1)*u*f;
				if(curU>0)
					curH = curH + curU;
				if(curH>h)
					reached = true;
				else
				{
					curH = curH - d;
					if(curH<0)
						break;
				}
			}
			if(reached)
				sb.append("success on day "+day+"\n");
			else
				sb.append("failure on day "+day+"\n");
		}
		
		System.out.print(sb);
	}
		
}
=======
package cp1_3;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class TheSnail{
	
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
			double u = Integer.parseInt(st.nextToken());
			double d = Integer.parseInt(st.nextToken());
			double f = Integer.parseInt(st.nextToken())*1.0/100;
			int day = 0;double curU = 0;
			double curH = 0;boolean reached = false;
			while(!reached)
			{
				day++;
				curU = u-(day-1)*u*f;
				if(curU>0)
					curH = curH + curU;
				if(curH>h)
					reached = true;
				else
				{
					curH = curH - d;
					if(curH<0)
						break;
				}
			}
			if(reached)
				sb.append("success on day "+day+"\n");
			else
				sb.append("failure on day "+day+"\n");
		}
		
		System.out.print(sb);
	}
		
}
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
