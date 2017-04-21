import java.io.File;

/**
 * Created by linhtran on 21/04/2017.
 */
public class SendMail {
    public static void main(String[] args) {
        File file=new File(ClassLoader.getSystemResource("csdldemo2.sql").getFile());
        System.out.println(file.isFile());
        System.out.println(file.getAbsolutePath());
    }
}
