package ConnectionHelper;

/*
 * Uludağ Üniversitesi Akıllı Ev Projesi
 *
 * Copyright (c) 2017.
 *
 * For more information see the LICENSE file.
 *
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 28.11.2017 - 20:26.
 */


import Data.Switch;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Android cihazın dış dünya ile iletişimini sağlayan ata sınıftır.
 * Sunucu ile socket.io üzerinden bağlantı kurar ve istenilen sorguları
 * yapar.
 *
 * Bu ata sınıf sadece bağlantıyı kurulma ve bağlantının sonlandırılma
 * metodlarını sunar.
 */
public class SocketCommunicationHelper {
    private Socket socket;
    private String connectUrl;
    private long startTime;

    public SocketCommunicationHelper(String url) {
        connectUrl = url;
        openSocket(connectUrl);
    }

    private void openSocket(String url){
        try {
            socket = IO.socket(url);
            socket.connect();
            startTime = (System.currentTimeMillis() / 1000);
        } catch (URISyntaxException e) {
            System.out.println("Hata!!!");
            e.printStackTrace();
        }
    }

    public void closeSocket(){
        socket.off();
        socket.disconnect();
    }

    public void ask(Askable communication){
        // TODO: Static ile bir kere geldiklerinden emin ol
        String[] messages = communication.getAskMessages();
        Emitter.Listener tempListener = args -> {
            communication.onAnswer(args[0].toString());
            socket.off(messages[0]);
        };

        socket.once(messages[0], tempListener); // Review: Gerçekten kaldırılıyor mu kontrol et."

        socket.emit(messages[0], messages[1]);
    }

    public void send(String key, String value){
        socket.emit(key, value);
    }

    public void send(String key, JSONObject value){
        socket.emit(key, value);
    }

    public void sendSwitchState(Switch aSwitch) {
        socket.emit("anahtar_durumu_degistir", aSwitch.serialize().toString());
    }
}