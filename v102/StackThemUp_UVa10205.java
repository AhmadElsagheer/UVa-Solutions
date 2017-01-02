package v102;
import java.util.*;
import java.io.*;

public class StackThemUp_UVa10205 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<Integer,Pair> cards = new TreeMap<Integer,Pair>();
		int a = 0;
		for(int i = 1; i <= 4; i++)
			for(int j = 2; j <= 14; j++)
				cards.put(a++, new Pair(j,i));
		
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] decks = new int[N][52];
			
			for(int i = 0; i < N; i++)
				for(int j = 0; j < 52; j++)
				{
					if(!st.hasMoreTokens())
						st = new StringTokenizer(br.readLine());
					decks[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			int M = 0;
			String line;
			LinkedList<Integer> shuffle = new LinkedList<Integer>();
			while(br.ready() && !(line=br.readLine()).equals(""))
				shuffle.add(Integer.parseInt(line) - 1);
			
			
			int[] last = new int[52];
			for(int i = 0; i < 52; i++)
				last[i] = i;
			for(int i = 0; i < shuffle.size(); i++)
			{
				int[] cur = new int[52];
				int k = shuffle.get(i);
				for(int j = 0; j < 52; j++)
					cur[j] = last[decks[k][j]];
				last = cur;
			}
			for(int i = 0; i < 52; i++)
			{
				Pair cur = cards.get(last[i]);
				switch(cur.num)
				{
				case 14:	out.print("Ace of ");break;
				case 11:	out.print("Jack of ");break;
				case 12:	out.print("Queen of ");break;
				case 13:	out.print("King of ");break;	
					default:	out.printf("%d of ",cur.num);
				}
				switch(cur.suit)
				{
				case 1:	out.println("Clubs");break;
				case 2:	out.println("Diamonds");break;
				case 3:	out.println("Hearts");break;
					default:	out.println("Spades");
				}
			}
			if(TC!=0)
				out.println();
		}
		out.flush();
		out.close();
	}
	
	static class Pair
	{
		int num, suit;
		Pair(int x, int y) {num  = x; suit = y;}
	}
}