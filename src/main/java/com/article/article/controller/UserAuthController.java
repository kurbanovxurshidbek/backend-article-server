package com.article.article.controller;


import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.request.SignInRequest;
import com.article.article.model.request.SignUpRequest;
import com.article.article.model.request.UserAccountRequest;
import com.article.article.model.response.UserAccountResponse;
import com.article.article.service.UserAccountService;
import com.article.article.service.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/signup")
    public Header<?> signUp(@Valid @RequestBody Header<SignUpRequest> dto) {
        UserAccountDto accountDto = userAuthService.signUp(dto);
        return Header.ok(UserAccountResponse.from(accountDto));
    }

    @PostMapping("/signin")
    public Header<?> signIn(@Valid @RequestBody Header<SignInRequest> dto) {
        String jwtToken = userAuthService.signIn(dto);
        return Header.ok(jwtToken);
    }

}
