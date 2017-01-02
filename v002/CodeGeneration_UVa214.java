package v002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodeGeneration_UVa214 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		boolean first = true;
		
		while(br.ready())
		{
			if(first)
				first = false;
			else
				sb.append("\n");
			String exp = br.readLine();
			char  tmp = '#';
			int pointer = 0;
			boolean empty = true;
			for(int i = 0; i < exp.length(); i++)
			{
				char c = exp.charAt(i);
				switch(c)
				{
				case '@':
					if(tmp=='#')
						sb.append("N\n");
					else
						sb.append("ST $"+pointer+++"\nL "+tmp+"\nN\n");
										
					tmp = '#';
					break;
				case '+':
					if(tmp=='#')
						sb.append("A $"+--pointer).append("\n");
					else
					{
						sb.append("A "+tmp).append("\n");
						tmp = '#';
					}
					break;
				case '*':
					if(tmp=='#')
						sb.append("M $"+--pointer).append("\n");
					else
					{
						sb.append("M "+tmp).append("\n");
						tmp = '#';
					}
					break;
				case '-':
					if(tmp=='#')
						sb.append("N\nA $"+--pointer).append("\n");
					else
					{
						sb.append("S "+tmp).append("\n");
						tmp = '#';
					}
					break;
				case '/':
					if(tmp=='#')
						sb.append("ST $"+pointer+"\nL $"+(pointer-1)+"\nD $"+pointer--).append("\n");
					else
					{
						sb.append("D "+tmp).append("\n");
						tmp = '#';
					}
					break;
					default:
						if(empty)
						{
							sb.append("L ").append(c).append("\n");
							empty  = false;
						}
						else
						{
							if(tmp!='#')
								sb.append("ST $"+pointer++).append("\n").append("L ").append(tmp).append("\n");							
							
							tmp = c;
						}
						
				}
			}
		
		}
		
		System.out.print(sb);
	}
}
