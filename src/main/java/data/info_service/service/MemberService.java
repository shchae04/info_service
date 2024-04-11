package data.info_service.service;

import data.info_service.entity.Member;
import data.info_service.entity.Role;
import data.info_service.exception.LoginFailureException;
import data.info_service.exception.MemberEmailAlreadyExistsException;
import data.info_service.repository.MemberRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberRegisterResponseDto registerMember(MemberRegisterRequestDto requestDto) {
        validateDuplicated(requestDto.getEmail());

        Member member = memberRepository.save(
                Member.builder()
                        .email(requestDto.getEmail())
                        .password(requestDto.getPassword())
                        .build()
        );

        return new MemberRegisterResponseDto(member.getId(), member.getEmail());
    }


    public void validateDuplicated(String email) {
        if (memberRepository.findByEmail(email).isPresent())
            throw new MemberEmailAlreadyExistsException();
    }

    public MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow();
        if (!requestDto.getPassword().equalsIgnoreCase(member.getPassword())) {
            throw new LoginFailureException();
        }
        return new MemberLoginResponseDto(member.getId(), "token");
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class MemberRegisterResponseDto {
        private Long id;
        private String email;
        private Role role;
        private String message;

        public MemberRegisterResponseDto(Long id, String email) {
            this.id = id;
            this.email = email;
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class MemberRegisterRequestDto {
        private String email;
        private Role role;
        private String message;
        private String password;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class MemberLoginResponseDto {
        private Long id;

        private String token;
        private String email;
        private String password;

        public MemberLoginResponseDto(Long id, String token) {
            this.id = id;
            this.token = token;
        }

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class MemberLoginRequestDto {
        private Long id;

        private String email;
        private String password;

    }
}
