package education.cs.scu.javautils;

import java.util.Random;

/**
 * Created by maicius on 2017/6/27.
 */
public class VerifyCodeUtil {
    public static int createVerifyCode(){
        int max=999999;
        int min=100000;
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }
}
