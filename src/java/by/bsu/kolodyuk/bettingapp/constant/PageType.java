
package by.bsu.kolodyuk.bettingapp.constant;


public enum PageType {
    
    INDEX("index"), ADMIN_ACCOUNT("adminaccount"), ACCOUNT("account"), ERROR("error"), LOGIN("login"), REGISTRATION("registration"),
    ADMIN_ADD_BETS("adminaddbets"), ALL_BETS("allbets"), ADMIN_SET_RESULTS("adminsetresults"), TRANSACTIONS("transactions"), 
    MY_BETS("mybets");
    
    private String pageName;

    private PageType(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }
    
}
