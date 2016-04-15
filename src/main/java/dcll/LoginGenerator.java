package dcll;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Classe representant un generateur de login.
 */
public class LoginGenerator {
    /**
     * loginService.
     */
    private LoginService loginService;
    /**
     * NUMEROPRENOM.
     */
    static final int NUMEROPRENOM = 1;
    /**
     * NUMERONOM.
     */
    static final int NUMERONOM = 3;
    /**
     * NUMEROCOUT.
     */
    static final int NUMEROCOUT = 2;
    /**
     * Construit un login generator.
     * @param loginServiceT le service de login
     */
    public LoginGenerator(final LoginService loginServiceT) {
        this.loginService = loginServiceT;
    }

    /**
     * Genere un login unique a partir d'un nom et d'un prenom
     * en prenant la premiere lettre du prenom,
     * concatenee avec les 3 premieres lettres du nom, le tout
     * mis en lettres capitales et desaccentue.
     * Le login genere doit etre unique par rapport a la liste
     * des logins existants. En cas de doublon,
     * on incremente le doublon d'un indice. Ci dessous des
     * exemples :
     * <ul>
     *     <li>Paul Dupond -> PDUP </li>
     *     <li>Pierre Dupard -> PDUP1</li>
     *     <li>Jacques Durand -> JDUR</li>
     *     <li>Lionel R&eacute;gal -> LREG</li>
     * </ul>
     * @param nom le nom
     * @param prenom le prenom
     * @return le login genere
     */
    public final String generateLoginForNomAndPrenom(final String nom,
                                                     final String prenom) {
        String p = deAccent(prenom.substring(0, NUMEROPRENOM).toUpperCase());
        String n = null;
        if (nom.length() < NUMERONOM) {
            n = deAccent(nom.substring(0, NUMEROCOUT).toUpperCase());
        } else {
            n = deAccent(nom.substring(0, NUMERONOM).toUpperCase());
        }
        String login = p + n;
        if (loginService.loginExists(login)) {
            login = login + "1";
            if (loginService.loginExists(login)) {
                login = p + n + "2";
            }
        }
        loginService.addLogin(login);
        return login;
    }

    /**
     * Supprime les accents d'une chaine de caractere.
     *
     * @param str la chaine de caractere
     * @return la chaine de caractere sans accents
     */
    private String deAccent(final String str) {
        String nfdNormalizedString = Normalizer.
                    normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

}
