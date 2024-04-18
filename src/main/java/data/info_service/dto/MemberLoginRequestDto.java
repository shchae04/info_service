package data.info_service.dto;

import data.info_service.entity.Role;
import lombok.Data;

@Data
public class MemberLoginRequestDto {

    private String email;
    private String username;
    private String password;
    private String nickName;
//    private Role role;

}
