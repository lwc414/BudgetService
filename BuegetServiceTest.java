import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class BuegetServiceTest {

    @Test
    public void Oneday() {
        //當日

        System.err.println("123");

    }



    @Test
    public void HoldMonth() {
        //整個月
        System.err.println("123");

    }


    @Test
    public void CrossMonth() {
        //跨月
        System.err.println("456");

    }

    @Test
    public void Error_Time() {
        //(反向)非法區間-起迄日錯置
        System.err.println("456");

    }



}

