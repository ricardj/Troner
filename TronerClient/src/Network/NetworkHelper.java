package Network;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class NetworkHelper {

    private NetworkHelper(){

    }

    public static boolean checkIp(String ip){
        boolean resultat = false;

        if (ip == null || ip.isEmpty()) {
            resultat = false;
        }else{
            if(ip.equals("localhost")) {
                resultat = true;
            }else {

                ip = ip.trim();
                if ((ip.length() < 6) & (ip.length() > 15)) resultat = false;

                try {

                    Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
                    Matcher matcher = pattern.matcher(ip);
                    resultat = matcher.matches();

                } catch (PatternSyntaxException ex) {
                    resultat = false;
                }
            }
        }

        return resultat;
    }

    public static boolean checkPort(String port){
        boolean resultat = false;

        try {
            int a =Integer.parseInt(port);
            if(a > 1024){
                resultat = true;
            }
        }catch (Exception e){
            resultat = false;
        }

        return resultat;
    }
}
