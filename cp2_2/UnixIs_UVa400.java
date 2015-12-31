package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UnixIs_UVa400 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			int N = Integer.parseInt(br.readLine());
			String[] words = new String[N];
			int max = 0;
			for(int i = 0; i < N; i++)
			{
				String line = br.readLine();
				max = Math.max(max, line.length());
				words[i] = line;
			}
			Arrays.sort(words);
			int C = 1 + (60-max)/(max+2);
			int R = (int) Math.ceil(N*1.0/C);
			String[][] out = new String[R][C];
			for(int i = 0, k = 0; i < C; i++)
				for(int j = 0; j < R && k < N; j++, k++)
					out[j][i] = words[k];
			for(int i = 0; i < 60; i++)
				sb.append("-");
			sb.append("\n");
			for(int i = 0; i < R; i++)
			{
				for(int j = 0; j < C; j++)
				{
					String cur = out[i][j];
					if(cur==null)
						break;
					sb.append(cur);
					int spaces = (j == C - 1?max:(max+2)) - cur.length();
					while(spaces-->0)
						sb.append(" ");	
				}
				sb.append("\n");
			}
			
		}
		System.out.print(sb);
	}
}
