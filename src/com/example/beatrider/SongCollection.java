package com.example.beatrider;

import java.util.ArrayList;

import com.example.beatrider.Beat.BeatType;


public class SongCollection {
	
	static Song BestDay; //American Authors - Best Day of my Life
	
	static {
		ArrayList<Beat> bestDayBeatPattern = new ArrayList<Beat>();

		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"100", "374"}, 0f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"300", "297"}, 938.095f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"500", "336"}, 1126.757f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"700", "521"}, 1808.844f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"900", "273"}, 1982.993f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1100", "306"}, 2316.78f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1300", "194"}, 2984.354f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1500", "155"}, 3550.34f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1700", "422"}, 3768.027f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"100", "900"}, 4188.889f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"300", "673"}, 4377.551f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"500", "721"}, 4798.413f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"700", "854"}, 5219.274f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"900", "789"}, 5465.986f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1100", "977"}, 5770.748f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1300", "757"}, 6351.247f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1500", "735"}, 7207.483f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1700", "914"}, 8107.256f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1694", "183"}, 8310.431f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1494", "471"}, 8731.293f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1294", "480"}, 8992.517f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1094", "530"}, 9602.041f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"894", "382"}, 9950.34f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"694", "433"}, 10269.615f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"494", "502"}, 10487.302f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"294", "272"}, 11169.388f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"94", "352"}, 11677.324f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"100", "718"}, 11880.499f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"300", "970"}, 12373.923f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"500", "904", "0,203.174", "2"}, 12794.785f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"700", "939"}, 13215.646f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"900", "738"}, 13825.17f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1100", "581", "0,261.224", "2"}, 14129.932f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1300", "849"}, 14739.456f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1500", "818"}, 15232.88f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1700", "969", "0,391.837", "2"}, 15552.154f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1694", "411"}, 16248.753f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1494", "466"}, 16829.252f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1294", "171", "0,275.737", "2"}, 17163.039f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1094", "166", "0,319.274", "2"}, 17641.95f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"894", "479"}, 18657.823f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"694", "340"}, 18962.585f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"494", "363"}, 19238.322f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"294", "494", "0,174.15", "2"}, 19789.796f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"94", "404"}, 20123.583f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"100", "768"}, 20689.569f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"300", "756"}, 21328.118f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"500", "946", "0,362.812", "2"}, 21618.367f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"700", "907", "0,174.15", "2"}, 22256.916f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"900", "816"}, 23113.152f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1100", "713"}, 23388.889f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1300", "699", "0,232.2", "2"}, 23621.088f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1500", "937"}, 24375.737f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1700", "705", "0,188.663", "2"}, 24636.961f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"100", "237"}, 25159.41f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"300", "183"}, 25812.472f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"500", "289", "0,261.224", "2"}, 26088.209f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"700", "519"}, 26654.195f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"900", "214", "0,203.174", "2"}, 27234.694f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1100", "318", "0,333.787", "2"}, 27641.043f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1300", "297", "0,174.15,333.787", "3"}, 28134.467f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1500", "401"}, 28860.091f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1700", "360"}, 29484.127f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1694", "790", "0,203.175", "2"}, 29730.839f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1494", "599"}, 30398.413f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"1294", "787", "0,188.662,348.3", "3"}, 30572.562f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1094", "868"}, 31211.111f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"894", "741"}, 32139.909f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"694", "805"}, 32328.571f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"494", "815", "0,275.737", "2"}, 32517.234f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"294", "977"}, 33881.406f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"94", "907"}, 34418.367f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1694", "157"}, 36914.512f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1494", "379"}, 37364.399f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1294", "444"}, 37770.748f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"894", "301"}, 38307.71f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1094", "406"}, 38031.973f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"694", "492"}, 38714.059f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"494", "491"}, 39947.619f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"294", "281"}, 40208.844f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"94", "334"}, 40818.367f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1694", "942"}, 41152.154f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1494", "756"}, 41674.603f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1294", "760"}, 42400.227f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1094", "561"}, 42646.939f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"894", "938"}, 42893.651f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"694", "862"}, 43154.875f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"494", "803"}, 43778.912f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"294", "761"}, 44098.186f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"94", "647"}, 44388.435f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1494", "329"}, 44954.422f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1694", "239"}, 44736.735f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1294", "270"}, 45360.771f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1094", "301"}, 45549.433f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"894", "540"}, 45926.757f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"694", "317"}, 46478.231f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"494", "313"}, 47102.268f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"294", "263"}, 47305.442f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"100", "665", "0,261.224", "2"}, 47726.304f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"94", "173"}, 47508.617f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"300", "655"}, 48901.814f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"500", "720"}, 49496.825f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"700", "724"}, 50062.812f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"900", "807"}, 50338.549f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1100", "750"}, 50744.898f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1300", "946"}, 51702.721f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1500", "664"}, 51963.946f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1700", "921"}, 52239.683f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1694", "398"}, 52486.395f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1494", "142"}, 53139.456f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1294", "470"}, 53444.218f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"1094", "202"}, 53705.442f));
		bestDayBeatPattern.add( new Beat(BeatType.SingleTap, new String[] {"894", "221"}, 54329.478f));
		bestDayBeatPattern.add( new Beat(BeatType.MultipleTap, new String[] {"694", "489", "0,246.712", "2"}, 54576.19f));

		BestDay = new Song(58, 120, "BestDayCut.mp3", bestDayBeatPattern);
		
	}
	
	
}