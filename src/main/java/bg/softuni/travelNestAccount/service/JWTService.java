package bg.softuni.travelNestAccount.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
   UserDetails extractUserDetails(String jwtToken);
}
