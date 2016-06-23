package cp2_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RotatedSquare_UVa10855 {

	
	public static char[][] rotate90(char[][] square)
	{
		char[][] rotated = new char[square.length][square.length];
		
		for(int i = 0; i < square.length; i++)
			for(int j = 0; j < square.length; j++)
				rotated[i][j] = square[square.length - j - 1][i];
		return rotated;
	}
	
	public static boolean checkEqual(char[][] big, char[][] small, int x, int y)
	{
		for(int i = 0; i < small.length; i++)
			for(int j = 0; j < small.length; j++)
				if(big[i+x][j+y]!=small[i][j])
					return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			if(X==0 && x ==0)
				break;
			
			char[][] big  = new char[X][X];
			char[][] small = new char[x][x];
			
			for(int i = 0; i < X; i++)
			{
				String row = br.readLine();
				for(int j = 0; j < X; j++)
					big[i][j] = row.charAt(j);
			}
			for(int i = 0; i < x; i++)
			{
				String row = br.readLine();
				for(int j = 0; j < x; j++)
					small[i][j] = row.charAt(j);
			}
			int[] reps = new int[4];int k = 0;
			do
			{
				for(int i = 0; i <= X - x; i++)
					for(int j = 0; j <= X - x; j++)
						if(checkEqual(big,small,i,j))
							reps[k]++;
				small = rotate90(small);
			}while(++k < 4);
			
			sb.append(reps[0]+" "+reps[1]+" "+reps[2]+" "+reps[3]+"\n");
		}
		
		System.out.print(sb);
	}
}
