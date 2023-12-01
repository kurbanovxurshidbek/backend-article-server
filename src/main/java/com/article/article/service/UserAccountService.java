package com.article.article.service;

import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.entity.UserAccount;
import com.article.article.model.request.UserAccountRequest;
import com.article.article.model.response.UsernameResponse;
import com.article.article.model.security.UserPrinciple;
import com.article.article.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountDto findByUsername(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow();
        return UserAccountDto.from(userAccount);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username)
                .map(userAccount ->
                        new UserPrinciple( // #principle - 2
                                userAccount.getUsername(),
                                userAccount.getPassword(),
                                userAccount.getNickname(),
                                userAccount.getRoles()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

