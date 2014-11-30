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
class Preference{
    boolean distance;
    boolean price;
    Preference(boolean d, boolean p){
        distance = d;
        price = p;
    }
}
public class MainApplication extends Application {

    private boolean loginStatus = false;
    public UserPerson me = new UserPerson();
    public Preference preference = new Preference(false, false);
    public String CSP_SERVER1 = "http://54.69.152.156:55321";
    public String CSP_SERVER2 = "http://104.236.55.89:3000";

    public boolean getLoginStatus(){
        return loginStatus;
    }
    public void setLoginStatus(boolean value){
        loginStatus = value;
    }

}
