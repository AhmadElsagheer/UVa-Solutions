package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CallForwarding_UVa380 {

	static int[][] timeline;
	static boolean[] called;
	static int N;
	static TreeMap<Integer,Integer> map;
	
	static void request(int from, int start, int dur, int to)
	{
		int source;
		if(map.containsKey(from))
			source = map.get(from);
		else
		{
			map.put(from, N);
			source = N++;
		}
		for(int i = start; i <= start+dur; i++)
			timeline[i][source] = to;
	}
	
	static int ring(int time, int ext)
	{
		if(!map.containsKey(ext))
			return ext;
		int source = map.get(ext);
		if(called[source])
			return 9999;
		if(timeline[time][source]==0)
			return ext;
		called[source] = true;
		return ring(time,timeline[time][source]);
	}
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = getInt(br.readLine());
		out.print("CALL FORWARDING OUTPUT\n");
		for(int k = 1; k <= TC; k++)
		{
			out.printf("SYSTEM %d\n",k);
			timeline = new int[9000][100];
			N = 0;
			map = new TreeMap<Integer,Integer>();
			while(true)
			{
				String request = br.readLine();
				if(request.equals("0000"))
					break;
				StringTokenizer st = new StringTokenizer(request);
				request(getInt(st.nextToken()),getInt(st.nextToken()),getInt(st.nextToken()),getInt(st.nextToken()));
			}
			while(true)
			{
				String call = br.readLine();
				if(call.equals("9000"))
					break;
				StringTokenizer st = new StringTokenizer(call);
				int time = getInt(st.nextToken());
				int ext = getInt(st.nextToken());
				called = new boolean[N];
				int target = ring(time,ext);
				out.printf("AT %04d CALL TO %04d RINGS %04d\n",time,ext,target);
			}
		}
		out.print("END OF OUTPUT\n");
		out.flush();
	}
	
	static int getInt(String x)
	{
		return Integer.parseInt(x);
	}
}


