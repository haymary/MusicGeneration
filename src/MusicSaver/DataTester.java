package MusicSaver;

import java.util.LinkedList;

/**
 * Created by pisatel on 26.07.16.
 */
public class DataTester {
    public static LinkedList<String> getMusicStringTestMethod() {
        LinkedList<String> list = new LinkedList<>();
        list.add("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq");
        list.add("V1 I[Flute] Rw     | Rw     | GmajQQQ  CmajQ");

        return list;
    }
}
