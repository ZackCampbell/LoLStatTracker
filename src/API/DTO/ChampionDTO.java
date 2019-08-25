
package API.DTO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "id",
    "key",
    "name",
    "title",
    "blurb",
    "info",
    "image",
    "tags",
    "partype",
    "stats"
})
@Getter
@ToString
public class ChampionDTO {

    private static HashMap<Long, ChampionDTO> championDataById;
    private static HashMap<String, ChampionDTO> championDataByName;

    @JsonProperty("version")
    private String version;
    @JsonProperty("id")
    private String id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("name")
    private String name;
    @JsonProperty("title")
    private String title;
    @JsonProperty("blurb")
    private String blurb;
    @JsonProperty("info")
    private ChampionInfoDTO info;
    @JsonProperty("image")
    private ImageDTO image;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("partype")
    private String partype;
    @JsonProperty("stats")
    private ChampionStatsDTO stats;

    public static void init() {
        championDataById = new HashMap<>();
        championDataByName = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(new File("././resources/champion.json"));
            JsonNode data = node.get("data");
            Iterator<JsonNode> it = data.elements();

            while (it.hasNext()) {
                JsonNode champ = it.next();
                ChampionDTO champDto = mapper.readValue(champ.toString(), ChampionDTO.class);

                championDataById.put(Long.parseLong(champDto.key), champDto);
                championDataByName.put(champDto.name.toLowerCase(), champDto);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static ChampionDTO getById(long id) {
        return championDataById.get(id);
    }

    public static ChampionDTO getByName(String name) {
        return championDataByName.get(name.toLowerCase());
    }
}
