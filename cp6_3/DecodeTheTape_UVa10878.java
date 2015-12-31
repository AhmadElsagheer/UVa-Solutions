package cp6_3;

import java.io.*;

public class DecodeTheTape_UVa10878 {
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		br.readLine();
		String line;
		while(!(line=br.readLine()).equals("___________"))
		{
			int unicode = 0;
			
			for(int i = 0; i < 3; i++)
				unicode += line.charAt(line.length()-i-2)=='o'?(int)Math.pow(2, i):0;
				for(int i = 4; i <= 9; i++)
					unicode += line.charAt(line.length()-i-2)=='o'?(int)Math.pow(2, i-1):0;
			sb.append((char)unicode);
		}
		
		
		System.out.print(sb);
	}
}
