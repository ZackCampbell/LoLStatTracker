
package API.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "0-10",
    "10-20",
    "20-30",
    "30-end"
})
public class StatDeltasDTO {

    @JsonProperty("0-10")
    public Double _010;
    @JsonProperty("10-20")
    public Double _1020;
    @JsonProperty("20-30")
    public Double _2030;
    @JsonProperty("30-end")
    public Double _30end;

}
