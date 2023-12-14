package com.sep6.app.controller;

import com.sep6.app.controller.request.AuthenticationRequest;
import com.sep6.app.controller.request.LoginResponse;
import com.sep6.app.controller.request.SimpleResponse;
import com.sep6.app.model.Friendship;
import com.sep6.app.model.FriendshipStatus;
import com.sep6.app.model.User;
import com.sep6.app.repository.FriendshipRepository;
import com.sep6.app.repository.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FriendshipRepository friendshipRepository;

    public AuthController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, FriendshipRepository friendshipRepository) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.friendshipRepository = friendshipRepository;
    }

    @PostMapping("/login")
    public LoginResponse auth(@RequestBody AuthenticationRequest auth) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.username(), auth.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Instant now = Instant.now();
        long expiry = 999999;
        String scope = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        final User user = userRepository.findByUsername(auth.username());

        return new LoginResponse(this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(), user.getId());
    }

    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> register(@RequestBody AuthenticationRequest auth) {

        if (userRepository.existsByUsername(auth.username())) {
            return ResponseEntity.badRequest().body(new SimpleResponse("Username is taken"));
        }

        User user = userRepository.save(new User(auth.email(), auth.username(), passwordEncoder.encode(auth.password())));

        return ResponseEntity.ok(new SimpleResponse("Register successful for user:" + user.getUsername()));

    }

    @GetMapping("/accessible")
    public ResponseEntity<Boolean> canAccessProfile(@RequestParam Integer requesterId, @RequestParam Integer resourceId) {
        Optional<Friendship> friendshipOptional1 = friendshipRepository.findByRequester_IdAndTarget_Id(requesterId, resourceId);
        Optional<Friendship> friendshipOptional2 = friendshipRepository.findByRequester_IdAndTarget_Id(resourceId, requesterId);

        if (friendshipOptional1.isEmpty() && friendshipOptional2.isEmpty()) {
            return ResponseEntity.ok().body(true);
        }

        if (friendshipOptional1.isPresent()) {
            if (friendshipOptional1.get().getStatus().equals(FriendshipStatus.BLOCKED))
                return ResponseEntity.notFound().build();
        }

        if (friendshipOptional2.isPresent()) {
            if (friendshipOptional2.get().getStatus().equals(FriendshipStatus.BLOCKED))
                return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok().body(true);
    }
}
