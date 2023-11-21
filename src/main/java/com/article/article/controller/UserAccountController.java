package com.article.article.controller;


import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.request.UserAccountRequest;
import com.article.article.model.response.UserIdResponse;
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
        UserIdResponse userAccount = userAccountService.createUserAccount(dto);
        return Header.ok(userAccount);
    }

    @GetMapping("/users/{userId}")
    public Header<?> getUser(@PathVariable("userId") String userId) {
        UserAccountDto userAccountDto = userAccountService.findByUserId(userId);
        return Header.ok(userAccountDto);
    }

}
