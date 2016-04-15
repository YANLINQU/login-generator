import dcll.LoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by QYL on 2016/4/15.
 */
public class LoginServiceTest {
    LoginService loginService;

    @Before
    public void setUp() throws Exception {
        loginService = new LoginService(new String[] {"JROL", "BPER", "CGUR", "JDUP", "JRAL", "JRAL1"});
    }

    @Test
    public void testLoginExists(){
        assertEquals(true,loginService.loginExists("JROL"));
    }

    @Test
    public void testAddLogin(){
        this.loginService.addLogin("QYL");
    }

    @Test
    public void testFindAllLoginsStartingWith(){
        assertNotEquals(0,this.loginService.findAllLoginsStartingWith("qyl"));
    }

    @Test
    public void testFindAllLoginsStartingWith2(){
        assertEquals(0,this.loginService.findAllLoginsStartingWith("GGG").size());
    }

    @Test
    public void testFindAllLogins(){
        assertNotNull(this.loginService.findAllLogins());
    }
}
