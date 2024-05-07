package data.info_service.service;

import data.info_service.dto.MemberLoginRequestDto;
import data.info_service.dto.MemberLoginResponseDto;
import data.info_service.dto.MemberRegisterRequestDto;
import data.info_service.dto.MemberRegisterResponseDto;
import data.info_service.entity.Member;
import data.info_service.entity.Role;
import data.info_service.exception.LoginFailureException;
import data.info_service.exception.MemberEmailAlreadyExistsException;
import data.info_service.repository.MemberRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberRegisterResponseDto registerMember(MemberRegisterRequestDto requestDto) {

        log.info("register member={}", requestDto);
        validateDuplicated(requestDto.getEmail());

        Member member = memberRepository.save(
                Member.builder()
                        .email(requestDto.getEmail())
                        .password(requestDto.getPassword())
                        .build()
        );
        log.info("Member Registered");

        return new MemberRegisterResponseDto();
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
        return new MemberLoginResponseDto();
    }


}
