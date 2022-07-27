package db;

import java.util.ArrayList;

public class InMemoryDB {
    private static ArrayList<User> userDatabase=new ArrayList();  // What we mostly do is accessing there for using an ArrayList would be ideal

    public static boolean registerUser(User user){
        if (findUser(user.getNic())!=null) return false;
        userDatabase.add(user);
        return true;
    }
    private static void removeUser(String nic){
       User user=findUser(nic);
       if (user!=null) userDatabase.remove(user);
    }
    private static User findUser(String nic){
        for (User us:userDatabase){
            if (us.getNic().equalsIgnoreCase(nic)){
                return us;
            }
        }
        return null;
    }
    private static ArrayList<User> printAllUsers(){
        return userDatabase;
    }

    public static boolean findUser(boolean b) {

        return false;
    }
}
