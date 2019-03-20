package ycy.ccyy.yueyavillage.util;

import java.nio.charset.Charset;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class DESedeUtil {
    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "DESede";

    public static final String PADDING = "DESede/ECB/PKCS5Padding";

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return KEY 密钥
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        Key k = toKey(HexStr2Byte(key));
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.DECRYPT_MODE, k);
        byte[] cip = cipher.doFinal(HexStr2Byte(data));
        return new String(cip, "UTF-8");
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        Key k = toKey(HexStr2Byte(key));
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        byte[] orig = cipher.doFinal(data.getBytes(Charset.forName("UTF-8")));
        return byte2HexStr(orig);
    }

    /**
     * 字节转换成16进制表示字符串 用空格
     *
     * @param b
     * @return
     */
    private static String byte2HexStr(byte[] b) {
        String hs = "";
        String stmp;
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * 将十六进制转换为字节数组
     *
     * @param hexString
     * @return
     */
    private static byte[] HexStr2Byte(String hexString) {
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        byte high;//
        byte low;//
        String hexStr = "0123456789ABCDEF";

        for (int i = 0; i < len; i++) {
            //右移四位得到高位
            high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
            bytes[i] = (byte) (high | low);//高地位做或运算
        }
        return bytes;
    }
}
