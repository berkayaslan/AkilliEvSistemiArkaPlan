package Test;

import ConnectionHelper.AllComponentsAsker;
import ConnectionHelper.ICommunicationUser;
import ConnectionHelper.SocketCommunicationHelper;
import ConnectionHelper.SwitchStateAsker;
import Data.Components;
import org.json.JSONException;
import org.json.JSONObject;

public class TestProject implements ICommunicationUser {
    static Components components;
    static boolean flag = true;

    public static void main(String[] args){


        TestProject user = new TestProject();
        SocketCommunicationHelper helper = new SocketCommunicationHelper("http://akilli-ev.herokuapp.com");
        AllComponentsAsker componentsAsker = new AllComponentsAsker(user);
        SwitchStateAsker sSA = new SwitchStateAsker(user);
        helper.ask(componentsAsker);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Runnable run = new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("Tüm Veriler istendi");
                    helper.ask(componentsAsker);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable run2 = new Runnable() {
            @Override
            public void run() {

                sSA.addTrackingSwitch(components.findSwitch("15Ghd"));
                System.out.println("Anahtar sorgulandı");
                while (flag) {
                    helper.ask(sSA);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        //Thread t1 = new Thread(run);
        Thread t2 = new Thread(run2);

        //t1.start();
        t2.start();
    }

    @Override
    public void doOnAnswer(String key, String message) {

        if (key.equals("anahtar_durumu")){
            System.out.println(message);
            //flag = false;
        }else {

            try {
                components = new Components(new JSONObject(message));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(components.findSwitch("15Ghd").serialize());
        }
    }
}
