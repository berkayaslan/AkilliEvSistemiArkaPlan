package Test;

import ConnectionHelper.*;
import Data.Switch;

import java.util.Objects;

/**
 * Uludağ Üniversitesi Akıllı Ev Projesi
 * <p>
 * Copyright (c) 2017.
 * <p>
 * For more information see the LICENSE file.
 * <p>
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 28.11.2017 - 20:23.
 */
public class TestConnection implements ICommunicationUser{
    public static void main(String[] args){
        System.out.println("Hello from TestConnection");

        SocketCommunicationHelper connection = new SocketCommunicationHelper("https://akilli-ev.herokuapp.com/");
        ICommunicationUser user = new TestConnection();
/*

        EmergencyAsker eA = new EmergencyAsker(user);
        SwitchStateAsker sSa = new SwitchStateAsker(user);
        AllComponentsAsker aca = new AllComponentsAsker(user);
        Switch s = new Switch("15Ghd");
        Switch s2 = new Switch("gHr84d");
        sSa.addTrackingSwitch(s);
        // sSa.addTrackingSwitch(s2);
        int i = 0;
        s2.setState(false);
        connection.sendSwitchState(s2);

        while (i<3){
            try {
                Thread.sleep(1500);
                // connection.ask(eA);
                connection.ask(sSa);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

        try {
            connection.sendSwitchState(s);
            Thread.sleep(1900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        i=0;

        while (i<3){
            try {
                Thread.sleep(1500);
                //connection.ask(eA);
                connection.ask(sSa);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i ++;
        }

        connection.ask(aca);
        // connection.ask(eA); // TODO: Cevap gelmeden kapatm
    */
    /*
        ICommunicationUser user = new TestConnection();
        SensorStateAsker sSA = new SensorStateAsker(user);
        Sensor s1 = new TemperatureSensor("sensor_1");
        sSA.addSensor(s1, 60);
        connection.ask(sSA);

        Switch s = new Switch("15Ghd");
        SwitchStateAsker ssA = new SwitchStateAsker(user);
        ssA.addTrackingSwitch(s);

        connection.ask(ssA);

        for (int i = 0; i<2; i++) {
            if (i%2 == 0)
                s.setState(true);
            else
                s.setState(false);

            connection.sendSwitchState(s);
            //System.out.println("Gönderildi");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        connection.ask(ssA);
        EmergencyAsker eA = new EmergencyAsker(user);
        while (true) {

            connection.ask(eA);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/

        AllComponentsAsker aCA = new AllComponentsAsker(user);
        connection.ask(aCA);

        Switch s = new Switch("15Ghd", true);
        connection.sendSwitchState(s);
        System.out.println("gönderildi");
    }




    @Override
    public void doOnAnswer(String title, String message) {

        if (!Objects.equals(message, "[]")){
            System.out.println(title);
            System.out.println(message);
        }
    }
}
