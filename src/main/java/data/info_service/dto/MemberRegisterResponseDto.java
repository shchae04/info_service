package data.info_service.dto;

import lombok.Data;

@Data
public class MemberRegisterResponseDto {

    private Long userId;
    private String username;
    private String nickName;
}
