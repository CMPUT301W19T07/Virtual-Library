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

public class SignUpTest {

    @Test
    public void UniqueUsername(){
        Userlist Tdata = new Userlist();
        String test = "Username3";
        boolean found = false;
        Tdata.addUser(new User("Username", "User","Email@test.com"));
        Tdata.addUser(new User("Username2","User2","Email2@test.com"));
        found = Tdata.UniqueUsername(test);
        assertFalse(found);
    }

    @Test
    public void ValidEmail(){
        Userlist Tdata = new Userlist();
        String test = "Email@test.com";
        boolean valid = false;
        valid = Tdata.ValidEmail(test);
        assertTrue(valid);

    }

}
