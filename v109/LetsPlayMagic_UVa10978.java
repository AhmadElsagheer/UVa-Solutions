package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LetsPlayMagic_UVa10978{
	
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