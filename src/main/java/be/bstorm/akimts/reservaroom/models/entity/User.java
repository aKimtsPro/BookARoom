package be.bstorm.akimts.reservaroom.models.entity;

import be.bstorm.akimts.reservaroom.models.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_username", nullable = false, unique = true)
    private String username;
    @Column(name = "user_password", nullable = false)
    private String password;
//    @Enumerated(EnumType.STRING)
//    @Column(name = "user_role", nullable = false)
//    private UserRole role;
    @Column(name = "user_enabled", nullable = false)
    private boolean enabled = true;

    @OneToMany(mappedBy = "bookedBy")
    private List<Booking> bookings;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "owner_id"))
    private Set<String> roles = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map( (role) -> new SimpleGrantedAuthority("ROLE_"+role) )
                .collect(Collectors.toSet());
//
//        List<GrantedAuthority> auths = new ArrayList<>();
//
//        for (String s : roles) {
//            auths.add( new SimpleGrantedAuthority( "ROLE_"+s ) );
//            if( s.equals("ADMIN") )
//                auths.add( new SimpleGrantedAuthority("WRITE"));
//        }
//
//        return auths;
//        return List.of( new SimpleGrantedAuthority( role.getAuthority() ));
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
}
