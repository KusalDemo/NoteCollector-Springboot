package lk.ijse.NoteCollector_Springboot.Service_BO.impl;

import lk.ijse.NoteCollector_Springboot.Service_BO.JWTService;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTServiceImpl implements JWTService {

    @Override
    public String extractUsername(String jwtToken) {
        return "";
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return "";
    }

    @Override
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        return false;
    }

    @Override
    public String refreshToken(String prevJwtToken) {
        return "";
    }
}
