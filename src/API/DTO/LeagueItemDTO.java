
package API.DTO;

import lombok.Getter;

@Getter
public class LeagueItemDTO {

    private Boolean freshBlood;
    private Boolean hotStreak;
    private Boolean inactive;
    private long leaguePoints;
    private long losses;
    private String rank;
    private String summonerId;
    private String summonerName;
    private Boolean veteran;
    private long wins;
    private MiniSeriesDTO miniSeries;

}
