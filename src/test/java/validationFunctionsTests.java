import org.junit.Assert;
import org.junit.Test;

public class validationFunctionsTests {




    Users users;
    private dummyDataFunctionsForTests tf = new dummyDataFunctionsForTests();

    private db db = new db();


/// is numeric positives
    @Test
    public void isNumericPositive1(){
        Assert.assertTrue(validationFunctions.isNumeric("123"));
    }
    @Test
    public void isNumericPositive2(){
        Assert.assertTrue(validationFunctions.isNumeric("0"));
    }

    @Test
    public void isNumericPositive3(){
        Assert.assertTrue(validationFunctions.isNumeric("-123"));
    }


/// is numeric negatives

    @Test
    public void isNumericNegative2(){
        Assert.assertFalse(validationFunctions.isNumeric("abc"));
    }

    @Test
    public void isNumericNegative3(){
        Assert.assertFalse(validationFunctions.isNumeric(""));
    }

    @Test
    public void isNumericNegative4(){
        Assert.assertFalse(validationFunctions.isNumeric("a123b"));
    }
    @Test
    public void isNumericNegative5(){
        Assert.assertFalse(validationFunctions.isNumeric("a123"));
    }

    @Test
    public void isNumericNegative6(){
        Assert.assertFalse(validationFunctions.isNumeric("123a"));
    }



/// is valid email positives


    @Test
    public void isValidMailPositive1(){
        Assert.assertTrue(validationFunctions.isValidMail("can@bagdiken.com"));
    }

    @Test
    public void isValidMailPositive2(){
        Assert.assertTrue(validationFunctions.isValidMail("can.bagdiken@bagdiken.com"));
    }

    @Test
    public void isValidMailPositive3(){
        Assert.assertTrue(validationFunctions.isValidMail("can@std.ieu.edu.tr"));
    }

    @Test
    public void isValidMailPositive4(){
        Assert.assertTrue(validationFunctions.isValidMail("can.bagdiken@std.ieu.edu.tr"));
    }




/// is valid email negatives


    @Test
    public void isValidMailNegative1(){
        Assert.assertFalse(validationFunctions.isValidMail("canbagdiken.com"));
    }

    @Test
    public void isValidMailNegative2(){
        Assert.assertFalse(validationFunctions.isValidMail("can@bagdiken"));
    }

    @Test
    public void isValidMailNegative3(){
        Assert.assertFalse(validationFunctions.isValidMail("can@"));
    }









}
