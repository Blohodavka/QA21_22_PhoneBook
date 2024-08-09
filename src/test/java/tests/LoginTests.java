package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    //Logger logger = LoggerFactory.getLogger(LoginTests.class);
    @BeforeMethod (alwaysRun = true)
    public void preCondition(){
        //if SingOut present --->logOut
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logOut();
            logger.info("Before method finish logout");
        }
    }


    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){

        logger.info("Start test with name `loginSuccess`");
        logger.info("Test data --> email:`blohodavka@mail.ru` & password: `Mama123`");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("blohodavka@mail.ru","Mama123$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert check is Element button 'Sing Out' present");

//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();

    }
    @Test(dataProvider = "loginModels",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){

        logger.info("Test data ---> " +user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("blohodavka@mail.ru","Mama123$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sing Out' present");

//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();

    }

    @Test(groups = {"smoke"})
    public void loginWrongEmail(){

        logger.info("Test data --> email:`blohodavkamail.ru` & password: `Mama123$`");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("blohodavkamail.ru","Mama123$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert check is alert with error text 'Wrong email or password'");


    }
    @Test
    public void loginWrongPassword(){

        logger.info("Test data --> email:`blohodavka@mail.ru` & password: `Mama12345$`");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("blohodavka@mail.ru","Mama1");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert check is alert with error text 'Wrong email or password'");


    } @Test
    public void loginUnregisteredUser(){

        logger.info("Test data --> email:`luck@mail.ru` & password: `Luck123$`");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("luck@mail.ru","Luck123$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert check is alert with error text 'Wrong email or password'");

    }

}