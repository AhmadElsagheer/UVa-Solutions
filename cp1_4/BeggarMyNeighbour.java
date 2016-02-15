package cp1_4;

//UVa 00162 Beggar My Neighbour 
// There was a presentation error >> see out put format =)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class BeggarMyNeighbour {

	public static void main(String[] args) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		while(!s.equals("#"))
		{
			LinkedList<String> n = new LinkedList<String>();
			LinkedList<String> d = new LinkedList<String>();
			LinkedList<String> stack = new LinkedList<String>();
			n.add(s);
			for(int i = 1; i < 52; i++)
			{	
				if(i%13==0)
					st = new StringTokenizer(br.readLine());
				if(i%2==1)
					d.addFirst(st.nextToken());
				else
					n.addFirst(st.nextToken());
			}
			int winner = 0; int curPlayer = 0; int cover = 0;boolean covering = false;
			while((!n.isEmpty()&&curPlayer==0)||(!d.isEmpty()&&curPlayer==1))
			{
				String curCard;
				if(curPlayer==0)
					curCard = n.remove();
				else
					curCard = d.remove();
				stack.addFirst(curCard);
				if(covering)
					cover--;
				
				switch(curCard.charAt(1))
				{
				case 'A': cover=4;winner = curPlayer; curPlayer = (curPlayer+1)%2;covering = true;break;
				case 'K': cover=3;winner = curPlayer; curPlayer = (curPlayer+1)%2;covering = true;break;
				case 'Q': cover=2;winner = curPlayer; curPlayer = (curPlayer+1)%2;covering = true;break;
				case 'J': cover=1;winner = curPlayer; curPlayer = (curPlayer+1)%2;covering = true;break;
				}
				if(cover==0 && covering)
				{
					covering = false;
					LinkedList<String> p;
					if(curPlayer==0)
						p = d;
					else
						p = n;
					while(!stack.isEmpty())
					{
						p.add(stack.removeLast());
					}
				}
				
				if(cover==0)
					curPlayer = (curPlayer+1)%2;
		
			}
			String x = 52-stack.size()+"";
			if(x.length()==1)
				x = " " + x;
			sb.append((winner==0?"2":"1")+" "+x+"\n");
			

			st = new StringTokenizer(br.readLine());
			s = st.nextToken();
		}
		System.out.print(sb);
	
	}
	
}
