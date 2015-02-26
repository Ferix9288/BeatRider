package com.example.beatrider;

import java.util.ArrayList;

import com.example.beatrider.Beat.BeatType;


public class SongCollection {
	
	static Song BestDay; //American Authors - Best Day of my Life
	
	static {
		ArrayList<Beat> bestDayBeatPattern = new ArrayList<Beat>();

		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "339"}, 96.372f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "313"}, 96.372f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "517"}, 734.921f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "257"}, 981.633f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "188"}, 1547.619f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "495"}, 1736.281f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "337", "0,159.637", "2"}, 1910.431f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "592"}, 2287.755f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "576"}, 3013.379f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"600", "888", "0,174.15", "2"}, 3376.19f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "888"}, 3869.615f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1080", "958", "0,174.15,348.3", "3"}, 4203.401f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "858"}, 4783.9f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "944", "0,203.174", "2"}, 5132.2f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "499", "0,188.662,406.349", "3"}, 5582.086f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "280"}, 6394.785f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1194", "239"}, 7004.308f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"954", "425"}, 7221.995f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "483"}, 7860.544f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "414", "0,188.662,377.324", "3"}, 8150.794f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"234", "504"}, 8818.367f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "917"}, 9311.791f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"360", "868", "0,290.25", "2"}, 9587.528f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"600", "843", "0,188.663", "2"}, 10298.639f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"840", "719", "391.837"}, 10777.551f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "664"}, 11169.388f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1320", "800", "0,304.762", "2"}, 11662.812f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "803"}, 12344.898f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "248", "0,203.175", "2"}, 12780.272f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "311"}, 13491.383f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "454"}, 13796.145f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "202", "0,275.737", "2"}, 14100.907f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "405"}, 14710.431f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1320", "269", "0,188.662,406.349", "3"}, 15087.755f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "400"}, 15929.478f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "559", "0,290.249", "2"}, 16219.728f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"360", "870", "0,319.274", "2"}, 16742.177f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "714"}, 17467.8f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "952", "0,174.15,377.324", "3"}, 17641.95f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "659"}, 18382.086f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1320", "809", "0,232.2,478.912", "3"}, 18599.773f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "751", "0,232.2", "2"}, 19281.859f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "227", "0,217.687", "2"}, 19673.696f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1434", "281", "0,188.662", "2"}, 20210.658f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1194", "539", "0,261.225", "2"}, 20689.569f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "452", "0,261.224", "2"}, 21255.556f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "310"}, 21865.079f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "218", "0,174.15", "2"}, 22256.916f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"234", "348"}, 22605.215f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "680", "0,275.737", "2"}, 23084.127f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1434", "879", "0,261.225", "2"}, 23650.113f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1194", "694"}, 24259.637f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "850", "0,174.15,333.787", "3"}, 24665.986f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "907"}, 25231.973f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "574", "0,275.737", "2"}, 25594.785f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "774", "0,232.199", "2"}, 26088.209f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "186", "0,348.299", "2"}, 26639.683f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"360", "258", "0,174.149", "2"}, 27162.132f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"600", "419", "0,391.837", "2"}, 27612.018f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "236", "0,174.15,348.299", "3"}, 28163.492f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1080", "506", "0,232.2", "2"}, 28656.916f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "447"}, 29469.615f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "310", "0,217.687", "2"}, 29730.839f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "547", "0,159.637", "2"}, 30398.413f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"360", "926", "0,188.662", "2"}, 30732.2f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "649"}, 31182.086f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "805", "0,275.737", "2"}, 31733.56f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"1080", "813", "507.936"}, 32241.497f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"1320", "956", "609.524"}, 32749.433f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"1560", "598", "551.474"}, 33358.957f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"120", "290", "580.499"}, 33910.431f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"360", "467", "435.374"}, 34490.93f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "409"}, 34926.304f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "434"}, 36377.551f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "215"}, 36885.488f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "376"}, 37335.374f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "955"}, 37770.748f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "219"}, 37509.524f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "804"}, 38336.735f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "774"}, 38090.023f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "565"}, 38772.109f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "572"}, 39309.07f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "723"}, 39918.594f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "858", "0,304.762", "2"}, 40165.306f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "536"}, 40731.293f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "225"}, 41166.667f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "463"}, 41718.141f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "301", "0,232.2", "2"}, 42168.027f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1080", "483", "0,188.662,435.374", "3"}, 42588.889f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "215"}, 43561.224f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "276", "0,261.224", "2"}, 43807.937f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1674", "875"}, 44330.385f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1434", "886", "0,362.812", "2"}, 44678.685f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1194", "646", "0,174.149", "2"}, 45230.159f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "900", "0,348.299", "2"}, 45607.483f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "682"}, 46579.819f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"474", "657"}, 46942.63f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "753", "0,203.174", "2"}, 47131.293f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "204", "0,275.737", "2"}, 47595.692f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "344"}, 48350.34f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "308"}, 48959.864f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "450"}, 49569.388f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "175"}, 50178.912f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "261"}, 50802.948f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "418"}, 51339.909f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1674", "886"}, 51876.871f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "756"}, 52109.07f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1194", "849", "0,217.688", "2"}, 52297.732f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"954", "874"}, 52762.132f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"714", "851", "0,232.2", "2"}, 53124.943f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "838", "0,232.2", "2"}, 53618.367f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "623", "0,246.712", "2"}, 54082.766f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "277", "0,203.174", "2"}, 54677.778f));

		BestDay = new Song(58, 120, "BestDayCut.mp3", bestDayBeatPattern);
		
	}
	
	
}