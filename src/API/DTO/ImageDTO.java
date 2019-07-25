
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "full",
    "sprite",
    "group",
    "x",
    "y",
    "w",
    "h"
})
@Getter
@ToString
public class ImageDTO {

    @JsonProperty("full")
    private String full;
    @JsonProperty("sprite")
    private String sprite;
    @JsonProperty("group")
    private String group;
    @JsonProperty("x")
    private Long x;
    @JsonProperty("y")
    private Long y;
    @JsonProperty("w")
    private Long w;
    @JsonProperty("h")
    private Long h;

}
