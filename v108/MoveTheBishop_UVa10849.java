package v108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MoveTheBishop_UVa10849 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			br.readLine();
			int t = Integer.parseInt(br.readLine());
			br.readLine();
			while(t-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r1 = Integer.parseInt(st.nextToken());
				int c1 = Integer.parseInt(st.nextToken());
				int r2 = Integer.parseInt(st.nextToken());
				int c2 = Integer.parseInt(st.nextToken());
				if(r1==r2 && c1==c2)
					sb.append("0\n");
				else
					if((r1+c1)%2!=(r2+c2)%2)
						sb.append("no move\n");
					else
						if(Math.abs(r1-r2)==Math.abs(c1-c2))
							sb.append("1\n");
						else
							sb.append("2\n");
			}
			
		}
		System.out.print(sb);
	}
}
