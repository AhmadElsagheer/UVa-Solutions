package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class CaesarCypher_UVa554 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		TreeSet<String> dic = new TreeSet<String>();
		while(true)
		{
			String line = br.readLine();
			if(line.equals("#"))
				break;
			dic.add(line);
		}
		
		String txt = br.readLine();
		int max = 0;
		String[] sol = null;
		for(int i = 0; i < 27; i++)
		{
			StringBuilder x = new StringBuilder();
			for(int j = 0; j < txt.length(); j++)
			{
				int c;
				if(txt.charAt(j) == ' ')
					c = 0;
				else
					c = (txt.charAt(j) - 'A' + 1);
				c = (c+i)%27;
				if(c == 0)
					c = ' ';
				else
					c += 'A' - 1;
				x.append((char)c);
			}
			String[] y = x.toString().split(" ");
			int count = 0;
			for(int j = 0; j < y.length; j++)
				if(dic.contains(y[j]))
					count++;
			if(count > max)
			{
				sol = y;
				max = count;
			}
		}
		int line = 60;
		for(int i = 0; i < sol.length; i++)
		{
			if(sol[i].isEmpty())
				continue;
			if(sol[i].length() > line - (line==60?0:1))
			{
				System.out.println();
				System.out.print(sol[i]);
				line = 60 - sol[i].length();
			}
			else
			{
				if(line==60)
					System.out.print(sol[i]);
				else
				{
					System.out.print(" "+sol[i]);line--;
				}
				line -= sol[i].length();
			}
			
		}
		System.out.println();
	}
}
