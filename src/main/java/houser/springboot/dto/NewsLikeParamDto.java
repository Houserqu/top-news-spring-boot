package houser.springboot.dto;

import javax.validation.constraints.NotBlank;

public class NewsLikeParamDto {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotBlank
    private String type;

}
