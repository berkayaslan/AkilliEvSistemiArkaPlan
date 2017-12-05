package ConnectionHelper;

/**
 * Uludağ Üniversitesi Akıllı Ev Projesi
 * <p>
 * Copyright (c) 2017.
 * <p>
 * For more information see the LICENSE file.
 * <p>
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 30.11.2017 - 04:40.
 */
interface ITask {
    String[] getAskMessages();
    void onAnswer(String answer); // Review: Private olursa çok daha iyi olur.
}
