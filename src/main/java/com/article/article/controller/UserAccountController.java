package com.article.article.controller;


import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.request.UserAccountRequest;
import com.article.article.model.response.UserAccountResponse;
import com.article.article.model.response.UsernameResponse;
import com.article.article.model.security.UserPrinciple;
import com.article.article.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/users/me")
    public Header<?> getUser(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        UserAccountDto userAccountDto = userAccountService.findByUsername(userPrinciple.getUsername());
        return Header.ok(UserAccountResponse.from(userAccountDto));
    }

}
