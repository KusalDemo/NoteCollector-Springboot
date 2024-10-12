package lk.ijse.NoteCollector_Springboot.Service_BO;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUsername(String jwtToken);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String jwtToken, UserDetails userDetails);
    String refreshToken(String prevJwtToken);
}
