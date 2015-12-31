package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HangmanJudge_UVa489 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			int k = Integer.parseInt(br.readLine());
			if(k==-1)
				break;
			int[] word = new int[26];
			boolean[] checked = new boolean[26];
			String line = br.readLine();
			int length = line.length();
			for(int i = 0; i < length; i++)
				word[line.charAt(i) - 'a']++;
			line = br.readLine();
			int strokes = 0;
			for(int i = 0; i < line.length(); i++)
			{
				int c = line.charAt(i) - 'a';
				if(!checked[c])
				{
					if(word[c]==0){if(++strokes==7)break;}
					else
					{
						length -= word[c];
						if(length==0)break;
					}
					checked[c] = true;
				}
			}
			out.printf("Round %d\n",k);
			if(length==0)
				out.println("You win.");
			else
				if(strokes==7)
					out.println("You lose.");
				else
					out.println("You chickened out.");
		
		}
		out.flush();
	}
}
