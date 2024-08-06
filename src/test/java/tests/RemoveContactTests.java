package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preConditions() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("blohodavka@mail.ru").withPassword("Mama123$"));
        }

       //if list <3 => add 3 contacts
        app.getHelperContact().provideContacts();

    }

    @Test
    public void removeOneContact(){

        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);

        //Assert size list less by one

    }

    @Test
    public void removeAllContacts() {

        app.getHelperContact().removeAllContacts();

        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        //"No Contacts here!"
    }
}
