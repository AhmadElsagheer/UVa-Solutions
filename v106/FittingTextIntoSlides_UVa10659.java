package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FittingTextIntoSlides_UVa10659 {

	static int X, Y, P;
	static ArrayList<Integer>[] words;
	
	static boolean tryFont(int font)
	{
		int lines = Y / font;
		int p = 0, i = 0;

		while(lines > 0 && p < P)
		{
			int c = X;
			while(i < words[p].size())
			{
				int cur = words[p].get(i) * font + (c==X?0:font);
				if(cur > c)
					break;
				c -= cur;
				i++;
			}
			if(i==words[p].size())
			{
				p++;i = 0;
			}
			lines--;
		}
		if(p == P)
			return true;
		return false;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			P = Integer.parseInt(br.readLine());
			words = new ArrayList[P];
			for(int i = 0; i < P; i++)
			{
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				int total = st.countTokens();
				words[i] = new ArrayList<Integer>(total);
				for(int j = 1; j <= total; j++)
					words[i].add(st.nextToken().length());
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			int font = 28;
			while(font >= 8 && !tryFont(font))
				font--;
			
			if(font >= 8)
				sb.append(font).append("\n");
			else
				sb.append("No solution\n");
						
		}
		System.out.print(sb);
	}
}
