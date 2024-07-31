package com.immunisation.immunisationcode.ENTITIES;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Guardian")
@Data
@Builder
@AllArgsConstructor
public class Guardian implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("Id")
    @Column(name = "Id")
    private int Id;

    @JsonProperty("firstName")
    @Column(name = "firstName")
    private String firstName ;

    @JsonProperty("lastName")
    @Column(name = "lastName")
    private String lastName ;

    @JsonProperty("email")
    @Column(name = "email", unique = true)
    private String email;

    @JsonProperty("Password")
    @Column(name = "Password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public Guardian() {
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
