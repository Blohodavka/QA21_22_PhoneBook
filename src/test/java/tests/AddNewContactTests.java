package tests;

import models.Contact;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

@BeforeClass
    public void preConditions(){
    if(!app.getHelperUser().isLogged()){
        app.getHelperUser().login(new User().withEmail("blohodavka@mail.ru").withPassword("Mama123$"));
    }

}

@Test
    public void addContactSuccessAllFields(){

    int i = (int)(System.currentTimeMillis()/1000%3600);

    Contact contact = Contact.builder()
            .name("Ivan")
            .lastName("Ivanov")
            .email("ivanov"+i+"@gmail.ru")
            .address("Russia")
            .phone("0125485"+i)
            .description("the best")
            .build();

    }
    @Test
    public void addContactSuccessAllReqFields(){

        int i = (int)(System.currentTimeMillis()/1000%3600);
        Contact contact = Contact.builder()
                .name("Sasha")
                .lastName("Sashov")
                .email("sashov"+i+"@gmail.ru")
                .address("UK")
                .phone("0125485"+i)
                .build();

    }


    @Test
    public void addNewContactWrongName(){

    }
    @Test
    public void addNewContactWrongAddress(){

    }
    @Test
    public void addNewContactWrongLastName(){

    }
    @Test
    public void addNewContactWrongPhone(){

    }
    @Test
    public void addNewContactWrongEmail(){

    }

}
