package ConnectionHelper;

/**
 * Uludağ Üniversitesi Akıllı Ev Projesi
 *
 * Copyright (c) 2017.
 *
 * For more information see the LICENSE file.
 *
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 05.12.2017 - 17:46.
 */

public class AllComponentsAsker implements ITask{
    private final String COMM_KEY = "tum_bilesenler";
    private final String COMM_MESSAGE = "tum_bilesenler"; // Review: Kullanıcı id olabilir.
    private ICommunicationUser user;

    public AllComponentsAsker(ICommunicationUser user){
        this.user = user;
    }

    @Override
    public String[] getAskMessages() {
        return new String[]{COMM_KEY, COMM_MESSAGE};
    }

    @Override
    public void onAnswer(String answer) {
        // TODO: Builder yaz
        user.doOnAnswer("smt", answer);
    }
}
