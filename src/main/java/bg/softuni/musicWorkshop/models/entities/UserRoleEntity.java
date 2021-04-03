package bg.softuni.musicWorkshop.models.entities;

import bg.softuni.musicWorkshop.models.entities.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRoleEntity extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRole;

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRoleEntity setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}