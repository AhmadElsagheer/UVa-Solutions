package cp2_2;

import java.io.*; 
import java.util.*;

public class LetsPlayMagic{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n;
		while((n=Integer.parseInt(br.readLine()))!=0)
		{
			String[] cards = new String[n]; int curP = -1;
			for(int i = 0; i < n; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				String curCard = "" + st.nextToken();
				int count = st.nextToken().length();
				while(count>0)
				{
					curP = (curP+1)%n;
					if(cards[curP]==null)
						count--;
				}
				cards[curP] = curCard;
			}
			sb.append(cards[0]);
			for(int i = 1; i < n; i++)
				sb.append(" "+cards[i]);
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
}	