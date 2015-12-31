package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MattyBlocks_UVa434 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0)
		{
			int K = Integer.parseInt(br.readLine());
			int[][] board = new int[K][K];
			int[][] right = new int[K][2];
			int[][] front = new int[K][2];
			StringTokenizer st  = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++)
				front[i][0] = Integer.parseInt(st.nextToken());
			st  = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++)
				right[i][0] = Integer.parseInt(st.nextToken());
			
			//find minimum for front
			for(int i = 0; i < K; i++)
			{
				
				int bestSoFar = -1;
				for(int j = 0; j < K; j++)
				{
					if(right[j][0]==front[i][0] && right[j][1]==0)
					{
						bestSoFar = j;right[j][1]=1;break;
					}
					else
						if(right[j][0]>=front[i][0])
							bestSoFar = j;
				}
				board[i][bestSoFar] = front[i][0];
				front[i][1] = 1;
			}
			//find minimum for right
			for(int i = 0; i < K; i++)
			{
				if(right[i][1]==1)
					continue;
				int bestSoFar = -1;
				for(int j = 0; j < K && bestSoFar == -1; j++)
				{
					if(front[j][0]>=right[i][0])
							bestSoFar = j;
				}
				board[bestSoFar][i] = right[i][0];
				right[i][1] = 1;
			}
			//count minimum
			int min = 0;
			for(int i = 0; i < K; i++)
				for(int j = 0; j <K; j++)
					min +=  board[i][j];
			
			//count maximum
			int max = min;
			for(int i = 0; i < K; i++)
				for(int j = 0; j <K; j++)
					max += Math.min(front[i][0], right[j][0]) - board[i][j];
			
			
			
			sb.append("Matty needs at least "+min+" blocks, and can add at most "+(max-min)+" extra blocks.\n");
		}
		
		System.out.print(sb);
	}
}
