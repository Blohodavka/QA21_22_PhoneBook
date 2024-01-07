package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preConditions() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("blohodavka@mail.ru").withPassword("Mama123$"));
        }

      //  app.getHelperContact().provideContacts(); //if list <3 => add 3 contacts

    }

    @Test
    public void removeOneContact(){



        //Assert size list less by one



    }

    @Test
    public void removeAllContacts(){

        //"No contacts here"



    }
}
