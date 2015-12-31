package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MasterMindHints_UVa340 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			sb.append("Game "+k+++":\n");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[n];
			for(int i = 0; i < n; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			while(true)
			{
				int p = 0;
				int[] g = new int[n];
				st = new StringTokenizer(br.readLine());
				for(int i = 0; i < n; i++)
					p += (g[i] = Integer.parseInt(st.nextToken()));
				if(p==0)
					break;
				int[] s = arr.clone();
				int x = 0, y = 0;
				for(int i = 0; i < n; i++)	if(s[i]==g[i]){x++;s[i]=g[i]=-1;}
				for(int i = 0; i < n; i++)
					if(s[i]!=-1) for(int j = 0; j < n; j++) if(s[i]==g[j]){y++;g[j]=-1;break;}
				sb.append("    ("+x+","+y+")\n");
			}
			
		}
		System.out.print(sb);
	}
}
