package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Get
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }



    // Post
    public void saveUser(User newUSer){
        userRepository.save(newUSer);
    }

    public boolean saveNewUser(User newUSer){
        try{
            newUSer.setPassword(passwordEncoder.encode(newUSer.getPassword()));
            newUSer.setRoles(Arrays.asList("USER"));
            User save = userRepository.save(newUSer);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public void saveAdmin(User newUSer){
        newUSer.setPassword(passwordEncoder.encode(newUSer.getPassword()));
        newUSer.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(newUSer);
    }

    // Delete
    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteUserByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }
}
