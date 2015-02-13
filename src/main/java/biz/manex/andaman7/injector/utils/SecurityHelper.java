package biz.manex.andaman7.injector.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A utility class to deal with security and cryptography.
 *
 * @author Pierre-Yves Derbaix (pierreyves.derbaix@gmail.com)<br/>
 * Copyright A7 Software (http://a7-software.com/)<br/>
 * Date: 19/01/2015.<br/>
 */
public class SecurityHelper {

    /**
     * The hexadecimal base number.
     */
    private static final int HEX_BASE = 16;

    /**
     * Returns a hexadecimal digest of the data based on the specified
     * algorithm.
     *
     * @param data the data to get the hash from
     * @param algo the hashing algorithm
     * @return the hexadecimal hash string
     * @throws NoSuchAlgorithmException if the algorithm is not known
     */
    public static String getHexDigest(String data, String algo) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(algo);
        md.update(data.getBytes());
        return new BigInteger(1, md.digest()).toString(HEX_BASE);
    }

    /**
     * Returns a hexadecimal SHA-256 digest of the specified data.
     *
     * @param data the data to get the hash from
     * @return the SHA-256 hexadecimal hash string
     */
    public static String getSHA256Digest(String data) {

        String hash = null;

        try {
            hash = getHexDigest(data, "SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return hash;
    }
}
