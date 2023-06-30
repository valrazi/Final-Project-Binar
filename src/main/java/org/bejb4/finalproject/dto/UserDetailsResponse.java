package org.bejb4.finalproject.dto;

public class UserDetailsResponse {
    private Long id;
    private String username;
    private String email;

    private String phoneNum;

    private String fullName;

    public UserDetailsResponse(Long id, String username, String email,
                       String phoneNum, String fullName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
