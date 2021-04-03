package bg.softuni.musicWorkshop.model.entities;

import bg.softuni.musicWorkshop.model.entities.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRoleEntity extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}