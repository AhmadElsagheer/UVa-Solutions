package v107;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class FreeSpots_UVa10703 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			if(W==0)
				break;
			int[][] board = new int[W+1][H+1];
			
			while(N-->0)
			{
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				
				if(x1!=x2 && y1!=y2)
				{
					int incRow = (x2-x1)/Math.abs(x2-x1);
					int incCol = (y2-y1)/Math.abs(y2-y1);
					int rowSteps = Math.abs(x2-x1);
					int colSteps = Math.abs(y2-y1);
					for(int i = x1,k = 0;k++ <= rowSteps; i+= incRow)
						for(int j = y1, z = 0; z++ <= colSteps; j += incCol)
							board[i][j] = 1;
					
				}
				else
					if(x1==x2 && y1==y2)
						board[x1][y1] = 1;
					else
						if(x1==x2)
						{
							int incCol = (y2-y1)/Math.abs(y2-y1);
							
							for(int j = y1; j != y2; j += incCol)
									board[x1][j] = 1;
							board[x1][y2] = 1;
						}
						else
						{
							int incRow = (x2-x1)/Math.abs(x2-x1);
							
							
							for(int i = x1; i != x2; i+= incRow)
								board[i][y1] = 1;
							board[x2][y1] = 1;
						}

									
						
			}
			int count = 0;
			for(int i = 1; i <= W; i++)
				for(int j = 1; j <= H; j++)
					if(board[i][j]==0)
						count++;
			if(count==0)
				sb.append("There is no empty spots.\n");
			else
				if(count==1)
					sb.append("There is one empty spot.\n");
				else
					sb.append("There are "+count+" empty spots.\n");
			
			br.readLine();
			
		}
		
		
		
		System.out.print(sb);
		
		
	}
	
}
