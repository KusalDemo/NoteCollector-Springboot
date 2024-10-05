package lk.ijse.NoteCollector_Springboot.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {

    //Separation of concern (SOC)
    public static String generateNoteId() {
        return "NOTE-" + UUID.randomUUID();
    }
    public static String generateUserId() {
        return "USER-" + UUID.randomUUID();
    }
    public static String generateProfilePicToBase64(byte[] profilePic) {
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
