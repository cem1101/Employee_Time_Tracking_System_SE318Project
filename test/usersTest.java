import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class usersTest {


    @Test
    public void getAuthGroupTest1() {
        Users._authgroup = 1;
        String result = Users.getAuthGroup();
        Assert.assertSame(result,"employee");
    }

    @Test
    public void getAuthGroupTest2() {
        Users._authgroup = 2;
        String result = Users.getAuthGroup();
        Assert.assertSame(result,"manager");
    }

    @Test
    public void getAuthGroupTest3() {
        Users._authgroup = 3;
        String result = Users.getAuthGroup();
        Assert.assertSame(result,"admin");
    }


    @Test
    public void getAuthGroupTest4() {
        Users._authgroup = 3;
        String result = Users.getAuthGroup();
        Assert.assertNotSame(result,"employee");
    }

    @Test
    public void getAuthGroupTest5() {
        Users._authgroup = 4;
        String result = Users.getAuthGroup();
        Assert.assertNotSame(result,"employee");
    }


}
