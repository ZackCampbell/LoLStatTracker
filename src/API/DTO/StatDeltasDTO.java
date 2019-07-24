
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "0-10",
    "10-20",
    "20-30",
    "30-end"
})
@Getter
@ToString
public class StatDeltasDTO {

    @JsonProperty("0-10")
    private Double _010;
    @JsonProperty("10-20")
    private Double _1020;
    @JsonProperty("20-30")
    private Double _2030;
    @JsonProperty("30-end")
    private Double _30end;

}
