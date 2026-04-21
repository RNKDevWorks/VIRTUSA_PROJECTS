package user;

import java.util.Scanner;
import service.Store;

public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public boolean checkPassword(String pwd) {
        return password.equals(pwd);
    }

    public abstract void showMenu(Store store, Scanner sc);
}