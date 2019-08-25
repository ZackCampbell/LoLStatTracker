
package API.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RuneSlotDTO {

    @JsonProperty("runes")
    private List<RuneDTO> runes;

}
