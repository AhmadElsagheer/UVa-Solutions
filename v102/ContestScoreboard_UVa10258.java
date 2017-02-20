package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ContestScoreboard_UVa10258 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		br.readLine();
		while(TC-->0)
		{
			Team[] teams = new Team[101];
			for(int i = 0; i <= 100; i++)
				teams[i] = new Team(i);
			String line;
			while(br.ready() && !(line=br.readLine()).equals(""))
			{
				st = new StringTokenizer(line);
				int i = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				char c = st.nextToken().charAt(0);
				switch(c)
				{
				
				case 'I':teams[i].tried(p);break;
				case 'C':teams[i].solved(p,t);break;
				default: teams[i].submit();
				}
			}
			Arrays.sort(teams);
			for(int i = 0; i <= 100; i++)
			{
				Team cur = teams[i];
				if(cur.submission!=0)
					sb.append(cur.id+" "+cur.solved+" "+cur.penalty+"\n");
			}
			
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}

class Team implements Comparable<Team>
{
	int id,solved,penalty,submission;
	int[] penalties = new int[10];
	
	Team(int x) {id = x;}
	
	void solved(int p, int t)
	{
		if(penalties[p]!=-1)
		{
			solved++;
			penalty += penalties[p] + t;
			penalties[p] = -1;
		}
		submission++;
	}
	
	void tried(int p)
	{
		if(penalties[p]!=-1)
			penalties[p] += 20;
		submission++;
	}
	
	void submit()
	{
		submission++;
	}

	@Override
	public int compareTo(Team o) {
		if(this.solved != o.solved)
			return o.solved - this.solved;
		if(this.penalty != o.penalty)
			return this.penalty - o.penalty;
		return this.id - o.id;
	}
	
	
}
