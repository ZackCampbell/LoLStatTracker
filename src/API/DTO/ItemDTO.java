package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "description",
        "colloq",
        "plaintext",
        "into", // What items it builds into
        "from", // What items it builds from
        "image",
        "gold",
        "tags",
        "maps",
        "stats",
        "effect",
        "depth", // ...?
        "hideFromAll",
        "consumed",
        "consumeOnFull",
        "requiredChampion",
        "stacks",
        "inStore",
        "specialRecipe"
})
@Getter
@ToString
public class ItemDTO {

    private static HashMap<Long, ItemDTO> itemData;

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    @ToString.Exclude
    private String description;
    @JsonProperty("colloq")
    @ToString.Exclude
    private String colloq;
    @JsonProperty("plaintext")
    @ToString.Exclude
    private String plaintext;
    @JsonProperty("into")
    @ToString.Exclude
    private String[] into = null;
    @JsonProperty("from")
    @ToString.Exclude
    private String[] from = null;
    @JsonProperty("image")
    @ToString.Exclude
    private ImageDTO image;
    @JsonProperty("gold")
    @ToString.Exclude
    private HashMap<String, Object> gold;
    @JsonProperty("tags")
    @ToString.Exclude
    private String[] tags;
    @JsonProperty("maps")
    @ToString.Exclude
    private HashMap<String, Boolean> maps;
    @JsonProperty("stats")
    @ToString.Exclude
    private HashMap<String, Float> stats;
    @JsonProperty("effect")
    @ToString.Exclude
    private HashMap<String, Float> effect; // Effect index to amount
    @JsonProperty("depth")
    private int depth;
    @JsonProperty("hideFromAll")
    @ToString.Exclude
    private boolean hideFromAll;
    @JsonProperty("consumed")
    @ToString.Exclude
    private boolean consumed;
    @JsonProperty("consumeOnFull")
    @ToString.Exclude
    private boolean consumeOnFull;
    @JsonProperty("requiredChampion")
    @ToString.Exclude
    private String requiredChampion;
    @JsonProperty("stacks")
    @ToString.Exclude
    private float stacks;
    @JsonProperty("inStore")
    @ToString.Exclude
    private boolean inStore;
    @JsonProperty("specialRecipe")
    @ToString.Exclude
    private float specialRecipe;
    @JsonProperty("requiredAlly")
    @ToString.Exclude
    private String requiredAlly; // For Ornn specific items, this is "Ornn"

    public static void init() {
        itemData = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(new File("././resources/item.json"));
            JsonNode data = node.get("data");
            Iterator<Map.Entry<String, JsonNode>> it = data.fields();

            while (it.hasNext()) {
                Map.Entry<String, JsonNode> item = it.next();
                ItemDTO itemDTO = mapper.readValue(item.getValue().toString(), ItemDTO.class);

                itemData.put(Long.parseLong(item.getKey()), itemDTO);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static ItemDTO getById(long id) {
        return itemData.get(id);
    }
}
