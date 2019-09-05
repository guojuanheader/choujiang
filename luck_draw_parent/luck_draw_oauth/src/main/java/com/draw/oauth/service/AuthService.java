package com.draw.oauth.service;

import com.draw.oauth.util.AuthToken;

public interface AuthService {
    AuthToken applyToken(String username, String password, String clientId, String clientSecret);
}
