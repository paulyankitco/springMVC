package kitco.security;

import java.util.UUID;

import org.springframework.stereotype.Component;

import kitco.bean.model.TokenModel;

@Component(value="tokenManager")
public class TokenManagerImpl implements TokenManager {

    public TokenModel createToken(long userId) {

        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        return model;
    }

    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        return new TokenModel(1, authentication);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = "";
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间

        return true;
    }

    public void deleteToken(long userId) {

    }
}