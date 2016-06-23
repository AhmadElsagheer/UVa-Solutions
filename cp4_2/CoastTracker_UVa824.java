package cp4_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoastTracker_UVa824 {

	static int[][] island;
	static int x,y,d;
	static int[] dy = new int[]{1,1,0,-1,-1,-1,0,1};
	static int[] dx = new int[]{0,-1,-1,-1,0,1,1,1};
	
	public static int findNext()
	{
		int start = (d + 6)%8;
		for(int i = 0; i < 8; i++)
		{
			if(island[y+dy[start]][x+dx[start]]==1)
				return start;
			else
				start = (start+1)%8;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			if(x==-1)
				break;
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			island = new int[y+2][x+2];
			for(int i = 0; i < 8; i++)
			{
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				island[b][a] = Integer.parseInt(st.nextToken());
			}
			out.println(findNext());
		}
		out.flush();
		out.close();
	}
	
}
