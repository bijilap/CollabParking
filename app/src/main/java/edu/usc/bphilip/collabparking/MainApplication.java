package edu.usc.bphilip.collabparking;

import android.app.Application;

import com.google.android.gms.plus.model.people.Person;

/**
 * Created by bijil_000 on 11/24/2014.
 */

class UserPerson{
    String name;
    String id;
    Person.Image pic;
}
public class MainApplication extends Application {

    private boolean loginStatus = false;
    public UserPerson me = new UserPerson();

    public boolean getLoginStatus(){
        return loginStatus;
    }
    public void setLoginStatus(boolean value){
        loginStatus = value;
    }

}
