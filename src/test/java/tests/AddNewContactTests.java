package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {

    @BeforeClass
    public void preConditions() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("blohodavka@mail.ru").withPassword("Mama123$"));
        }

    }

    @Test
    public void addContactSuccessAllFields() {

        int i = (int) (System.currentTimeMillis() / 1000 % 3600);

        Contact contact = Contact.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .email("ivanov" + i + "@gmail.ru")
                .address("Russia")
                .phone("0125485" + i)
                .description("all fields")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen"+i+".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddByPhone(contact.getPhone()));


    }

    @Test
    public void addContactSuccessAllReqFields() {

        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
        Contact contact = Contact.builder()
                .name("SashaReq")
                .lastName("Sashov")
                .email("sashov" + i + "@gmail.ru")
                .address("UK")
                .phone("0125485" + i)
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddByPhone(contact.getPhone()));

    }


    @Test
    public void addNewContactWrongName() {

        Contact contact = Contact.builder()
                .name("")
                .lastName("Ivanov")
                .email("ivanovee@gmail.ru")
                .address("Russia")
                .phone("0125485555")
                .description("wrong name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());


    }

    @Test
    public void addNewContactWrongAddress() {
        Contact contact = Contact.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .email("ivanova@gmail.ru")
                .address("")
                .phone("012548548484")
                .description("wrong address")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
      //  app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongLastName() {
        Contact contact = Contact.builder()
                .name("Ivan")
                .lastName("")
                .email("ivanove@gmail.ru")
                .address("Russia")
                .phone("0125485865996")
                .description("wrong Last Name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongPhone() {
        Contact contact = Contact.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .email("ivanovqq@gmail.ru")
                .address("Russia")
                .phone("")
                .description("wrong phone")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactWrongEmail() {
        Contact contact = Contact.builder()
                .name("Ivan")
                .lastName("Ivanov")
                .email("ivanovgmail.ru")
                .address("Russia")
                .phone("012548511221")
                .description("wrong email")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().getScreen("src/test/screenshots/screen.png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid:"));

    }

}
