package vl.team07.com.virtuallibrary;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private String username = "TestUsername";
    private String name = "TestName";
    private String email = "Test@example.com";
    private User user = new User(username, name, email);


    @Test
    public void testGetUsername(){
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSetUsername(){
        String newUsername = "NewUsername";
        user.setUsername(newUsername);
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    public void testGetName(){
        assertEquals(name, user.getName());
    }

    @Test
    public void testSetName(){
        String newName = "NewName";
        user.setName(newName);
        assertEquals(newName, user.getName());
    }
    @Test
    public void testGetEmail(){
        assertEquals(email, user.getEmail());
    }

    @Test

    public void testSetEmail(){
        String newEmail = "New@Email.com";
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
    }

    @Test

    public void testAge(){
        int newAge = 20;
        user.setAge(newAge);
        assertEquals(newAge, user.getAge());
    }

    @Test

    public void testNationality(){
        String newNationality = "New Nationality";
        user.setNationality(newNationality);
        assertEquals(newNationality, user.getNationality());
    }


}
