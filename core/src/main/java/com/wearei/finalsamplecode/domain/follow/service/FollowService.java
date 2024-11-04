package com.wearei.finalsamplecode.domain.follow.service;

import com.wearei.finalsamplecode.domain.follow.dto.request.CreateFollowRequest;
import com.wearei.finalsamplecode.domain.follow.dto.response.CreateFollowResponse;
import com.wearei.finalsamplecode.domain.follow.entity.Follow;
import com.wearei.finalsamplecode.domain.follow.repository.FollowRepository;
import com.wearei.finalsamplecode.domain.player.entity.Player;
import com.wearei.finalsamplecode.domain.player.repository.PlayerRepository;
import com.wearei.finalsamplecode.domain.user.entity.User;
import com.wearei.finalsamplecode.domain.user.repository.UserRepository;
import com.wearei.finalsamplecode.apipayload.status.ErrorStatus;
import com.wearei.finalsamplecode.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public CreateFollowResponse createFollow(CreateFollowRequest createFollowRequest) {
        User user = userRepository.findById(
                createFollowRequest.getUserId()).orElseThrow(
                        ()-> new ApiException(ErrorStatus._NOT_FOUND_USER));

        Player player = playerRepository.findById(
                createFollowRequest.getPlayerId()).orElseThrow(
                ()-> new ApiException(ErrorStatus._NOT_FOUND_PLAYER));

        Follow follow = new Follow(user,player);
        Follow savedFollow = followRepository.save(follow);

        return new CreateFollowResponse(savedFollow.getFollowId(), player.getId());
    }
}
