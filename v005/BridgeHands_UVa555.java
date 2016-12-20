package v005;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BridgeHands_UVa555 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			char dealer = sc.nextLine().charAt(0);
			if(dealer=='#')
				break;
			int start;
			ArrayList<Pair>[] sets = new ArrayList[4];
			for(int i = 0; i < 4; i++)
				sets[i] = new ArrayList<Pair>(13);
			switch(dealer)
			{
			case 'N':start = 2;break;
			case 'E':start = 3;break;
			case 'S':start = 0;break;
				default:start = 1;
			}
			for(int i = 0; i < 2; i++)
			{
				String line = sc.nextLine();
				for(int j = 0; j < 52; j+=2)
					sets[(++start)%4].add(new Pair(new String(new char[]{line.charAt(j),line.charAt(j+1)})));
			}
			for(int i = 0; i < 4; i++)
				Collections.sort(sets[i]);
			
			char[] dir = new char[]{'S','W','N','E'};
			for(int i = 0; i < 4; i++)
			{
				out.printf("%c:",dir[i]);
				for(int j = 0; j < 13; j++)
					out.printf(" %s",sets[i].get(j));
				out.println();
			}
			
			
		}
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>
	{
		String card;
		int suit,num;
		Pair(String card)
		{
			this.card = card;
			switch(card.charAt(0))
			{
			case 'C':suit = 1;break;
			case 'D':suit = 2;break;
			case 'S':suit = 3;break;
				default:suit = 4;
			}
			switch(card.charAt(1))
			{
			case 'A': num = 14;break;
			case 'K': num = 13;break;
			case 'Q': num = 12;break;
			case 'J': num = 11;break;
			case 'T': num = 10;break;
			default: num = card.charAt(1) -'0';
			}
		}
		
		public String toString()
		{
			return this.card;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.suit!=o.suit)
				return this.suit - o.suit;
			return this.num - o.num;
		}
	}
}

