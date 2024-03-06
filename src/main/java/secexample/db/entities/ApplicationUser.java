package secexample.db.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import  secexample.db.enumerations.Role;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @ToString.Exclude
    private String name;

    @ToString.Exclude
    private String lastname;

    private String username;

    @ToString.Exclude
    private String password;

    @ToString.Exclude
    private boolean accountNonExpired;

    @ToString.Exclude
    private boolean accountNonLocked;

    @ToString.Exclude
    private boolean credentialsNonExpired;

    @ToString.Exclude
    private boolean enabled;


    private Integer failedLogins;

    @ToString.Exclude
    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Holiday> holidays;

    @Enumerated(EnumType.STRING)
    //better always carry with us roles
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;


    public ApplicationUser() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return authorities;
    }

}