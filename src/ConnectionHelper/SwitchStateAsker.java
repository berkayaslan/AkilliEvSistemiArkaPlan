package ConnectionHelper;

import Data.Switch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Uludağ Üniversitesi Akıllı Ev Projesi
 * <p>
 * Copyright (c) 2017.
 * <p>
 * For more information see the LICENSE file.
 * <p>
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 02.12.2017 - 10:24.
 */
public class SwitchStateAsker implements Askable {
    private final String COMM_KEY = "anahtar_durumu";
    private String COMM_MESSAGE;
    private JSONArray jtrackingSwitches;
    private LinkedList<Switch> trackingSwitches;
    private ICommunicationUser communicationUser;
    private JSONObject JSwitches;

    private SwitchStateAsker(){

        this.jtrackingSwitches = new JSONArray();
        this.trackingSwitches = new LinkedList<>();
        this.JSwitches = new JSONObject();
        try {
            this.JSwitches.put("anahtarlar", jtrackingSwitches);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public SwitchStateAsker(ICommunicationUser user){
        this();
        this.communicationUser = user;

    }

    public SwitchStateAsker(LinkedList<Switch> switches, ICommunicationUser user){
        this(user);
        addTrackingSwitches(switches);
    }

    @Override
    public String[] getAskMessages() {
        jtrackingSwitches = new JSONArray();
        for (Switch aSwitch :trackingSwitches){
            jtrackingSwitches.put(aSwitch.serialize());
        }

        try {
            this.JSwitches.put("anahtarlar", jtrackingSwitches);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        COMM_MESSAGE = JSwitches.toString();
        return new String[]{this.COMM_KEY, this.COMM_MESSAGE};
    }

    @Override
    public void onAnswer(String answer) {
        LinkedList<String> changedSwitchesIds = new LinkedList<>();
        try {
            JSONObject JAnswer = new JSONObject(answer);
            JSONArray incomingSwitches = new JSONArray(JAnswer.get("anahtarlar").toString());
            int size = incomingSwitches.length();

            for (int i=0;i<size;i++){
                JSONObject incomingSwitch = incomingSwitches.getJSONObject(i);
                boolean isChanged = this.isSwitchChanged(incomingSwitch.getString("id"),
                        incomingSwitch.getBoolean("durum"));
                if (isChanged){
                    changedSwitchesIds.add(incomingSwitch.getString("id"));
                }
            }

            if (changedSwitchesIds.size()>0)
                communicationUser.doOnAnswer(COMM_KEY, changedSwitchesIds.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public  Switch[] parseChangedSwitches(String changedSwitches){
        String temp = changedSwitches.replace("[", "");
        temp = temp.replace("]", "");
        String[] changedSwitchId = temp.split(",");

        Switch[] changedSwitchesArr = new Switch[changedSwitchId.length];
        for (int i=0; i<changedSwitchId.length;i++){
            changedSwitchesArr[i] = this.findSwitch(changedSwitchId[i]);
        }

        return changedSwitchesArr;
    }

    public void addTrackingSwitch(Switch aSwitch){
        if (findSwitch(aSwitch.getElementId())==null)
            trackingSwitches.add(aSwitch);
        // jtrackingSwitches.put(aSwitch.serialize());
    }

    public void addTrackingSwitches(LinkedList<Switch> switches){
        for (Switch aSwitch:switches) {
            if (findSwitch(aSwitch.getElementId()) == null)
                trackingSwitches.add(aSwitch);
        }
    }

    private Switch findSwitch(String id){
        for (Switch aSwitch: this.trackingSwitches){
            if (aSwitch.getElementId().equals(id)){
                return aSwitch;
            }
        }
        return null;
    }

    private boolean isSwitchChanged(String id, boolean state){
        Switch sw = this.findSwitch(id);

        assert sw != null;
        if (sw.getState() == state) return false;
        else return true;
    }
}
