package com.example.moody;


import java.util.ArrayList;

public class FirebaseHelper {

    public FirebaseHelper(){
    }


    public ArrayList<String> friendStringToList(String friends){
        ArrayList<String> result = new ArrayList<>();
        String[] r = friends.split("/");
        for (String s:r) {
            result.add(s);
        }
        return result;
    }

    public boolean checkFriend(String friend, String email){
        if(friend.matches("(.*)" + email + "(.*)")){
            return true;
        }else{
            return  false;
        }
    }

    public String dataCleaner(String list){
        String result = "";
        String[] r = list.split("/");
        for (String email: r) {
            if(email.equals("")){
                continue;
            }else{
                result = result + "/" + email;
            }
        }
        return result;
    }

}
