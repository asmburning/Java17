package feature;

import java.security.*;
import java.security.interfaces.EdECPrivateKey;
import java.security.interfaces.EdECPublicKey;
import java.util.Base64;

public class EdwardsCurveDsa {

    public static void main(String[] args) throws Exception{
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            for (Provider.Service service : provider.getServices()) {
                if (service.getAlgorithm().contains("EdDSA")){
                    System.out.println(provider.getName());
                    System.out.println("---"+service.getAlgorithm());
                }
            }
        }

        //Edwards-Curve Digital Signature Algorithm
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EdDSA");
        keyPairGenerator.initialize(255);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        EdECPublicKey publicKey = (EdECPublicKey)keyPair.getPublic();
        EdECPrivateKey privateKey = (EdECPrivateKey) keyPair.getPrivate();

        String source = "test source";

        Signature signature = Signature.getInstance("Ed25519");
        signature.initSign(privateKey);
        signature.update(source.getBytes());
        byte[] sign = signature.sign();
        System.out.println(Base64.getEncoder().encodeToString(sign));

        signature.initVerify(publicKey);
        signature.update(source.getBytes());
        boolean verify = signature.verify(sign);
        System.out.println(verify);

    }

}
