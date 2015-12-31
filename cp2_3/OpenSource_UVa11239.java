package cp2_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class OpenSource_UVa11239 {


	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			String name = sc.nextLine();
			if(name.equals("0"))
				break;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			ArrayList<Project> projects = new ArrayList<Project>(100);
						
			while(!name.equals("1"))
			{
				Project p = new Project(name);
				projects.add(p);
				TreeSet<String> set = new TreeSet<String>();
				while(true)
				{
					name = sc.nextLine();
					if(name.equals("1") || Character.isUpperCase(name.charAt(0)))
						break;
					if(set.contains(name))
						continue;
					set.add(name);
					Integer f = map.get(name);
					if(f == null)
						f = 0;
					map.put(name, f + 1);
					p.students.add(name);
				}
			}
			
			for(Project p : projects)				
				for(int j = 0; j < p.students.size(); ++j)
					if(map.get(p.students.get(j)) > 1)
					{
						int end = p.students.size() - 1;
						p.students.set(j, p.students.get(end));
						p.students.remove(end);
						--j;
					}
			
			Collections.sort(projects);
			for(Project p : projects)
				out.format("%s %d\n", p.name, p.students.size());
			
		}
		out.flush();

	}
	
	static class Project implements Comparable<Project>
	{
		String name;
		ArrayList<String> students = new ArrayList<String>();
		
		Project(String x) { name = x; }
		
		public int compareTo(Project p)
		{
			if(students.size() != p.students.size())
				return p.students.size() - students.size();
			return name.compareTo(p.name);
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
