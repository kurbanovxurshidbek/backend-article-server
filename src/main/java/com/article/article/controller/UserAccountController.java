package com.article.article.controller;


import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.request.UserAccountRequest;
import com.article.article.model.response.UserAccountResponse;
import com.article.article.model.response.UsernameResponse;
import com.article.article.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/users")
    public Header<?> createUser(@Valid @RequestBody Header<UserAccountRequest> dto) {
        UsernameResponse userAccount = userAccountService.createUserAccount(dto);
        return Header.ok(userAccount);
    }

    @GetMapping("/users/{username}")
    public Header<?> getUser(@PathVariable("username") String username) {
        UserAccountDto userAccountDto = userAccountService.findByUsername(username);
        return Header.ok(UserAccountResponse.from(userAccountDto));
    }

}
