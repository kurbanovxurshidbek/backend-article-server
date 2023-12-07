package com.article.article.service;

import com.article.article.model.common.Header;
import com.article.article.model.dto.UserAccountDto;
import com.article.article.model.entity.RoleType;
import com.article.article.model.entity.UserAccount;
import com.article.article.model.request.SignInRequest;
import com.article.article.model.request.SignUpRequest;
import com.article.article.model.response.UserAccountResponse;
import com.article.article.model.security.UserPrinciple;
import com.article.article.repository.RoleTypeRepository;
import com.article.article.repository.UserAccountRepository;
import com.article.article.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAuthService {
    private final UserAccountRepository userAccountRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleTypeRepository roleTypeRepository;

    public UserAccountDto signUp(Header<SignUpRequest> dto) {
        SignUpRequest request = dto.getData();
        var username = request.username();
        var password = passwordEncoder.encode(request.password());
        var nickname = request.nickname();

        UserAccount userAccount = UserAccount.of(username, password, nickname);
        var roleType = roleTypeRepository.findById(1L).orElseThrow();
        userAccount.addRoleType(roleType);

        UserAccount savedUserAccount = userAccountRepository.save(userAccount);
        return UserAccountDto.from(savedUserAccount);
    }

    public String signIn(Header<SignInRequest> dto) {
        SignInRequest request = dto.getData();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = userAccountRepository.findByUsername(request.username()).map(UserPrinciple::from).orElseThrow(() -> new IllegalArgumentException("Please check your login information"));
        //var user = (User) authenticate.getPrincipal();
        return jwtProvider.generateToken(user);
    }
}
