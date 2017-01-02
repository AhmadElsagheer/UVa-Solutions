package v106;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class WhatIsTheCard_UVa10646 {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = sc.nextInt();
		for(int k = 1; k <= TC; k++)
		{
			Stack<String> pile = new Stack<String>();
			for(int i = 0; i < 52; i++)
				pile.push(sc.next());
			Stack<String> hand = new Stack<String>();
			for(int i = 0; i < 25; i++)
				hand.push(pile.pop());
			int Y = 0;
			for(int i = 0; i < 3; i++)
			{
				String card = pile.pop();
				int X = getValue(card);
				Y += X;
				X = 10 - X;
				while(X-->0)
					pile.pop();
			}
			while(!hand.isEmpty())
				pile.push(hand.pop());
			int remove = pile.size() - Y;
			while(remove-->0)
				pile.pop();
			out.printf("Case %d: %s\n",k,pile.pop());
		}
		out.flush();
	}
	
	public static int getValue(String card)
	{
		int x = card.charAt(0) - '0';
		if(x>=2&& x<=9)
			return x;
		return 10;
	}
	
	
	
	
	
	
	
}
