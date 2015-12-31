package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Birthdates_UVa12541 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Person[] people = new Person[N];
		for(int i = 0; i < N; i++)
		{
			StringTokenizer st=  new StringTokenizer(br.readLine());
			people[i] = new Person(st.nextToken(),getInt(st.nextToken()),getInt(st.nextToken()),getInt(st.nextToken()));
		}
		Arrays.sort(people);
		System.out.println(people[N-1].name);
		System.out.println(people[0].name);
	}
	
	static int getInt(String x)
	{
		return Integer.parseInt(x);
	}
}

class Person implements Comparable<Person>
{
	String name;
	int dd,mm,yy;
	Person(String s, int x, int y, int z)
	{
		name = s; dd = x; mm = y; yy = z;
	}
	@Override
	public int compareTo(Person o) {
		if(yy!=o.yy)
			return yy - o.yy;
		if(mm!=o.mm)
			return mm - o.mm;
		return dd - o.dd;
					
	}
}
