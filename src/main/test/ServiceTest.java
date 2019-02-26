import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceTest {

    @Test
    public void testOrderId() {
        StringBuilder orderId = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            orderId.append((int) (Math.random() * 10));
        }
        System.out.println(orderId.toString());
    }

    @Test
    public void testCreateTime(){
        /*Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("当前时间为: " + ft.format(dNow));*/

        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
}
