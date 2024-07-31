package com.godoynetworks.Saferoom.security;

import com.godoynetworks.Saferoom.model.Client;
import com.godoynetworks.Saferoom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(client.getEmail(), client.getPassword(),
                client.getRole(),
                client.getFirstName());
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//    }
}
