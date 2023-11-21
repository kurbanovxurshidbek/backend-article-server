package com.article.article.service;

import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.entity.UserAccount;
import com.article.article.model.request.UserAccountRequest;
import com.article.article.model.response.UserIdResponse;
import com.article.article.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username).map(UserAccountDto::from);
    }

    public UserIdResponse createUserAccount(Header<UserAccountRequest> dto) {
        var userDto = dto.getData();
        var id = userDto.userId();
        var password = userDto.password();
        var email = userDto.email();
        var nickname = userDto.nickname();
        var memo = userDto.memo();

        UserAccount userAccount = UserAccount.of(id, password, email, nickname, memo);
        UserAccount savedUserAccount = userAccountRepository.save(userAccount);
        return new UserIdResponse(savedUserAccount.getUserId());
    }

    public UserAccountDto findByUserId(String userId) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId).orElseThrow();
        return UserAccountDto.from(userAccount);
    }
}

