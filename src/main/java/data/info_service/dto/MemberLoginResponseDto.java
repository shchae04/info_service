package data.info_service.dto;

import lombok.Data;

@Data
public class MemberLoginResponseDto {

    private Long userId;
    private String email;
    private String password;
    private String nickName;

    private boolean success;

}
