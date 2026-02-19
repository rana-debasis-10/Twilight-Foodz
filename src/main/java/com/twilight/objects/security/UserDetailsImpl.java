package com.twilight.objects.security;

import com.twilight.objects.database.Customer;
import com.twilight.types.Role;

import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private String id;
    private String password;
    private String mobNo;
    private Role role;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public UserDetailsImpl(Customer customer) {

        this.id = customer.getId();
        this.password = customer.getPassword();
        this.role = customer.getRole();
        this.accountNonExpired = customer.isAccountNonExpired();
        this.accountNonLocked = customer.isAccountNonLocked();
        this.credentialsNonExpired = customer.isCredentialsNonExpired();
        this.enabled = customer.isEnabled();
        this.mobNo = customer.getMobNo();
    }
    public UserDetailsImpl (String id, Role role,String mobNo, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled){
        this.id = id;
        this.role = role;
        this.mobNo = mobNo;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + this.role)
        );
    }
    @Override @NonNull
    public String getUsername() { return id; }
    @Override public String getPassword() { return password; }
    @Override public boolean isAccountNonExpired() { return accountNonExpired; }
    @Override public boolean isAccountNonLocked() { return accountNonLocked; }
    @Override public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
    @Override public boolean isEnabled() { return enabled; }
}

