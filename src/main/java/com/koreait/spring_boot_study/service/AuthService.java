package com.koreait.spring_boot_study.service;

import com.koreait.spring_boot_study.dto.SignUpRespDto;
import com.koreait.spring_boot_study.dto.SignupReqDto;
import com.koreait.spring_boot_study.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    public SignUpRespDto signup(SignupReqDto signupReqDto){
        if(signupReqDto.getEmail() == null || signupReqDto.getEmail().trim().isEmpty()){
            SignUpRespDto signUpRespDto = new SignUpRespDto("failed", "이메일을 입력해주세요");
            return signUpRespDto;
        }else if (signupReqDto.getPassword() == null || signupReqDto.getPassword().trim().isEmpty()){
            SignUpRespDto signUpRespDto = new SignUpRespDto("failed","비밀번호를 입력해주세요");
            return signUpRespDto;
        }else if (signupReqDto.getUsername() == null || signupReqDto.getUsername().trim().isEmpty()){
            SignUpRespDto signUpRespDto = new SignUpRespDto("failed","사용자 이름을 입력해주세요");
            return signUpRespDto;
        }

        int chkEmail = authRepository.findByEmail(signupReqDto.getEmail());
        if(chkEmail == 1){
            authRepository.addUser(signupReqDto);
            return new SignUpRespDto("success",signupReqDto.getUsername()+ "님 회원가입이 완료되었습니다.");
        } else if (chkEmail == 0) {
            return new SignUpRespDto("failed","이미 존재하는 이메일입니다.");
        }
        return new SignUpRespDto("failed","회원가입에 오류가 발생했습니다.");
    }
}
