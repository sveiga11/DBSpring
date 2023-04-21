package com.example.DBSpring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/v1/users";
    @Autowired
    UserController userController;

    @GetMapping
    public List<UserDTO> users(){
       return userController.readAll();
    }

    @GetMapping("/{id}")
    public UserDTO user(@PathVariable Integer id) {
        return userController.getUserById(id);
    }

    @GetMapping("/{id}/email")
    public Map<String,String> email(@PathVariable Integer id) {
        return Collections.singletonMap("email", userController.getUserById(id).getEmail());
    }

    @PostMapping
    public UserDTO newUser(@RequestBody UserDTO userDTO){
        return userController.addUser(userDTO);
    }
    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable Integer id){
        userController.deleteUserById(id);
    }

    @PutMapping("{id}")
    public void editUserById(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
        UserDTO userDTO1 = userController.getUserById(id);
        userDTO1.setId(userDTO.getId());
        userDTO1.setFullName(userDTO.getFullName());
        userDTO1.setEmail(userDTO.getEmail());
        userDTO1.setPassword(userDTO.getPassword());
        userController.editUserById(userDTO);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public void updateUser(@PathVariable Integer id, @RequestBody JsonPatch jsonPatch) {
        userController.patchUser(id, jsonPatch);
    }

}
