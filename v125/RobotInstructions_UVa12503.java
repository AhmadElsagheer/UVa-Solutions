package v125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotInstructions_UVa12503{

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt((new StringTokenizer(br.readLine())).nextToken());
		for(int i = 1; i <= t; i++)
		{
			int p = 0;
			int n = Integer.parseInt((new StringTokenizer(br.readLine())).nextToken());
			String[] s = new String[n];
			for(int j = 0; j < n; j++)
				s[j] = br.readLine();
			for(int j = 0; j < n; j++)
				p += instruction(s,j);

			sb.append(p+"\n");
		}

		System.out.print(sb);

	}

	public static int instruction(String[] s, int index)
	{

		StringTokenizer st = new StringTokenizer(s[index]);
		String tmp = st.nextToken();
		if(tmp.equals("LEFT"))
			return -1;
		if(tmp.equals("RIGHT"))
			return 1;
		st.nextToken();
		return instruction(s,Integer.parseInt(st.nextToken())-1);

	}
}
