package Test;

import ConnectionHelper.SocketCommunicationHelper;
import ConnectionHelper.EmergencyAsker;
import ConnectionHelper.ICommunicationUser;
import ConnectionHelper.SwitchStateAsker;
import Data.Switch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        Switch s = new Switch("2D3dgr");
        Switch s2 = new Switch("21Khgr");
        sSa.addTrackingSwitch(s);
        sSa.addTrackingSwitch(s2);
        int i = 0;

        while (i<3){
            try {
                Thread.sleep(1600);
                connection.ask(eA);
                connection.ask(sSa);

                i ++;
                System.out.println("Mesaj sayısı + " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        connection.closeSocket();

    }

    @Override
    public void doOnAnswer(String title, String message) {
        System.out.println(title);
    }
}
