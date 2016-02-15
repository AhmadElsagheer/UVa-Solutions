package cp3_2;


import java.io.*;
import java.util.StringTokenizer;


public class  CD_UVa624 {
		
	static int N;
	static int nTracks;
	static int[] tracks;
	static int kTracks;
	
	static int[] selectedTracks;
	static int maxTime;
	static int[] recommendedTracks;
	
	public static int calculateTime()
	{
		int time = 0;
		for(int i = 1; i <= kTracks; i++)
		{
			time += selectedTracks[i];
			if(time>N)
				return -1;
		}
		return time;
	}
	
	public static void findOptimum(int currentTrack, int startTrack)
	{
		for(int i = startTrack; i <= nTracks; i++)
		{
			selectedTracks[currentTrack] = tracks[i];
			if(currentTrack==kTracks)
			{
				int time = calculateTime();
				if(time==-1)
					continue;
				
				if(time > maxTime || time==maxTime && selectedTracks.length > recommendedTracks.length)
				{
					maxTime = time;
					recommendedTracks = selectedTracks.clone();
				}
			}
			else
				findOptimum(currentTrack+1, i+1);
			
		}
	}
	
	public static void findOptimum()
	{
		kTracks++;
		selectedTracks = new int[kTracks+1];
		findOptimum(1,1);
		if(kTracks == nTracks)
			return;
		findOptimum();
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		
		while(true)
		{
			String x = br.readLine();
			if(x==null || x.equals(""))
				break;
			StringTokenizer st = new StringTokenizer(x);
			N = Integer.parseInt(st.nextToken());
			nTracks = Integer.parseInt(st.nextToken());
			tracks = new int[nTracks+1];
			for(int i = 1; i <= nTracks; i++)
				tracks[i] = Integer.parseInt(st.nextToken());
			
			if(nTracks==0 || N == 0)
			{
				sb.append("sum:0\n");
				continue;
			}
			
			maxTime = 0;
			kTracks = 0;
			findOptimum();
			
			if(maxTime==0)
			{
				sb.append("sum:0\n");
				continue;
			}
			
			for(int i = 1; i < recommendedTracks.length; i++)
				sb.append(recommendedTracks[i]+" ");
			sb.append("sum:"+maxTime+"\n");
			
		}
		
		System.out.print(sb);



		
	}
}
