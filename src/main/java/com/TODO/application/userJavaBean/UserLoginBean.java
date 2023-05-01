package com.TODO.application.userJavaBean;
import java.io.Serializable;
public class UserLoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    
    public void setUserName(String userName) {
    	this.userName=userName;
    }
    public void setPassword(String password) {
    	this.password=password;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
}
