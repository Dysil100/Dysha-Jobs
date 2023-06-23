package duo.cmr.dysha.boundedContexts.routen;

public class Routen {

    public static final String  ADMINROUTE = "/adminindex";
    public static final String  LEADERROUTE = "/leaderindex";
    public static final String  ANALYSE = "/analyse";

    public static final String  AVIS = "/avis";
    public static final String  DELETEAVIS = "/avis/delete/{id}";
    public static final String  AVISLISTE = "/avisliste";

    public static final String  PASSWORDEINGABE = "/passwordeingabe";
    public static final String  MAILEINGABE = "/maileingabe";

    public static final String  REGISTRATION = "/registration";

    public static final String  FINANCESUEBERSICHT = "/financesuebersicht";
    public static final String  MODIFIERFINANCES = "/finances/modifier/{id}";
    public static final String  MODIFIERFINANCESPOST = "/financesmodifier";
    public static final String  FINANCESDELETE = "/finances/delete/{id}";

    public static final String  CONTACTS = "/contacts";

    public static final String PROJECTVERLAUFUEBERSICHT = "/project/verlauf/{projectName}";
    public static final String VERLAUFMODIFIER = "/project/verlauf/modifier/{id}";
    public static final String PROJECTVERLAUFDELETE = "/project/verlauf/delete/{id}";

    public static final String  TELECHARGER = "telecharger";
    public static final String ADDSTANDARD = "/addsandards";

    public static final String EMPTYROUTE = "";

    public static final String USERINDEX = "/index";

    public static final String USERLISTE = "/userliste";

    public static final String NOTIFICATIONS = "/notifications";

    public static final String CONFIRMPASSWORDRECOVER = "/delete/confirm";
    public static final String CONFRIMREGISTRATION = "/registration/confirm";

    public static final String NEWPRODUCT = "/newproduct";
    public static final String PRODUCTLISTE = "/productliste";
    public static final String PRODUCT_DELETE_ID = "/product/delete/{id}";
    public static final String PRODUCTSEARCH = "/productliste/search";
}
