package bg.softuni.musicWorkshop.service.impl;

import bg.softuni.musicWorkshop.model.entities.UserEntity;
import bg.softuni.musicWorkshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MusicDBUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public MusicDBUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name" + username + "was not found"));

        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> authorities =
                userEntity.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole().name())).collect(Collectors.toList());

        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                authorities);
    }
}
