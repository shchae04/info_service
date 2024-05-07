package data.info_service.dto;

import lombok.Data;

@Data
public class MemberRegisterRequestDto {


    private String email;
    private String username;
    private String password;
    //DEFAULT SET SYS_USER
//    private Role role;
}
