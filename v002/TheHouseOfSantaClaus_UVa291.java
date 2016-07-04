package v002;
public class TheHouseOfSantaClaus_UVa291 {

	static boolean[][] used = new boolean[6][6];
	static StringBuilder sb = new StringBuilder();
	
	static void bt(int pos, int line, String s)
	{
		if(line == 8)
			sb.append(s+"\n");
		else
		{
			for(int i = 1; i <= 5; ++i)
				if(pos != i && !used[pos][i])
				{
					used[pos][i] = used[i][pos] = true;
					bt(i, line + 1, s + i);
					used[pos][i] = used[i][pos] = false;
				}
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		used[1][4] = used[4][1] = used[2][4] = used[4][2] = true;
		bt(1, 0, "1");
		System.out.print(sb);
	}
}