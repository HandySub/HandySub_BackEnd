package com.example.handySub.domain.user.events;

import com.example.handySub.domain.user.collection.User;
import com.example.handySub.domain.user.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserListener extends AbstractMongoEventListener<User> {
    private final SequenceGeneratorService seqGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event){
        event.getSource().setId(seqGenerator.generateSeq(User.SEQUENCE_NAME));
    }
}
