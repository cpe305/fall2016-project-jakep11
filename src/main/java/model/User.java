package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCrypt;

import app.PasswordMatches;

@Entity
@PasswordMatches
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String username;
  private String password;
  private ArrayList<Long> triathlons;

  protected User() {}

  public User(String username, String password) {
    this.username = username;
    //this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    this.password = password;
    triathlons = new ArrayList<Long>();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void addTri(long id) {
    triathlons.add(id);
  }
  
  public ArrayList<Long> getTris() {
    return triathlons;
  }
  
  public Long getId() {
    return id;
  }
  
  @Override
  public String toString() {
    return String.format("User[id=%d, userName='%s', password='%s']", id, username, password);
  }

  public String getPassword() {
    return password;
  }

  public List<String> getRoles() {
    ArrayList<String> list = new ArrayList<String>();
    list.add("USER");
    return list;
  }

}
