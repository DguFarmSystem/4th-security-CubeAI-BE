package com.cubeai.domain.auth.service;

import com.cubeai.domain.auth.dto.AuthDto;
import com.cubeai.domain.auth.dto.CustomOAuth2User;
import com.cubeai.domain.auth.dto.GoogleResponseDto;
import com.cubeai.domain.auth.dto.OAuth2ResponseDto;
import com.cubeai.domain.member.entity.Member;
import com.cubeai.domain.member.repository.MemberRepository;
import com.cubeai.global.error.ErrorCode;
import com.cubeai.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2ResponseDto oAuth2Response;
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponseDto(oAuth2User.getAttributes());
        } else {
            return null;
        }
        String oauthId = oAuth2Response.getProviderId();
        Member existingMember = memberRepository.findByOAuthId(oauthId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        Member member;
        if (existingMember == null) {
            member = Member.builder()
                    .OAuthId(oauthId)
                    .nickname(oAuth2Response.getName())
                    .profileUrl(oAuth2Response.getProfileUrl())
                    .build();
            memberRepository.save(member);
        } else {
            existingMember.updateNickname(oAuth2Response.getName());
            existingMember.updateProfileUrl(oAuth2Response.getProfileUrl());
            member = existingMember;
        }
        AuthDto authDto = AuthDto.of(member.getMemberId(), oauthId);
        return new CustomOAuth2User(authDto);
    }
}
