package com.article.article.service;

import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.entity.UserAccount;
import com.article.article.model.request.UserAccountRequest;
import com.article.article.model.response.UsernameResponse;
import com.article.article.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UsernameResponse createUserAccount(Header<UserAccountRequest> dto) {
        var userDto = dto.getData();
        var username = userDto.username();
        var password = userDto.password();
        var nickname = userDto.nickname();

        UserAccount userAccount = UserAccount.of(username, password, nickname);
        UserAccount savedUserAccount = userAccountRepository.save(userAccount);
        return new UsernameResponse(savedUserAccount.getUsername());
    }

    public UserAccountDto findByUsername(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow();
        return UserAccountDto.from(userAccount);
    }
}

