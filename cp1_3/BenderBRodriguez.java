<<<<<<< HEAD
package cp1_3;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class BenderBRodriguez{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int l = Integer.parseInt(br.readLine());
			if(l==0)
				break;
			StringTokenizer st = new StringTokenizer(br.readLine());
			char orAxis = 'x';
			int orCount = 0;
			for(int i = 0; i < l-1; i++)
			{
				String s = st.nextToken();
				if(s.charAt(0)=='N')
					continue;
				if(orCount%2==0)
					orAxis = s.charAt(1);
				if(s.charAt(1)==orAxis)
					if(s.charAt(0)=='+')
						orCount = (orCount+1)%4;
					else
					{
						orCount--;
						if(orCount==-1)
							orCount = 3;
					}
				if(orCount%2==0)
					orAxis = 'x';
			}
			String r = ""+orAxis;
			if(orCount<2)
				r = "+" + r;
			else
				r = "-" + r;
			sb.append(r+"\n");
		}
		System.out.print(sb);
	
	
	}
=======
package cp1_3;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class BenderBRodriguez{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int l = Integer.parseInt(br.readLine());
			if(l==0)
				break;
			StringTokenizer st = new StringTokenizer(br.readLine());
			char orAxis = 'x';
			int orCount = 0;
			for(int i = 0; i < l-1; i++)
			{
				String s = st.nextToken();
				if(s.charAt(0)=='N')
					continue;
				if(orCount%2==0)
					orAxis = s.charAt(1);
				if(s.charAt(1)==orAxis)
					if(s.charAt(0)=='+')
						orCount = (orCount+1)%4;
					else
					{
						orCount--;
						if(orCount==-1)
							orCount = 3;
					}
				if(orCount%2==0)
					orAxis = 'x';
			}
			String r = ""+orAxis;
			if(orCount<2)
				r = "+" + r;
			else
				r = "-" + r;
			sb.append(r+"\n");
		}
		System.out.print(sb);
	
	
	}
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	