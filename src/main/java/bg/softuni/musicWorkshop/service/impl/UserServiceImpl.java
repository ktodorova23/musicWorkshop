package bg.softuni.musicWorkshop.service.impl;

import bg.softuni.musicWorkshop.model.entities.UserEntity;
import bg.softuni.musicWorkshop.model.entities.UserRoleEntity;
import bg.softuni.musicWorkshop.model.entities.enums.UserRoleEnum;
import bg.softuni.musicWorkshop.repositories.UserRepository;
import bg.softuni.musicWorkshop.repositories.UserRoleRepository;
import bg.softuni.musicWorkshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity("admin", passwordEncoder.encode("topsecret"));
            UserEntity userEntity = new UserEntity("user", passwordEncoder.encode("topsecret"));

            admin.setRoles(List.of(adminRole, userRole));
            userEntity.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, userEntity));
        }
    }
}
