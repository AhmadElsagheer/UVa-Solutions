package v007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class HeadJudgeHeadache_UVa790 {

	static class Team implements Comparable<Team>
	{
		int id, solved, penalty, subs,fsubs[];
		
		Team(int x) {id = x; fsubs = new int[7];}
		
		void submit(char p, char s, int t)
		{
			subs++;
			if(fsubs[p-'A']==-1)
				return;
			if(s=='N')
				fsubs[p-'A']++;
			else
			{
				solved++;
				penalty += t + fsubs[p-'A'] * 20;
				fsubs[p-'A'] = -1;
			}
		}
		
		public int compareTo(Team x)
		{
			if(solved==0 && x.solved==0)
				return id - x.id;
			if(solved != x.solved)
				return x.solved - solved;
			if(penalty != x.penalty)
				return penalty - x.penalty;
			return id - x.id;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		br.readLine();
		while(TC-->0)
		{

			Team[] teams  = new Team[26];
			for(int i = 0; i < 26; i++)
				teams[i] = new Team(i);
			ArrayList<Sub> submissions = new ArrayList<Sub>();
			int max = 0;

			while(br.ready())
			{
				String line = br.readLine();
				if(line.equals(""))
					break;
				StringTokenizer st = new StringTokenizer(line);
				if(st.countTokens()==0)
					break;
				int id = Integer.parseInt(st.nextToken());	max = Math.max(id, max);
				char p = st.nextToken().charAt(0);
				String time = st.nextToken();
				char s = st.nextToken().charAt(0);
				st = new StringTokenizer(time,":");
				submissions.add(new Sub(id,Integer.parseInt(st.nextToken())*60 + Integer.parseInt(st.nextToken()),p,s));
			}
			
			Collections.sort(submissions);
			for(int i = 0; i < submissions.size(); i++)
			{
				Sub cur = submissions.get(i);
				teams[cur.id].submit(cur.p, cur.s, cur.time);
			}
			Arrays.sort(teams);
			out.printf("RANK TEAM PRO/SOLVED TIME\n");
			int next = 1;int last = 0;
			for(int i = 0; i < 26; i++)
				if(teams[i].id > max || teams[i].id == 0)
					continue;
				else
					if(teams[i].solved==0)
						out.printf("%4d %4d\n",next,teams[i].id);
					else
					{
						if(i!=0 && teams[i-1].solved == teams[i].solved && teams[i-1].penalty == teams[i].penalty)
							out.printf("%4d %4d    %d       %4d\n",last,teams[i].id,teams[i].solved, teams[i].penalty);
						else
							out.printf("%4d %4d    %d       %4d\n",last = next,teams[i].id,teams[i].solved, teams[i].penalty);
						next++;
					}
					
			if(TC!=0)
				out.println();
		}
		
		out.flush();
	}
	
	
	
	
}

class Sub	implements Comparable<Sub>
{
	int id, time;
	char p,s;
	Sub(int x, int y, char a, char b)
	{
		id = x;
		time = y;
		p = a;
		s = b;
	}
	
	public int compareTo(Sub x)
	{
		return time - x.time;
	}
}