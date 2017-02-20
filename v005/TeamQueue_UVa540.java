package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TeamQueue_UVa540 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			int teams = Integer.parseInt(br.readLine());
			if(teams==0)
				break;
			sb.append("Scenario #"+k+++"\n");
			TeamQueue x = new TeamQueue(teams);
			while(teams-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int[] id = new int[n];
				for(int i = 0; i < n; i++)
					id[i] = Integer.parseInt(st.nextToken());
				x.addTeam(id);
			}
			while(true)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				String comm = st.nextToken();
				if(comm.equals("STOP"))
					break;
				if(comm.equals("DEQUEUE"))
					sb.append(x.dequeue()+"\n");
				else
					x.enqueue(Integer.parseInt(st.nextToken()));
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}

class TeamQueue
{
	int[] teamOf;
	int nxt;
	Queue<Integer> queue;
	Queue<Integer>[] team_queue;
	
	TeamQueue(int teams)
	{
		queue = new LinkedList<Integer>();
		team_queue = new LinkedList[teams];
		for(int i = 0; i < teams; i++)
			team_queue[i] = new LinkedList<Integer>();
		teamOf = new int[1000000];
	}
	
	void addTeam(int[] id)
	{
		for(int i = 0; i < id.length; i++)
			teamOf[id[i]] = nxt;
		nxt++;
	}
	
	void enqueue(int id)
	{
		int team = teamOf[id];
		if(team_queue[team].size() == 0)
			queue.add(team);
		team_queue[team].add(id);		
	}
	
	int dequeue()
	{
		int ret = team_queue[queue.peek()].remove();
		if(team_queue[queue.peek()].size() == 0)
			queue.remove();
		return ret;
	}
}