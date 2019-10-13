package model;

import java.util.Date;


public class User {
    public enum Role {
        ADMIN,
        USER
    }

    private Long id;
    private String cpf;
    private String email;
    private String fullName;
    private String username;
    private String password;
    private Date lastAccess;
    private Role role; /*Papel: Adm ou usuário*/

    public User(String cpf, String email, String fullName, String username, String password) {
        this.cpf = cpf;
        this.email = email;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
