
package API.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RuneDTO {

    @JsonProperty("icon")
    private String icon;
    @JsonProperty("id")
    private long id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("longDesc")
    private String longDesc;
    @JsonProperty("name")
    private String name;
    @JsonProperty("shortDesc")
    private String shortDesc;

}
