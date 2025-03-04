package org.spacademy.hotelmanagement.controller.auth;

import java.util.Optional;

import jakarta.persistence.EntityExistsException;

import org.spacademy.hotelmanagement.dto.AuthenticationRequest;
import org.spacademy.hotelmanagement.dto.AuthenticationResponse;
import org.spacademy.hotelmanagement.dto.SignupRequest;
import org.spacademy.hotelmanagement.dto.UserDto;
import org.spacademy.hotelmanagement.entity.User;
import org.spacademy.hotelmanagement.repository.UserRepository;
import org.spacademy.hotelmanagement.services.auth.AuthService;
import org.spacademy.hotelmanagement.services.jwt.UserService;
import org.spacademy.hotelmanagement.utill.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	private final AuthenticationManager authenticationManager;
	
	private final UserRepository userRepository;
	
	private final JwtUtil jwtUtil;
	
	private final UserService userService;
	
    @PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		try {
			System.out.println("controll here");
			UserDto createUser =authService.createUser(signupRequest);
			return new ResponseEntity<>(createUser, HttpStatus.OK);
		} catch(EntityExistsException entityExistException) {
			return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
		} catch(Exception e) {
			return new ResponseEntity<>("User not created, come again later", HttpStatus.BAD_REQUEST);
		}
	}
    
    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
    	} 
    	catch (BadCredentialsException e) {
    		throw new BadCredentialsException("Incorrect username or password");
    	}
    	
    	final UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
    	
    	Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
    	final String jwt= jwtUtil.generateToken(userDetails);
    	AuthenticationResponse authenticationResponse= new AuthenticationResponse();
    	if(optionalUser.isPresent()) {
	    	authenticationResponse.setJwt(jwt);
	    	authenticationResponse.setUserRole(optionalUser.get().getUserRole());
	    	authenticationResponse.setUserId(optionalUser.get().getId());
    	}
    	return authenticationResponse;
    	
    }

}
