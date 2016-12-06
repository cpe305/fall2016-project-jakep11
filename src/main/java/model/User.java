package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity

public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String username;
  private String password;
  private ArrayList<Long> triathlons;

  protected User() {}

  /**
   * User constructor.
   * @param username unique username
   * @param password hashed password
   */
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    triathlons = new ArrayList<Long>();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * addTri adds a new triathlon id.
   * @param id unique id of the triathlon
   */
  public void addTri(long id) {
    if (triathlons.contains(id)) {
      return;
    }
    triathlons.add(id);
  }
  
  public void deleteTri(long id) {
    triathlons.remove(id);
  }

  public ArrayList<Long> getTris() {
    return triathlons;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return String.format("User[id=%d, userName='%s', password='%s', numTris='%s']", id, username,
        password, triathlons.size());
  }

  public String getPassword() {
    return password;
  }

  /**
   * getRoles needed for user athentication.
   * @return returns a list of roles. Only USER roles
   */
  public List<String> getRoles() {
    ArrayList<String> list = new ArrayList<String>();
    list.add("USER");
    return list;
  }

}
