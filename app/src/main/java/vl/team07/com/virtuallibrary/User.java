/*
 * Copyright <2019-1-23> <Ronghui Shao>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package vl.team07.com.virtuallibrary;

public class User {

    private String UserName;
    private String Name;
    private int Password;
    private String Email;
    private int Age;
    private String Nationality;
    private int ContactInfo;    // Phone number
    private String Address;

    public User(){
        this.UserName = "";
        this.Name = "";
        this.Password = 0;
        this.Email = "";
        this.Age = 0;
        this.Nationality = "";
        this.ContactInfo = 0;
        this.Address = "";
    }

    public User(String username, String name, int password, String email, int age, String nationality, int contactInfo, String address){
        this.UserName = username;
        this.Name = name;
        this.Password = password;
        this.Email = email;
        this.Age = age;
        this.Nationality = nationality;
        this.ContactInfo = contactInfo;
        this.Address = address;
    }

    public void setUserName(String inputUserName){this.UserName = inputUserName;}

    public void setName(String inputName){this.Name = inputName;}

    public void setPassword(int inputPassword){this.Password = inputPassword;}

    public void setEmail(String inputEmail){this.Email = inputEmail;}

    public void setAge(int inputAge){this.Age = inputAge;}

    public void setNationality(String inputNationality){this.Nationality = inputNationality;}

    public void setContactInfo(int inputContactInfo){this.ContactInfo = inputContactInfo;}

    public void setAddress(String inputAddress){this.Address = inputAddress;}

    public String getUserName(){return this.UserName;}

    public String getName(){return this.Name;}

    public int getPassword(){return this.Password;}

    public String getEmail(){return this.Email;}

    public int getAge(){return this.Age;}

    public String getNationality(){return this.Nationality;}

    public int getContactInfo(){return this.ContactInfo;}

    public String getAddress(){return this.Address;}

}