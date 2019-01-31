package kitco.security;

import kitco.bean.model.TokenModel;

public interface TokenManager {

    public TokenModel createToken(long userId);

    public boolean checkToken(TokenModel model);

    public TokenModel getToken(String authentication);

    public void deleteToken(long userId);

}
