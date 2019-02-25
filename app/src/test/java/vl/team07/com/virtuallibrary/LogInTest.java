/*
 * Class Name
 *
 * Date of Initiation
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import org.junit.Test;
import static org.junit.Assert.*;

public class LogInTest {

    @Test
    public void checkusername(){
        Userlist Tdata = new Userlist();
    String test = "Username";
    boolean found = false;
    Tdata.addUser(new User("Username", "User","Email@test.com"));
    Tdata.addUser(new User("Username2","User2","Email2@test.com"));
    found = Tdata.checkusername(test);
    assertTrue(found);
    }
/*
    Apparently the accounts do not need passwords
    Keep this code around just in case that gets changed
    @Test
    public void checkpassword(){
        Userlist Tdata = new Userlist();
        String Utest = "Username";
        String Ptest = "Password";
        boolean found = false;
        Tdata.addUser(new User("Username","Password","Email@test.com"));
        Tdata.addUser(new User("Username2","Password2","Email2@test.com"));
        found = Tdata.checkpassword(Utest,Ptest);
        assertTrue(found);
    }
*/
}
