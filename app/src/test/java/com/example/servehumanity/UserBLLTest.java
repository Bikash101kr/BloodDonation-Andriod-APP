package com.example.servehumanity;

        import com.example.servehumanity.bll.ProfileBLL;
        import com.example.servehumanity.bll.UserBLL;

        import org.junit.Before;
        import org.junit.Test;

        import static org.junit.Assert.assertEquals;

public class UserBLLTest {
    String Id;


    @Before
    public void setUp(){
        ProfileBLL profileBLL = new ProfileBLL("bikash", "dhakal", "2020-12-12",
                "address2", "Chitwan", "1234", "male",  "B+", "image.jepg");
        boolean res = profileBLL.addProfile();
        if (res) {
            Id = ProfileBLL.id;
        }
    }
    @Test
    public void checkLogin() {
        UserBLL userBLL = new UserBLL("bikash", "12345"  );
        boolean res = userBLL.loginUser();
        assertEquals(true, res);
    }

    @Test
    public void checkUserRegister() {
        UserBLL userBLL = new UserBLL("Bikash2", "password", "bikash33@gmail.com", Id);
        boolean res = userBLL.registerUser();
        assertEquals(true, res);
    }
}

