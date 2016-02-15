//package cp1_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.BitSet;
//import java.util.StringTokenizer;
//
//public class BridgeHandEvaluator_UVa462 {
//
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		while(br.ready())
//		{
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			Pair[] hand = new Pair[13];
//			BitSet stopped = new BitSet(4);
//			int[] suitCards = new int[4];
//			for(int i = 0; i < 13; i++)
//			{
//				Pair card = mapCard(st.nextToken());
//				hand[i] = card;
//				suitCards[card.suit]++;
//			}
//			int[] score = countPoints(hand,suitCards,stopped);
//			sb.append(bid(score,suitCards,stopped)).append("\n");
//		}
//		
//		System.out.print(sb);
//	}
//	
//	static String open(int suit)
//	{
//		switch(suit)
//		{
//		case -1:return "PASS";
//		case 4: return "BID NO-TRUMP";
//		case 0:return "BID S";
//		case 1:return "BID H";
//		case 2:return "BID D";
//			default: return "BID C";
//		}
//	}
//	
//	static Pair mapCard(String x)
//	{
//		int num, suit;
//		switch(x.charAt(0))
//		{
//		case 'K':num = 13;break;
//		case 'Q':num = 12;break;
//		case 'J':num = 11;break;
//		case 'A':num = 14;break;
//		case 'T':num = 10;break;
//			default: num = x.charAt(0) - '0';
//		}
//		switch(x.charAt(1))
//		{
//		case 'S':suit = 0;break;
//		case 'H':suit = 1;break;
//		case 'D':suit = 2;break;
//			default:suit = 3;
//		}
//		
//		return new Pair(num,suit);
//	}
//	
//	
//	static String bid(int[] score, int[] suitCards, BitSet stopped)
//	{
//		int suit;
//		if(score[0]<14)
//			suit = -1;
//		else
//			if(score[1] >= 16 && stopped.cardinality()==4)
//				suit = 4;
//			else
//			{
//				int max = suitCards[0];int index = 0;
//				for(int i = 1; i < 4; i++)
//					if(suitCards[i]>max)
//					{
//						max = suitCards[i];
//						index = i;
//					}
//				suit = index;
//			}
//		
//		
//		return open(suit);
//	}	
//	
//	static int[] countPoints(Pair[] hand,int[] suitCards, BitSet stopped)
//	{
//		int[] res = new int[2];
//		int count = 0;
//		for(int i = 0; i < 13; i++)
//		{
//			switch(hand[i].num)
//			{
//			case 14:count+=4;stopped.set(hand[i].suit);break;
//			case 13:
//				if(suitCards[hand[i].suit]>1)
//					{count+=3;stopped.set(hand[i].suit);}
//				else count+=2;break;
//			case 12:
//				if(suitCards[hand[i].suit]>2)
//					{count+=2;stopped.set(hand[i].suit);}
//				else count+=1;break;
//			case 11:if(suitCards[hand[i].suit]>3)count+=1;break;
//			}
//		}
//		res[1] = count;
//		for(int i = 0; i < 4; i++)
//			if(suitCards[i]==2)
//				count += 1;
//			else
//				if(suitCards[i]<=1)
//					count += 2;
//		res[0] = count;
//		return res;
//	}
//}
//
//class Pair
//{
//	int suit,num;
//	Pair(int num, int suit) {this.suit = suit; this.num = num;}
//}