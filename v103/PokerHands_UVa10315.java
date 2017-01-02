package v103;
import java.util.*;
import java.io.*;

public class PokerHands_UVa10315 {

	public static int compare(Pair[] black, Pair[] white)
	{
		boolean b,w;int fb,fw;
		Arrays.sort(black);
		Arrays.sort(white);
		int[] pB = findPairs(black);
		int[] pW = findPairs(white);
		int higher = higher(black,white);
		
		b = stFlush(black);w = stFlush(white);
		if(b||w)
		{
			if(b&&!w) return 0;if(w&&!b) return 1;
			return higher;
		}
		
		fb = FourOfKind(black);
		fw = FourOfKind(white);
		if(fb!=-1 || fw !=-1)
		{
			if(fb>fw) return 0;	if(fw>fb) return 1;
			return -1;
		}
		
		
		fb = fullHouse(black,pB);
		fw = fullHouse(white,pW);
		if(fb!=-1 || fw!=-1)
		{
			if(fb>fw) return 0;	if(fw>fb) return 1;
			return -1;
		}
		
		b = flush(black);w =flush(white);
		if(b || w)
		{
			if(b&&!w) return 0;if(w&&!b) return 1;
			return higher;
		}

		b = cons(black);w =cons(white);
		if(b || w)
		{
			if(b&&!w) return 0;if(w&&!b) return 1;
			return higher;
		}
		fb = ThreeOfKind(black);
		fw = ThreeOfKind(white);
		if(fb!=-1 || fw!=-1)
		{
			if(fb>fw) return 0;	if(fw>fb) return 1;
			return -1;
		}
		
		int x = twoPair(pB,pW);
		if(x==1)return 1;if(x==0)return 0;
		
		return higher;
	}
	
	public static int fullHouse(Pair[] hand, int[] pairs)
	{
		int card = hand[2].num;
		int count = 0;
		for(int i = 0; i < 5; i++)
			if(hand[i].num==card)
				count++;
		if(count==3 && pairs[0]!=-1)
			return card;
		return -1;
	}
	
	public static int[] findPairs(Pair[] hand)
	{
		int[] pairs = new int[]{-1,-1};
		int[] count = new int[5];
		
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
				if(hand[i].num==hand[j].num)
					count[i]++;
		int i = 0;
		while(i < 5)
		{
			if(count[i]==2)
			{
				pairs[0] = hand[i].num;
				i += count[i];
				break;
			}
			i += count[i];
		}
		while(i < 5)
		{
			if(count[i]==2)
			{
				pairs[1] = hand[i].num;break;
			}
			i += count[i];
		}
		return pairs;
	}
	
	public static int onePair(int[] black, int[] white)
	{
		if(black[0]!=-1)
		{
			if(white[0]==-1)
				return 0;
			else
				if(black[0]!=white[0])
					if(black[0]>white[0])
						return 0;
					else
						return 1;
			return -1;
		}
		else
		{
			if(white[0]!=-1)
				return 1;
			else
				return -1;
		}
	}
	public static int twoPair(int[] black, int[] white)
	{
		if(black[0]==-1 || black[1]==-1)
		{
			if(white[0]==-1 || white[1]==-1)
				return onePair(black,white);
			else
				return 1;
		}
		else
		{
			if(white[0]==-1 || white[1]==-1)
				return 0;
			else
			{
				if(black[0]!=white[0])
				{
					if(black[0]>white[0])
						return 0;
					else
						return 1;
				}
				else
				{
					if(black[1]!=white[1])
					{
						if(black[1]>white[1])
							return 0;
						return 1;
					}
				}
			}
		}
		return -1;
		
	}
	
	public static int ThreeOfKind(Pair[] hand)
	{
		int card = hand[2].num;
		int count = 0;
		for(int i = 0; i < 5; i++)
			if(hand[i].num==card)
				count++;
		return count==3?card:-1;
		
	}
	
	public static boolean cons(Pair[] hand)
	{
		for(int i = 0; i < 4; i++)
			if(hand[i].num- hand[i+1].num!=1)
				return false;
		return true;
	}
	public static boolean flush(Pair[] hand)
	{
		int suit = hand[0].suit;
		for(int i = 1; i < 5; i++)
			if(hand[i].suit!=suit)
				return false;
		return true;
	}
	
	public static int FourOfKind(Pair[] hand)
	{
		int card = hand[2].num;
		int count = 0;
		for(int i = 0; i < 5; i++)
			if(hand[i].num==card)
				count++;
		return count==4?card:-1;
		
	}
	public static boolean stFlush(Pair[] hand)
	{
		
		for(int i = 0; i < 4; i++)
			if(hand[i].suit != hand[i+1].suit || hand[i].num - hand[i+1].num!=1)
				return false;
		return true;
	}
	
	public static int higher(Pair[] black, Pair[] white)
	{
		//hands are sorted
		for(int i = 0; i < 5; i++)
		{
			if(white[i].num==black[i].num)
				continue;
			if(white[i].num>black[i].num)
				return 1;
			return 0;
		}
		return -1;
	}
	
	public static Pair map(String x)
	{
		int suit,num;
		switch(x.charAt(0))
		{
		case 'T':num = 10;break;
		case 'J':num = 11;break;
		case 'Q':num = 12;break;
		case 'K':num = 13;break;
		case 'A':num = 14;break;
		default: num = x.charAt(0) - '0';
		}
		switch(x.charAt(1))
		{
		case 'H':suit = 1;break;
		case 'C':suit = 2;break;
		case 'S':suit = 3;break;
		default:suit = 4;
		}
		return new Pair(suit,num);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Pair[] black = new Pair[5];
			Pair[] white = new Pair[5];
			for(int i = 0; i < 5; i++)
				black[i] = map(st.nextToken());
			for(int i = 0; i < 5; i++)
				white[i] = map(st.nextToken());
			int res = compare(black,white);
			if(res==1)
				out.println("White wins.");
			else
				if(res==0)
					out.println("Black wins.");
				else
					out.println("Tie.");
			
		}
		out.flush();
	}
	
	static class Pair implements Comparable<Pair>
	{
		int suit, num;
		Pair(int x, int y){suit = x; num = y;}
		
		public int compareTo(Pair o) {
			return o.num - this.num;
		}
		
		
	}
}
