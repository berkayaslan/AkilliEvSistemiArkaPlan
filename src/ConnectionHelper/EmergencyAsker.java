package ConnectionHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Uludağ Üniversitesi Akıllı Ev Projesi
 * <p>
 * Copyright (c) 2017.
 * <p>
 * For more information see the LICENSE file.
 * <p>
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 30.11.2017 - 05:43.
 */
public class EmergencyAsker implements ITask {
    private final String COMM_KEY = "acil_durum";
    private final String COMM_MESSAGE = "acil_durum";
    private ICommunicationUser user;

    public EmergencyAsker(ICommunicationUser user){
        this.user = user;
    }

    @Override
    public String[] getAskMessages() {
        return new String[]{COMM_KEY, COMM_MESSAGE};
    }

    @Override
    public void onAnswer(String answer) {
        if (answer.equals("")){
            // Yapılacak bir şey yok
            System.out.println("Acil bir durum yok.");
        }else{
            String[] ans = parseAnswer(answer);
            assert ans != null; // TODO: Exception kullanılmalı.
            user.doOnAnswer(ans[0], ans[1]);
        }
    }

    private String[] parseAnswer(String sAnswer){
        String reason;
        String location;
        try {
            JSONObject jAnswer = new JSONObject(sAnswer);
            reason = (String) jAnswer.get("sebep");
            location = (String) jAnswer.get("yer");

            // TODO: Sebep ve yer bilgisini Enumlar ile title ve message bilgisine çevir.

            return new String[]{reason, location};
        }
        catch (JSONException e) {e.printStackTrace();}
        return null;
    }
}
