package com.example.hw28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyUser  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotEmpty(message = "username should not be null")
    @Column(unique = true)
    private String username;
    @NotEmpty(message = "password should not be null")
    private String password;

    @Pattern(regexp = "^(CUSTOMER||ADMIN)$",message = "role should be CUSTOMER or ADMIN only")
    @Column(columnDefinition = "VARCHAR(10) not null check (role='ADMIN' or role='CUSTOMER')")
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "myUser")
    private List<Orders>orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
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
