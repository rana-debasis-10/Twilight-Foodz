package com.twilight.services;

import com.twilight.components.database.Customer;
import com.twilight.components.security.UserDetailsImpl;
import com.twilight.dataTransferObjects.authentication.BasicUserDetails;
import com.twilight.dataTransferObjects.authentication.LoginDetails;
import com.twilight.exceptions.UserAlreadyExists;
import com.twilight.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final CustomerRepo customerRepo;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService, CustomerRepo customerRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.customerRepo = customerRepo;
    }

    @Transactional
    /// For Registration
    public String register(BasicUserDetails basicUserDetails) throws UserAlreadyExists {
        return getString(basicUserDetails, passwordEncoder, customerRepo, jwtService);

    }

    static String getString(BasicUserDetails basicUserDetails, PasswordEncoder passwordEncoder, CustomerRepo customerRepo, JwtService jwtService) {
        Customer customer = new Customer(basicUserDetails);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try{
            customerRepo.save(customer);
            return jwtService.generateToken(customer.getId(),customer.getRole(),customer.getMobNo());
        }
        catch(Exception e){
            throw new UserAlreadyExists(customer.getEmail());
        }
    }

    @Transactional
    public String registerOnly(BasicUserDetails basicUserDetails)throws UserAlreadyExists {
        Customer customer = new Customer(basicUserDetails);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try{
            customerRepo.save(customer);
            return customer.getId();
        }
        catch(Exception e){
            throw new UserAlreadyExists(customer.getEmail());
        }
    }
    @Transactional
    public String login(LoginDetails loginDetails){
        return getString(loginDetails, authenticationManager, jwtService);
    }

    static String getString(LoginDetails loginDetails, AuthenticationManager authenticationManager, JwtService jwtService) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.email(), loginDetails.password()));
        if (authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            assert userDetails != null;
            return jwtService.generateToken(userDetails.getId(),userDetails.getRole(),userDetails.getMobNo());
        } else {
            throw new UsernameNotFoundException(loginDetails.email());
        }
    }
}
