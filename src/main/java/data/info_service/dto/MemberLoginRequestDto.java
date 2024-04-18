package data.info_service.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDto {

    private String email;
    private String password;

}
