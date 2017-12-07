package Test;

import ConnectionHelper.*;
import Data.Switch;

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

                System.out.println("Mesaj sayısı + " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

        try {
            connection.sendSwitchState(s);
            System.out.println("Anahtar durumu değişimi sunucuya yollandı!!");
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

                System.out.println("Mesaj sayısı + " + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("hata");
            }
            i ++;
        }

        connection.ask(aca);
        // connection.ask(eA); // TODO: Cevap gelmeden kapatm
    }

    @Override
    public void doOnAnswer(String title, String message) {

        if (message != "[]"){
            System.out.println(title);
            System.out.println(message);
        }
    }
}
