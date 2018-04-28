/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duydat.dtos;

/**
 *
 * @author Nguyen Duy Dat
 */
public class UserDTO {
    //details of an user
    private String username,fullname,role,password;

    //constructors
    public UserDTO(String username, String fullname, String role, String password) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
        this.password = password;
    }

    public UserDTO() {
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
