package v117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumpingMario_UVa11764{

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			int n = Integer.parseInt(br.readLine());int ups = 0; int downs = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int current = Integer.parseInt(st.nextToken());
			for(int j = 0; j < n-1; j++)
			{
				int next = Integer.parseInt(st.nextToken());
				if(next>current)
					ups++;
				else
					if(next<current)
						downs++;
				current = next;
			}
			sb.append("Case "+(i+1)+": "+ups+" "+downs+"\n");
		}
		System.out.print(sb);
		
	}
}
