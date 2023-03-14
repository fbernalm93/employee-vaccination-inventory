package vaccinationinventory.user.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccinationinventory.security.TokenJWT;
import vaccinationinventory.user.application.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/loginsystem")
    @ApiOperation(value = "Login with user and password, in this case the user and pass are the person id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Login Ok"),
            @ApiResponse(code = 400, message = "Not Login")})
    public ResponseEntity<TokenJWT> loginSystem(@RequestHeader ("Authorization") String authentication){
        return new ResponseEntity<TokenJWT>(userService.authenticationProcess(authentication), HttpStatus.OK);
    }

}
