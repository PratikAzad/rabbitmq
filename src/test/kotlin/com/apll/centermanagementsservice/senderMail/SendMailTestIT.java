package com.apll.centermanagementsservice.senderMail;

import com.apll.centermanagementsservice.util.SenderMail;
import org.junit.Assert;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;


public class SendMailTestIT {



    @Test
    public void sendMail()throws MessagingException {
        String result= SenderMail.sendMail("pkazad20@gmail.com");
        System.out.println(result);
        Assert.assertEquals(true,result.equals("Mail Send"));
    }

    @Test
    public void sendAllMail()throws MessagingException {
        List<String> list=new ArrayList<>();

        list.add("aatramshipra@gmail.com");
        list.add("rameshshaka@gmail.com");
        list.add("pkazad20@gmail.com");


        String result= SenderMail.sendAllMail(list);
        System.out.println(result);
        Assert.assertEquals(true,result.equals("Mail Send"));
    }

    /*@Test
    public void testProp(){
        PropTest a=new PropTest();
        System.out.println(a.getMyProperties());
    }*/
}