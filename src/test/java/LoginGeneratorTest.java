import dcll.LoginGenerator;
import dcll.LoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by QYL on 2016/4/15.
 */
public class LoginGeneratorTest {
    LoginGenerator loginGenerator;

    @Before
    public void setUp() throws Exception {
        LoginService loginService = new LoginService(new String[] {"JROL", "BPER", "CGUR", "JDUP", "JRAL", "JRAL1"});
        loginGenerator = new LoginGenerator(loginService);
    }

    @Test
    public void testGenerateLoginForNomAndPrenom(){
        assertNotEquals("PDUR",this.loginGenerator.generateLoginForNomAndPrenom("Durand","Paul"));
        assertEquals("JROL1",this.loginGenerator.generateLoginForNomAndPrenom("Rolling","Jean"));
        assertEquals("PDUR1",this.loginGenerator.generateLoginForNomAndPrenom("Dùrand","Paul"));
    }
}
