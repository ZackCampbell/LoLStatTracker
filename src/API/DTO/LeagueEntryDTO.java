
package API.DTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LeagueEntryDTO {

    private Boolean freshBlood;
    private Boolean hotStreak;
    private Boolean inactive;
    private String leagueId;
    private long leaguePoints;
    private long losses;
    private String queueType;
    private String rank;
    private String summonerId;
    private String summonerName;
    private String tier;
    private Boolean veteran;
    private long wins;
    private MiniSeriesDTO miniSeries;

}
