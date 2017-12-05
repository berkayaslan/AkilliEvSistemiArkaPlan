package ConnectionHelper;

import Data.Switch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class SwitchStateAsker implements ITask{
    private final String COMM_KEY = "anahtar_durumu";
    private String COMM_MESSAGE;
    private JSONArray jtrackingSwitches;
    private LinkedList<Switch> trackingSwitches;
    private ICommunicationUser communicationUser;

    private SwitchStateAsker(){
        this.jtrackingSwitches = new JSONArray();
        this.trackingSwitches = new LinkedList<>();
    }

    public SwitchStateAsker(ICommunicationUser user){
        this();
        this.communicationUser = user;

    }

    public SwitchStateAsker(Switch[] switches, ICommunicationUser user){
        this(user);
        addTrackingSwitches(switches);
    }

    @Override
    public String[] getAskMessages() {
        COMM_MESSAGE = jtrackingSwitches.toString();
        return new String[]{this.COMM_KEY, this.COMM_MESSAGE};
    }

    @Override
    public void onAnswer(String answer) {
        LinkedList<String> changedSwitchesIds = new LinkedList<>();
        try {
            JSONArray incomingSwitches = new JSONArray(answer);
            int size = incomingSwitches.length();

            for (int i=0;i<size;i++){
                JSONObject incomingSwitch = incomingSwitches.getJSONObject(i);
                boolean isChanged = this.isSwitchChanged(incomingSwitch.getString("id"),
                        incomingSwitch.getBoolean("durum"));
                if (isChanged){
                    changedSwitchesIds.add(incomingSwitch.getString("id"));
                }
            }

            communicationUser.doOnAnswer("Durum Değişimi", changedSwitchesIds.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addTrackingSwitch(Switch aSwitch){
        trackingSwitches.add(aSwitch);
        jtrackingSwitches.put(aSwitch.getElementId());
    }

    public void addTrackingSwitches(Switch[] switches){
        for (Switch aSwitch: switches){
            trackingSwitches.add(aSwitch);
            jtrackingSwitches.put(aSwitch.getElementId());
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
