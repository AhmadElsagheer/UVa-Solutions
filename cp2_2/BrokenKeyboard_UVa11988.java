package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;

public class BrokenKeyboard_UVa11988 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		while(br.ready())
		{
			LinkedList<String> words = new LinkedList<String>();
			String line = br.readLine();
			int i = 0, j = 0;char last = ']';
			while(j < line.length())
			{
				if(line.charAt(j)=='[')
				{
					String word = line.substring(i,j);
					if(last==']')
						words.addLast(word);
					else
						words.addFirst(word);
					last = '[';
					i = j + 1;
				}
				else
					if(line.charAt(j)==']')
					{
						String word = line.substring(i,j);
						if(last==']')
							words.addLast(word);
						else
							words.addFirst(word);
						last = ']';
						i = j + 1;
					}
				j++;
			}
			String word = line.substring(i,j);
			if(last==']')
				words.addLast(word);
			else
				words.addFirst(word);
			StringBuilder sb = new StringBuilder();
			while(!words.isEmpty())
				sb.append(words.remove());
			out.println(sb);
		}
		out.flush();
	}
}
