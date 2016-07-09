package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class FootballAkaSoccer_UVa10194 {

	public static void main(String[] args) throws IOException 
	{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "ISO-8859-1"));
		
		int tc = Integer.parseInt(sc.readLine());
		while(tc-->0)
		{
			String tournament = sc.readLine();
			int N = Integer.parseInt(sc.readLine());
			Team[] teams = new Team[N];
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < N; ++i)
			{
				String name = sc.readLine();
				teams[i] = new Team(name);
				map.put(name, i);
			}
			
			int M = Integer.parseInt(sc.readLine());
			while(M-->0)
			{
				String[] in = sc.readLine().split("@|#");
				int g1 = Integer.parseInt(in[1]);
				int g2 = Integer.parseInt(in[2]);
				Team t1 = teams[map.get(in[0])], t2 = teams[map.get(in[3])];
				t1.addMatch(g1, g2);
				t2.addMatch(g2, g1);
			}
			Arrays.sort(teams);
			out.println(tournament);
			for(int i = 0; i < N; ++i)
				out.printf("%d) %s\n", i + 1, teams[i]);
			if(tc != 0)
				out.println();
		}

		out.flush();
		out.close();
	}

	static class Team implements Comparable<Team>
	{
		String name;
		int points, games, wins, ties, losses, gd, gs, ga;

		Team(String s) { name = s; }

		public int compareTo(Team t)
		{
			if(points != t.points)
				return t.points - points;
			if(wins != t.wins)
				return t.wins - wins;
			if(gd != t.gd)
				return t.gd - gd;
			if(gs != t.gs)
				return t.gs - gs;
			if(games != t.games)
				return games - t.games;
			return name.toLowerCase().compareTo(t.name.toLowerCase());
		}
		
		void addMatch(int g1, int g2)
		{
			gs += g1; ga += g2; gd += g1 - g2;
			++games;
			if(g1 > g2)
			{
				++wins;
				points += 3;
			}
			else if(g1 == g2)
			{
				++ties;
				++points;
			}
			else
				++losses;	
		}
		
		public String toString()
		{
			return String.format("%s %dp, %dg (%d-%d-%d), %dgd (%d-%d)", name, points, games, wins, ties, losses, gd, gs, ga);
		}
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}