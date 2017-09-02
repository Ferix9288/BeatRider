package com.example.beatrider;

import java.util.ArrayList;

import com.example.beatrider.Beat.BeatType;


public class SongCollection {
	
	static ArrayList<Song> allSongs = new ArrayList<Song>();
	
	static Song BestDay; //American Authors - Best Day of my Life
	
	static {
		ArrayList<Beat> bestDayBeatPattern = new ArrayList<Beat>();

		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "251"}, 96.372f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "508"}, 96.372f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "175"}, 734.921f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "356"}, 981.633f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "280"}, 1547.619f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "350"}, 1736.281f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "344", "0,159.637", "2"}, 1910.431f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1674", "875"}, 2287.755f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "757"}, 3013.379f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1194", "866", "0,174.15", "2"}, 3376.19f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"954", "663"}, 3869.615f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"714", "554", "0,174.15,348.3", "3"}, 4203.401f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"474", "590"}, 4783.9f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "664", "0,203.174", "2"}, 5132.2f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "194", "0,188.662,406.349", "3"}, 5582.086f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "200"}, 6394.785f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1194", "176"}, 7004.308f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"954", "399"}, 7221.995f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "448"}, 7860.544f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "448", "0,188.662,377.324", "3"}, 8150.794f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"234", "387", "493.424"}, 8818.367f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "827"}, 9311.791f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"360", "844", "0,290.25", "2"}, 9587.528f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"600", "950", "0,188.663", "2"}, 10298.639f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "652"}, 10777.551f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"1080", "868", "493.424"}, 11169.388f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1320", "826", "0,304.762", "2"}, 11662.812f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "648"}, 12344.898f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "495", "0,203.175", "2"}, 12780.272f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "411"}, 13491.383f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "218"}, 13796.145f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "405", "0,275.737", "2"}, 14100.907f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "461"}, 14710.431f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1320", "321", "0,188.662,406.349", "3"}, 15087.755f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "263"}, 15929.478f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "855", "0,290.249", "2"}, 16219.728f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1434", "882", "0,319.274", "2"}, 16742.177f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1194", "612"}, 17467.8f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "685", "0,174.15,377.324", "3"}, 17641.95f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "906"}, 18382.086f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "767", "0,232.2,478.912", "3"}, 18599.773f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "836", "0,232.2", "2"}, 19281.859f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "431", "0,217.687", "2"}, 19673.696f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1434", "485", "0,188.662", "2"}, 20210.658f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1194", "193", "0,261.225", "2"}, 20689.569f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "274", "0,261.224", "2"}, 21255.556f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "235"}, 21865.079f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "393", "0,174.15", "2"}, 22256.916f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"234", "429"}, 22605.215f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "823", "0,275.737", "2"}, 23084.127f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1434", "865", "0,261.225", "2"}, 23650.113f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1194", "574"}, 24259.637f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "798", "0,174.15,333.787", "3"}, 24665.986f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "959"}, 25231.973f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"474", "798", "0,275.737", "2"}, 25594.785f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "771", "0,232.199", "2"}, 26088.209f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "528", "0,348.299", "2"}, 26639.683f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1434", "289", "0,174.149", "2"}, 27162.132f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1194", "517", "0,391.837", "2"}, 27612.018f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "526", "0,174.15,348.299", "3"}, 28163.492f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"714", "530", "0,232.2", "2"}, 28656.916f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"474", "426"}, 29469.615f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "200", "0,217.687", "2"}, 29730.839f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "597", "0,159.637", "2"}, 30398.413f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"360", "861", "0,188.662", "2"}, 30732.2f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "624"}, 31182.086f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "612", "0,275.737", "2"}, 31733.56f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "818"}, 32241.497f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"1320", "864", "609.524"}, 32749.433f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "952"}, 33358.957f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "661"}, 33358.957f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "376"}, 34490.93f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"1674", "335", "580.499"}, 33910.431f));
		bestDayBeatPattern.add( new Beat(BeatType.Hold, new String[] {"1194", "499", "1451.247"}, 34926.304f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "534"}, 34490.93f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"954", "238"}, 36377.551f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"954", "444"}, 36377.551f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"714", "226"}, 36885.488f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"474", "491"}, 37335.374f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "696"}, 37770.748f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"234", "402"}, 37509.524f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "906"}, 38336.735f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "850"}, 38090.023f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "564"}, 38772.109f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "882"}, 38772.109f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "744"}, 39309.07f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "782"}, 39918.594f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "593", "0,304.762", "2"}, 40165.306f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1674", "301"}, 40731.293f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "239"}, 41166.667f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1434", "408"}, 41166.667f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1194", "448"}, 41718.141f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"954", "493", "0,232.2", "2"}, 42168.027f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"714", "189", "0,188.662,435.374", "3"}, 42588.889f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"474", "295"}, 43561.224f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"234", "455", "0,261.224", "2"}, 43807.937f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "829"}, 44330.385f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"360", "830", "0,362.812", "2"}, 44678.685f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"600", "612", "0,174.149", "2"}, 45230.159f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"840", "748", "0,348.299", "2"}, 45607.483f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "596"}, 46579.819f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "593"}, 46942.63f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "904", "0,203.174", "2"}, 47131.293f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"120", "173", "0,275.737", "2"}, 47595.692f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "454"}, 48350.34f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"600", "284"}, 48959.864f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "468"}, 49569.388f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1080", "453"}, 50178.912f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "203"}, 50802.948f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1320", "450"}, 50802.948f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1560", "247"}, 51339.909f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"120", "770"}, 51876.871f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"360", "734"}, 52109.07f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"600", "940", "0,217.688", "2"}, 52297.732f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"840", "932"}, 52762.132f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1080", "944", "0,232.2", "2"}, 53124.943f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1320", "676", "0,232.2", "2"}, 53618.367f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1560", "678", "0,246.712", "2"}, 54082.766f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1674", "214", "0,203.174", "2"}, 54677.778f));

		BestDay = new Song("Best Day", "American Authors", 58, 120, "BestDayCut.mp3", bestDayBeatPattern);
		allSongs.add(BestDay);
		//allSongs.add(BestDay);
		//allSongs.add(BestDay);

		//BestDay = new Song(58, 120, "BestDayCut.mp3", bestDayBeatPattern);
		
	}
	
	
}