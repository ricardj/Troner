package Controlador.ControllerHelpers;

public class RegisterInformationChecker {
    public static final int NO_PROBLEM = 0;
    public static final int PASSWORD_FORMAT_PROBLEM = 1;
    public static final int NAME_FORMAT_PROBLEM = 2;
    public static final int MAIL_FORMAT_PROBLEM = 3;
    public static final int PASSWORD_MATCHING_PROBLEM = 4;

    private static final int PASSWORD_STUFF = 0;
    private static final int NAME_STUFF = 1;
    private static final int MAIL_STUFF = 2;
    private static final int MIN_LONG_NOM = 4;
    private static final int MIN_LONG_PASS = 6;
    private static final int MAX_LONG = 10;

    private Boolean[] thereIsProblem;
    private int[] problem;

    public RegisterInformationChecker(String nom, String correu, String contrasenya, String confirmacioContrasenya) throws LoginException{

        thereIsProblem = new Boolean[3];
        problem = new int[3];
        checker(nom,correu,contrasenya,confirmacioContrasenya);
    }

    private void checker(String nom, String correu, String contrasenya, String confirmacioContrasenya) throws LoginException {
        chekNom(nom);
        checkCorreu(correu);
        checkContrasenya(contrasenya,confirmacioContrasenya);
    }

    private void checkContrasenya(String contrasenya, String confirmacioContrasenya) {
        System.out.println(contrasenya);
        System.out.println(confirmacioContrasenya);

        if(!contrasenya.equals(confirmacioContrasenya)) {
            problem[PASSWORD_STUFF] = PASSWORD_MATCHING_PROBLEM;
            thereIsProblem[PASSWORD_STUFF] = true;
        }else {
            //TODO: comprobar elements de la contrasenya
            problem[PASSWORD_STUFF] = NO_PROBLEM;
            thereIsProblem[PASSWORD_STUFF] = false;
        }
    }

    private void checkCorreu(String correu) {
        boolean valid = validateCorreu( correu );
        //boolean valid = true;
        if (valid){
            problem[MAIL_STUFF] = NO_PROBLEM;
            thereIsProblem[MAIL_STUFF] = false;
        } else {
            problem[MAIL_STUFF] = MAIL_FORMAT_PROBLEM;
            thereIsProblem[MAIL_STUFF] = true;
        }
    }

    private void chekNom(String nom) throws LoginException {
        nomOk( nom );
        boolean valid = validateNom( nom );
        if (valid){
            problem[NAME_STUFF] = NO_PROBLEM;
            thereIsProblem[NAME_STUFF] = false;
        } else {
            problem[NAME_STUFF] = NAME_FORMAT_PROBLEM;
            thereIsProblem[NAME_STUFF] = true;
        }

    }

    public boolean thereIsProblem(){
        for(int i = 0; i<thereIsProblem.length;i++){
            if(thereIsProblem[i]) return true;
        }
        return false;
    }

    public int getProblem() {
        for(int i = 0; i<thereIsProblem.length;i++){
            if(thereIsProblem[i]) return problem[i];
        }
        return NO_PROBLEM;
    }
    public static void nomOk (String nom) throws LoginException{
        if (nom.length() > MAX_LONG){
            throw new LoginException( "Error: nom superior a "+ Integer.toString(MAX_LONG)+" caràcters" );
        }
        if (nom.length() < MIN_LONG_NOM){
            throw new LoginException( "Error: nom inferior a "+ Integer.toString(MIN_LONG_NOM) +" caràcters" );
        }
    }

    public static void contrasenyaOk (String contrasenya) throws LoginException{
        if (contrasenya.length() > MAX_LONG){
            throw new LoginException( "Error: contrasenya superior a "+ Integer.toString(MAX_LONG) +" caràcters" );
        }
        if (contrasenya.length() < MIN_LONG_PASS){
            throw new LoginException( "Error: contrasenya inferior a " + Integer.toString(MIN_LONG_PASS) + " caràcters" );
        }
    }

    public static final String VALID_EMAIL_ADDRESS_REGEX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String VALID_NAME_REGEX = "^(([A-Za-z0-9\\._])(?!.*[\\._].*[\\._].*[\\._])){4,10}$";

    public static boolean validateCorreu(String emailStr) {
        return emailStr.matches( VALID_EMAIL_ADDRESS_REGEX );
    }

    public static boolean validateNom (String userName){
        return userName.matches(VALID_NAME_REGEX);
    }


}
