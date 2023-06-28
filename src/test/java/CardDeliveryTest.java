import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardDeliveryTest {
    @BeforeEach
    public void openUrl(){
        open("http://localhost:9999");
    }
    public String actualData() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, 10);
    Date date = calendar.getTime();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    String newDate = dateFormat.format(date);

    return newDate;
    }
    public String data() {
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    String newDate = dateFormat.format(date);
    return newDate;
    }
    @Test
    public void sendingCompletedForm() throws InterruptedException {

        $("[data-test-id=city]").$("[type=text]").setValue("Сыктывкар");
        $("[data-test-id=date]").$("[class=input__control]").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id=date]").$("[class=input__control]").setValue(actualData());
        $("[name=name]").setValue("Петров Николай");
        $("[name=phone]").setValue("+12345678900");
        $(".checkbox__text").click();
        $(".button__text").click();
        Thread.sleep(15000);
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + actualData()));
    }
}
