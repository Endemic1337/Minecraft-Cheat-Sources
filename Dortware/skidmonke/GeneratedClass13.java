package skidmonke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;

public class GeneratedClass13 {

    private static final String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
    public static int lastSeed;

    public static void main(String[] args) throws Exception {
        new GeneratedClass13().getAuthResponse("javax.net.ssl.HttpsURLConnection", "java.net.URL", "java.io.InputStream",
                "java.lang.String",
                "https://intent.store/product/23/whitelist?dat=", getKey(),
                "LYGV6ILURVT7mi8V",
                "BmfrwoKUyN5wBAMc",
                "qESR7lpRWInukfSP",
                "WzhaT14Vh5zZq8GN");
    }

    public static String getKey() throws Exception {
        String hwid = HWID();

        String encryptedHWID = AES.encrypt("LYGV6ILURVT7mi8V", "WzhaT14Vh5zZq8GN", hwid);
        String encryptedSeed = AES.encrypt("BmfrwoKUyN5wBAMc", "WzhaT14Vh5zZq8GN", java.lang.String.valueOf(lastSeed = new Random().nextInt(100000)));

        return Indirection.enbxz().encodeToString(AES.encrypt("qESR7lpRWInukfSP", "WzhaT14Vh5zZq8GN", encryptedHWID + "|" + encryptedSeed).getBytes());
    }

    public static String HWID() throws Exception {
        return textToSHA1(Indirection.acvcxv("PROCESSOR_IDENTIFIER") + Indirection.acvcxv("COMPUTERNAME") + Indirection.becv("user.name"));
    }

    static String textToSHA1(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash;
        md.update(text.getBytes(StandardCharsets.ISO_8859_1), 0, text.length());
        sha1hash = md.digest();
        return bytesToHex(sha1hash);
    }

    static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = "0123456789abcdef".toCharArray()[v >>> 4];
            hexChars[j * 2 + 1] = "0123456789abcdef".toCharArray()[v & 0x0F];
        }
        return new String(hexChars);
    }

    // GENERATED BY FARDFUSCATOR v3
    // User: DortDev
    // License Type: Developer
    // Warning: Method wasn't fully converted.
    public void getAuthResponse(Object... args) throws Exception {
        /*
         Call via:
         getAuthResponse("javax.net.ssl.HttpsURLConnection", "java.net.URL", "java.io.InputStream",
         "java.lang.String",
         "https://intent.store/product/23/whitelist?dat=", getKey(),
         "LYGV6ILURVT7mi8V",
         "BmfrwoKUyN5wBAMc",
         "qESR7lpRWInukfSP",
         "WzhaT14Vh5zZq8GN");
        */
        //P1 (Class Names)
        String httpsType = args[0].toString();
        String urlType = args[1].toString();
        String streamType = args[2].toString();
        String stringType = args[3].toString();

        //P2 (Classes)
        Class httpsClient = Class.forName(httpsType);
        Class url = Class.forName(urlType);
        Class string = Class.forName(stringType);
        Class bufferedReader = Class.forName("java.io.BufferedReader");
        Class isReader = Class.forName("java.io.InputStreamReader");

        //P3 (Constructors)
        Constructor urlConstructor = url.getConstructor(string);
        Constructor buffered = bufferedReader.getConstructor(Class.forName("java.io.Reader"));

        //P4 (Instances)
        Object urlInstance = urlConstructor.newInstance(args[4].toString() + args[5].toString());

        Object httpsClientInstance = null;

        for (Method method : urlInstance.getClass().getMethods())
            if (method.getParameterCount() == 0 && method.getReturnType() == Class.forName("java.net.URLConnection"))
                httpsClientInstance = method.invoke(urlInstance);

        Object streamInstance = null;

        //P5 (Loop methods)
        for (Method method : httpsClientInstance.getClass().getMethods())
            if (method.getName().equals("getInputStream") && method.getParameterCount() == 0)
                streamInstance = method.invoke(httpsClientInstance);
        Constructor isReaderConstructor = isReader.getConstructor(Class.forName("java.io.InputStream"));

        Object buffered2 = buffered.newInstance(isReaderConstructor.newInstance(streamInstance));

        Object p = null;
        for (Method method : buffered2.getClass().getMethods()) {
            if (method.getName().equals("readLine") && method.getParameterCount() == 0) {
                p = method.invoke(buffered2);
            }
        }
        Object a = null;
        for (Method method : string.getMethods()) {
            if (method.getName().equals("getBytes"))
                a = method.invoke(p);
        }

        String decodedResponse = new String(Objects.requireNonNull(Indirection.afdd(a)));
        String decryptedResponse = AES.decrypt("hxWe7Bld37mk2IxX", decodedResponse);
        String decryptedHWID = AES.decrypt("ZjW5imogbFEwBHWd", decryptedResponse.split("\\|")[0]);
        String decryptedSeed = AES.decrypt("wmB84GAGlRuzAklm", decryptedResponse.split("\\|")[1]);

        //P6 (Unknown, Translated anyway)

        String key1 = args[6].toString();
        String key2 = args[7].toString();
        String key3 = args[8].toString();
        String iv = args[9].toString();

        //Unable to convert automatically
        String currentLine;
        String accessCode;
        throw new Exception("Was unable to fully convert method to reflection, please manually finish.");
    }

}
