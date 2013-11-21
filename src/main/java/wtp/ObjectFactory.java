
package wtp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wtp package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ResetPasswordAuthToken_QNAME = new QName("http://wtp", "authToken");
    private final static QName _ResetPasswordUserId_QNAME = new QName("http://wtp", "userId");
    private final static QName _LogoutResponseReturn_QNAME = new QName("http://wtp", "return");
    private final static QName _RegisterUserArgs3_QNAME = new QName("http://wtp", "args3");
    private final static QName _RegisterUserArgs4_QNAME = new QName("http://wtp", "args4");
    private final static QName _RegisterUserArgs1_QNAME = new QName("http://wtp", "args1");
    private final static QName _RegisterUserArgs2_QNAME = new QName("http://wtp", "args2");
    private final static QName _RegisterUserArgs0_QNAME = new QName("http://wtp", "args0");
    private final static QName _RegisterUserArgs5_QNAME = new QName("http://wtp", "args5");
    private final static QName _RegisterUserArgs6_QNAME = new QName("http://wtp", "args6");
    private final static QName _ActivateUserUsername_QNAME = new QName("http://wtp", "username");
    private final static QName _ChangePasswordOldPassword_QNAME = new QName("http://wtp", "oldPassword");
    private final static QName _ChangePasswordNewPassword_QNAME = new QName("http://wtp", "newPassword");
    private final static QName _LoginPassword_QNAME = new QName("http://wtp", "password");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wtp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IsTokenValidResponse }
     * 
     */
    public IsTokenValidResponse createIsTokenValidResponse() {
        return new IsTokenValidResponse();
    }

    /**
     * Create an instance of {@link ActivateUserResponse }
     * 
     */
    public ActivateUserResponse createActivateUserResponse() {
        return new ActivateUserResponse();
    }

    /**
     * Create an instance of {@link ResetPassword }
     * 
     */
    public ResetPassword createResetPassword() {
        return new ResetPassword();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link EnableAccountResponse }
     * 
     */
    public EnableAccountResponse createEnableAccountResponse() {
        return new EnableAccountResponse();
    }

    /**
     * Create an instance of {@link IsTokenValid }
     * 
     */
    public IsTokenValid createIsTokenValid() {
        return new IsTokenValid();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link ActivateUser }
     * 
     */
    public ActivateUser createActivateUser() {
        return new ActivateUser();
    }

    /**
     * Create an instance of {@link ResetPasswordResponse }
     * 
     */
    public ResetPasswordResponse createResetPasswordResponse() {
        return new ResetPasswordResponse();
    }

    /**
     * Create an instance of {@link DisableAccount }
     * 
     */
    public DisableAccount createDisableAccount() {
        return new DisableAccount();
    }

    /**
     * Create an instance of {@link ChangePasswordResponse }
     * 
     */
    public ChangePasswordResponse createChangePasswordResponse() {
        return new ChangePasswordResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link DisableAccountResponse }
     * 
     */
    public DisableAccountResponse createDisableAccountResponse() {
        return new DisableAccountResponse();
    }

    /**
     * Create an instance of {@link ChangePassword }
     * 
     */
    public ChangePassword createChangePassword() {
        return new ChangePassword();
    }

    /**
     * Create an instance of {@link EnableAccount }
     * 
     */
    public EnableAccount createEnableAccount() {
        return new EnableAccount();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "authToken", scope = ResetPassword.class)
    public JAXBElement<String> createResetPasswordAuthToken(String value) {
        return new JAXBElement<String>(_ResetPasswordAuthToken_QNAME, String.class, ResetPassword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "userId", scope = ResetPassword.class)
    public JAXBElement<String> createResetPasswordUserId(String value) {
        return new JAXBElement<String>(_ResetPasswordUserId_QNAME, String.class, ResetPassword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = LogoutResponse.class)
    public JAXBElement<String> createLogoutResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, LogoutResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "args3", scope = RegisterUser.class)
    public JAXBElement<String> createRegisterUserArgs3(String value) {
        return new JAXBElement<String>(_RegisterUserArgs3_QNAME, String.class, RegisterUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "args4", scope = RegisterUser.class)
    public JAXBElement<String> createRegisterUserArgs4(String value) {
        return new JAXBElement<String>(_RegisterUserArgs4_QNAME, String.class, RegisterUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "args1", scope = RegisterUser.class)
    public JAXBElement<String> createRegisterUserArgs1(String value) {
        return new JAXBElement<String>(_RegisterUserArgs1_QNAME, String.class, RegisterUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "args2", scope = RegisterUser.class)
    public JAXBElement<String> createRegisterUserArgs2(String value) {
        return new JAXBElement<String>(_RegisterUserArgs2_QNAME, String.class, RegisterUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "args0", scope = RegisterUser.class)
    public JAXBElement<String> createRegisterUserArgs0(String value) {
        return new JAXBElement<String>(_RegisterUserArgs0_QNAME, String.class, RegisterUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "args5", scope = RegisterUser.class)
    public JAXBElement<String> createRegisterUserArgs5(String value) {
        return new JAXBElement<String>(_RegisterUserArgs5_QNAME, String.class, RegisterUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "args6", scope = RegisterUser.class)
    public JAXBElement<String> createRegisterUserArgs6(String value) {
        return new JAXBElement<String>(_RegisterUserArgs6_QNAME, String.class, RegisterUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = IsTokenValidResponse.class)
    public JAXBElement<String> createIsTokenValidResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, IsTokenValidResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "authToken", scope = IsTokenValid.class)
    public JAXBElement<String> createIsTokenValidAuthToken(String value) {
        return new JAXBElement<String>(_ResetPasswordAuthToken_QNAME, String.class, IsTokenValid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = DisableAccountResponse.class)
    public JAXBElement<String> createDisableAccountResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, DisableAccountResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "username", scope = ActivateUser.class)
    public JAXBElement<String> createActivateUserUsername(String value) {
        return new JAXBElement<String>(_ActivateUserUsername_QNAME, String.class, ActivateUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = ActivateUserResponse.class)
    public JAXBElement<String> createActivateUserResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, ActivateUserResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = RegisterUserResponse.class)
    public JAXBElement<String> createRegisterUserResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, RegisterUserResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "authToken", scope = Logout.class)
    public JAXBElement<String> createLogoutAuthToken(String value) {
        return new JAXBElement<String>(_ResetPasswordAuthToken_QNAME, String.class, Logout.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = LoginResponse.class)
    public JAXBElement<String> createLoginResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, LoginResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = ResetPasswordResponse.class)
    public JAXBElement<String> createResetPasswordResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, ResetPasswordResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = ChangePasswordResponse.class)
    public JAXBElement<String> createChangePasswordResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, ChangePasswordResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "authToken", scope = DisableAccount.class)
    public JAXBElement<String> createDisableAccountAuthToken(String value) {
        return new JAXBElement<String>(_ResetPasswordAuthToken_QNAME, String.class, DisableAccount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "userId", scope = DisableAccount.class)
    public JAXBElement<String> createDisableAccountUserId(String value) {
        return new JAXBElement<String>(_ResetPasswordUserId_QNAME, String.class, DisableAccount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "authToken", scope = EnableAccount.class)
    public JAXBElement<String> createEnableAccountAuthToken(String value) {
        return new JAXBElement<String>(_ResetPasswordAuthToken_QNAME, String.class, EnableAccount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "userId", scope = EnableAccount.class)
    public JAXBElement<String> createEnableAccountUserId(String value) {
        return new JAXBElement<String>(_ResetPasswordUserId_QNAME, String.class, EnableAccount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "authToken", scope = ChangePassword.class)
    public JAXBElement<String> createChangePasswordAuthToken(String value) {
        return new JAXBElement<String>(_ResetPasswordAuthToken_QNAME, String.class, ChangePassword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "oldPassword", scope = ChangePassword.class)
    public JAXBElement<String> createChangePasswordOldPassword(String value) {
        return new JAXBElement<String>(_ChangePasswordOldPassword_QNAME, String.class, ChangePassword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "newPassword", scope = ChangePassword.class)
    public JAXBElement<String> createChangePasswordNewPassword(String value) {
        return new JAXBElement<String>(_ChangePasswordNewPassword_QNAME, String.class, ChangePassword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "userId", scope = ChangePassword.class)
    public JAXBElement<String> createChangePasswordUserId(String value) {
        return new JAXBElement<String>(_ResetPasswordUserId_QNAME, String.class, ChangePassword.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "password", scope = Login.class)
    public JAXBElement<String> createLoginPassword(String value) {
        return new JAXBElement<String>(_LoginPassword_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "username", scope = Login.class)
    public JAXBElement<String> createLoginUsername(String value) {
        return new JAXBElement<String>(_ActivateUserUsername_QNAME, String.class, Login.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wtp", name = "return", scope = EnableAccountResponse.class)
    public JAXBElement<String> createEnableAccountResponseReturn(String value) {
        return new JAXBElement<String>(_LogoutResponseReturn_QNAME, String.class, EnableAccountResponse.class, value);
    }

}
