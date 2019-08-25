
package API.DTO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RuneTreeDTO extends RuneDTO {

    private static HashMap<Long, RuneDTO> runeData;

//    @JsonProperty("icon")
//    private String icon;
//    @JsonProperty("id")
//    private long id;
//    @JsonProperty("key")
//    private String key;
//    @JsonProperty("name")
//    private String name;
    @JsonProperty("slots")
    private List<RuneSlotDTO> slots;

    public static void init() {
        runeData = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(new File("././resources/runesReforged.json"));
            Iterator<JsonNode> it = node.elements();

            while (it.hasNext()) {
                JsonNode runeTree = it.next();
                RuneTreeDTO dto = mapper.readValue(runeTree.toString(), RuneTreeDTO.class);
                runeData.put(dto.getId(), dto);

                for (RuneSlotDTO slot : dto.getSlots()) {
                    for (RuneDTO rune : slot.getRunes()) {
                        runeData.put(rune.getId(), rune);
                    }
                }

            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static RuneDTO getById(long id) {
        return runeData.get(id);
    }

}
