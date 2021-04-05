package bg.softuni.musicWorkshop.service;

import bg.softuni.musicWorkshop.model.binding.UserRegistrationBindingModel;
import bg.softuni.musicWorkshop.model.service.UserRegistrationServiceModel;

public interface UserService {
    void seedUsers();

    void registerAndLogin(UserRegistrationServiceModel userRegistrationServiceModel);
}
