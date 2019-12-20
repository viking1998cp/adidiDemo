package manhthang.adididemo.Data;

import java.util.ArrayList;

import manhthang.adididemo.Object.User;

public class AccountData {
    //Demo phone and pass
    public static final String OTP = "1234";
    private static User user;
    public static User getAccount(){
        if(user == null){
            user = new User();
            user.setFirstName("Mạnh");
            user.setLastName("Thắng");
            user.setEmail("ngthang12081998@gmail.com");
            user.setPassWord("123456");
            user.setPhoneNumber("123456");
            return user;
        }else {
            return user;
        }
    }

    public static User register(User tmp){
        if(user == null){
            user = new User();
        }
        user.setLastName(tmp.getLastName());
        user.setFirstName(tmp.getFirstName());
        user.setPhoneNumber(tmp.getPhoneNumber());
        user.setPassWord(tmp.getPassWord());
        user.setEmail(tmp.getEmail());

        return user;

    }
}
