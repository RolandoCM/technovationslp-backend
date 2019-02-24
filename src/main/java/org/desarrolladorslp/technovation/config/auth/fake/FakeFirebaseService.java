package org.desarrolladorslp.technovation.config.auth.fake;

import java.util.Map;

import org.desarrolladorslp.technovation.config.auth.TokenInfo;
import org.desarrolladorslp.technovation.services.FirebaseService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("fake-token-granter")
public class FakeFirebaseService implements FirebaseService {

    private FakeTokenProperties fakeTokenProperties;

    public FakeFirebaseService(FakeTokenProperties fakeTokenProperties) {
        this.fakeTokenProperties = fakeTokenProperties;
    }

    @Override
    public TokenInfo parseToken(String idToken) {
        Map<String, FakeToken> tokens = fakeTokenProperties.getFakeTokens();

        if (!tokens.containsKey(idToken))
            return null;

        FakeToken fakeToken = tokens.get(idToken);
        TokenInfo token = new TokenInfo();

        token.setEmail(fakeToken.getEmail());
        token.setName(fakeToken.getName());
        token.setUid(idToken);

        return token;
    }
}