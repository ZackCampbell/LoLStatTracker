
package API.DTO;

import java.util.List;
import lombok.Getter;

@Getter
public class LeagueListDTO {

    private List<LeagueItemDTO> entries;
    private String leagueId;
    private String name;
    private String queue;
    private String tier;

}
