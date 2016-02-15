package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MirrorMirror_UVa466 {

	public static int[][] reverseV(int[][] pattern)
	{
		int[][] reversed = new int[pattern.length][pattern.length];
		
		for(int j = 0; j < pattern.length; j++)		// columns
			for(int i = 0; i < pattern.length; i++)
				reversed[i][j] = pattern[pattern.length - i - 1][j];
		
		return reversed;
			
	}
	
	public static int[][] rotate90(int[][] pattern)
	{
		int[][] rotated = new int[pattern.length][pattern.length];
		
		for(int i = 0; i < pattern.length; i++)
			for(int j = 0; j < pattern.length; j++)
				rotated[i][j] = pattern[j][pattern.length - i - 1];
		
		return rotated;
	}
	
	public static boolean equal(int[][] original, int[][] transformed)
	{
		for(int i = 0; i < original.length; i++)
			for(int j = 0; j < original.length; j++)
				if(original[i][j]!=transformed[i][j])
					return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;

		while(br.ready())
		{
			int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			
			int[][] original = new int[N][N];
			int[][] transformed = new int[N][N];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				String row = st.nextToken();
				for(int j = 0; j < N; j++)
					original[i][j] = row.charAt(j);
				row = st.nextToken();
				for(int j = 0; j < N; j++)
					transformed[i][j] = row.charAt(j);
			}
			
			
			int[][] trial;
			String res = "";
			if(equal(original,transformed))
				res = " was preserved.";
			else
			{
				trial = rotate90(transformed);
				if(equal(trial,original))
					res = " was rotated 90 degrees.";
				else
				{
					trial = rotate90(trial);
					if(equal(trial,original))
						res = " was rotated 180 degrees.";
					else
					{
						trial = rotate90(trial);
						if(equal(trial,original))
							res = " was rotated 270 degrees.";
						else
						{
							trial = reverseV(transformed);
							if(equal(trial,original))
								res = " was reflected vertically.";
							else
							{
								trial = rotate90(transformed);
								trial = reverseV(trial);
								
								if(equal(trial,original))
									res = " was reflected vertically and rotated 90 degrees.";
								else
								{
									trial = rotate90(transformed);
									trial = rotate90(trial);
									trial = reverseV(trial);
									if(equal(trial,original))
										res = " was reflected vertically and rotated 180 degrees.";
									else
									{
										trial = rotate90(transformed);
										trial = rotate90(trial);
										trial = rotate90(trial);
										trial = reverseV(trial);
										if(equal(trial,original))
											res = " was reflected vertically and rotated 270 degrees.";
										else
											res = " was improperly transformed.";
									}
								}
							}
						}
					}
				}
			}
			
			
			
			
			sb.append("Pattern "+k+++res+"\n");
		}
		
		
		System.out.print(sb);
		
		
	}
}
