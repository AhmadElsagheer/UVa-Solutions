package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DieGame_UVa10409 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int moves = Integer.parseInt(br.readLine());
			if(moves==0)
				break;
			int[] state = new int[]{1,2,3};
			int tmp;
			while(moves-->0)
				switch(br.readLine().charAt(0))
				{
				case 'n':
					tmp = state[0];state[0] = 7 - state[1];state[1] = tmp;break;
				case 's':
					tmp = state[0];state[0] = state[1];state[1] = 7 - tmp;break;
				case 'w':
					tmp = state[0];state[0] = 7 - state[2];state[2] = tmp;break;
					default:
						tmp = state[0];state[0] = state[2];state[2] = 7 - tmp;
				}
			sb.append(state[0]).append("\n");
		}
		System.out.print(sb);
	}
}
