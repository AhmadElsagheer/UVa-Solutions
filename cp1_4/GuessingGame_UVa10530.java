package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

public class GuessingGame_UVa10530 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		boolean play  = true;
		while(play)
		{
			BitSet numbers = new BitSet(11);
			numbers.set(1,11);
			while(true)
			{
				int guess = Integer.parseInt(br.readLine());
				if(guess==0)
				{
					play = false;
					break;
				}
				String response = br.readLine();
				if(response.equals("right on"))
				{
					if(!numbers.get(guess))
						sb.append("Stan is dishonest\n");
					else
						sb.append("Stan may be honest\n");
					break;
				}
				else
					if(response.equals("too high"))
						numbers.clear(guess,11);
					else
						numbers.clear(1,guess+1);
			}
		}
		System.out.print(sb);
	}
}
