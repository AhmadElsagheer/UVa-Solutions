package v007;	

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LCDisplay_UVa706 {

	
	static int[] segment = new int[]{63,6,91,79,102,109,125,7,127,111};
	static int[] idx = new int[]{0,6,3};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =  new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			String n = st.nextToken();
			if(s==0)
				break;
			char[][] out = new char[(s<<1)+3][(s+3)*n.length() - 1];
			
			for(int i = 0, k = 0; i < n.length(); i++, k+= s + 3)
			{
				int x = n.charAt(i) - '0';
				for(int j = 0; j < 3; j++)
					if((segment[x] & 1<<idx[j]) !=0)
						for(int c = k + 1; c  < k + 1 + s; c++)
							out[j*(s+1)][c] = '-';
				
				if((segment[x] & (1<<1)) !=0)
					for(int j = 1; j < 1 + s; j++)
						out[j][k+s+1] = '|';		
				if((segment[x] & (1<<2)) !=0)
					for(int j = s + 2 ; j < (s<<1) + 2; j++)
						out[j][k+s+1] = '|';
				if((segment[x] & (1<<5)) !=0)
					for(int j = 1; j < 1 + s; j++)
						out[j][k] = '|';
				if((segment[x] & (1<<4)) !=0)
					for(int j = s + 2 ; j < (s<<1) + 2; j++)
						out[j][k] = '|';
				
			}
			for(int i = 0; i < out.length; i++)
			{
				for(int j = 0; j < out[i].length; j++)
					if(out[i][j]=='\u0000')
						sb.append(" ");
					else
						sb.append(out[i][j]);
				sb.append("\n");
			}

			sb.append("\n");
		}
		System.out.print(sb);
	}
}
