package bg.softuni.musicWorkshop.models.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {

    private String name;
    private String password;

    //if OneToMany there will be a unique key in the rel table
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoles;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleEntity> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
