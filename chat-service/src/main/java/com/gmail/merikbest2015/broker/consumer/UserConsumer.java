package com.gmail.merikbest2015.broker.consumer;

import com.gmail.merikbest2015.event.BlockUserEvent;
import com.gmail.merikbest2015.event.FollowRequestUserEvent;
import com.gmail.merikbest2015.event.FollowUserEvent;
import com.gmail.merikbest2015.event.UpdateUserEvent;
import com.gmail.merikbest2015.service.UserHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static com.gmail.merikbest2015.constants.KafkaTopicConstants.*;
import static com.gmail.merikbest2015.constants.PathConstants.AUTH_USER_ID_HEADER;

@Component
@RequiredArgsConstructor
public class UserConsumer {

    private final UserHandlerService userHandlerService;

    @KafkaListener(topics = UPDATE_USER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void userUpdateListener(UpdateUserEvent userEvent) {
        userHandlerService.handleUpdateUser(userEvent);
    }

    @KafkaListener(topics = BLOCK_USER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void userBlockListener(BlockUserEvent userEvent, @Header(AUTH_USER_ID_HEADER) String authId) {
        userHandlerService.handleBlockUser(userEvent, authId);
    }

    @KafkaListener(topics = FOLLOW_USER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void userFollowListener(FollowUserEvent userEvent, @Header(AUTH_USER_ID_HEADER) String authId) {
        userHandlerService.handleFollowUser(userEvent, authId);
    }

    @KafkaListener(topics = FOLLOW_REQUEST_USER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void userFollowRequestListener(FollowRequestUserEvent userEvent, @Header(AUTH_USER_ID_HEADER) String authId) {
        userHandlerService.handleFollowUserRequest(userEvent, authId);
    }
}
