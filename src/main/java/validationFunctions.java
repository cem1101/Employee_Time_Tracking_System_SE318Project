public class validationFunctions {











    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }




    public static boolean isValidMail(String str) {
        if (str == null) {
            return false;
        }
        if(str.indexOf("@") == -1){
            return false;
        }

        int checkCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '@') {
                checkCounter++;
            }
        }

        if(checkCounter>1){
            return false;
        }

        String domainSideOfEmail = str.substring(str.indexOf("@"));


        // check the domain of email is valid
        checkCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                checkCounter++;
            }
        }
        if(checkCounter<1){
            return false;
        }


        return true;
    }









}
