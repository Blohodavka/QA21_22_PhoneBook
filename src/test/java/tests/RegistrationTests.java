package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        //if SingOut present --->logOut
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logOut();
        }
    }

    @Test
    public void registrationSuccess(){

//Random random = new Random();
        //int i = random.nextInt(1000);

        int i = (int)(System.currentTimeMillis()/1000%3600);

        User user = new User().withEmail("mania"+i+"@mail.ru").withPassword("Mama123$");
        logger.info("Tests run with data: --->"+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        logger.info("openRegistrationForm invoked");

        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("fillRegistrationForm invoked");

        app.getHelperUser().submitRegistration();
        logger.info("submitLogin invoked");

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());

    }

    @Test(description = "Bug report #123456, Fixed")
    public void registrationWrongEmail(){

        User user = new User().withEmail("maniamail.ru").withPassword("Mama123$");
        logger.info("Tests run with data: --->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();


        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }

    @Test
    public void registrationWrongPassword(){

        Random random = new Random();
        int i = random.nextInt(1000);

        User user = new User().withEmail("mania"+i+"@mail.ru").withPassword("Mama12");
        logger.info("Tests run with data: --->"+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();


        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }

    @Test
    public void registrationExistsUser() {

        User user = new User().withEmail("blohodavak@mail.ru").withPassword("Masha123$");
        logger.info("Tests run with data: --->"+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();


        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

    }
}
