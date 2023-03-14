package vaccinationinventory.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vaccinationinventory.security.JwtUtilService;
import vaccinationinventory.security.TokenJWT;

import java.util.Base64;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtilService jwt;
    @Autowired
    private UserDetailService detailService;
    public TokenJWT authenticationProcess(String authentication) {
        authentication = authentication.replace("Basic ","");
        byte[] decodedBytes = Base64.getDecoder().decode(authentication);
        String decodedString = new String(decodedBytes);
        String[] credentials =  decodedString.split(":");
        String user = credentials[0];
        String pass = credentials[1];
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user, pass));
        final UserDetails userdetils = detailService.loadUserByUsername(user);

        final String tokenjwt = jwt.generateToken(userdetils);

        return new TokenJWT(tokenjwt);
    }

}
