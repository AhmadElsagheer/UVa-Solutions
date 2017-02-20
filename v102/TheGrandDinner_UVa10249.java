package v102;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class TheGrandDinner_UVa10249 {

	static Table[] tables;
	static Team[] teams;
	static int available;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			if(M==0)
				break;
			st = new StringTokenizer(br.readLine());
			teams = new Team[M];
			for(int i = 0; i < M; i++)
				teams[i] = new Team(i+1,Integer.parseInt(st.nextToken()));
			
			st = new StringTokenizer(br.readLine());
			tables = new Table[N];
			available = 0;
			for(int i = 0; i < N; i++)
			{
				tables[i] = new Table(i+1,Integer.parseInt(st.nextToken()));
				if(tables[i].Capacity > 0)
					available++;
			}
			Arrays.sort(teams);
			boolean possible = true;
			for(int i = 0; i < M && possible; i++)
				if(!seatTeam(i))
					possible = false;
			if(possible)
			{
				sb.append("1\n");
				Arrays.sort(teams, new ID());
				for(int i = 0; i < M; i++)
				{
					Team cur = teams[i];
					Arrays.sort(cur.tables);
					for(int j = 0; j < cur.members; j++)
						sb.append(cur.tables[j]).append(j==cur.members-1?"\n":" ");
				}
			}
			else
				sb.append("0\n");
			
		}
		
		System.out.print(sb);
	}
	
	static boolean seatTeam(int idx)
	{
		Team x = teams[idx];
		if(x.members > available)
			return false;
		Arrays.sort(tables);
		for(int i = 0, j = 0; j < x.members; i++)
			if(tables[i].Capacity>0)
			{
				x.tables[j++] = tables[i].id;
				tables[i].Capacity--;
				if(tables[i].Capacity==0)
					available--;
				
			}
		return true;
	}
	
	static class Team implements Comparable<Team>
	{
		int id, members;
		int[] tables;
		
		Team(int x, int y)
		{
			id = x; members = y;
			tables = new int[y];
		}
		
		public int compareTo(Team o) {
		
			return o.members - this.members;
		}
	}

	static class Table implements Comparable<Table>
	{
		int id, Capacity;
			
		Table(int x, int y) { Capacity = y; id = x;}

		public int compareTo(Table o) {
			return o.Capacity - this.Capacity;
		}
	}

	static class ID implements Comparator<Team>
	{

		@Override
		public int compare(Team o1, Team o2) {
			// TODO Auto-generated method stub
			return o1.id - o2.id;
		}
		
	}
	
}