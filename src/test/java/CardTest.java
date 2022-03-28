import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.openqa.selenium.Keys.chord;

public class CardTest{
    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void test(){
        String plannigDate = generateDate(5);
        open("http://localhost:9999/");
        $x("//input[@type='text']").val("Уфа");
        $("[placeholder='Дата встречи']").sendKeys(chord(Keys.SHIFT, Keys.HOME), Keys.DELETE,plannigDate);
        $x("//input[@name='name']").val("Кирилл Мит");
        $x("//input[@name='phone']").val("+89370659485");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $x("//*[contains(text(),'Успешно!')]").should(appear, ofSeconds(10));
}
    @Test
    public void notCity(){
        String plannigDate = generateDate(5);
        open("http://localhost:9999/");
        $x("//input[@type='text']").val("");
        $("[placeholder='Дата встречи']").sendKeys(chord(Keys.SHIFT, Keys.HOME), Keys.DELETE,plannigDate);
        $x("//input[@name='name']").val("Кирилл Мит");
        $x("//input[@name='phone']").val("+89370659485");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $x("//*[contains(text(),'Поле обязательно для заполнения')]").should(visible);
    }
    @Test
    public void notName(){
        String plannigDate = generateDate(5);
        open("http://localhost:9999/");
        $x("//input[@type='text']").val("Уфа");
        $("[placeholder='Дата встречи']").sendKeys(chord(Keys.SHIFT, Keys.HOME), Keys.DELETE,plannigDate);
        $x("//input[@name='name']").val("");
        $x("//input[@name='phone']").val("+89370659485");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $x("//*[contains(text(),'Поле обязательно для заполнения')]").should(visible);
    }
    @Test
    public void notTel(){
        String plannigDate = generateDate(5);
        open("http://localhost:9999/");
        $x("//input[@type='text']").val("Уфа");
        $("[placeholder='Дата встречи']").sendKeys(chord(Keys.SHIFT, Keys.HOME), Keys.DELETE,plannigDate);
        $x("//input[@name='name']").val("Анна");
        $x("//input[@name='phone']").val("");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $x("//*[contains(text(),'Поле обязательно для заполнения')]").should(visible);
    }
    @Test
    public void test2(){
        open("http://localhost:9999/");
        String plannigDate = generateDate(5);
        $x("//input[@type='text']").val("Уфа");
        $("[placeholder='Дата встречи']").sendKeys(chord(Keys.SHIFT, Keys.HOME), Keys.DELETE,plannigDate);
        $x("//input[@name='name']").val("Кирилл Мит");
        $x("//input[@name='phone']").val("+89370659485");
        $(withText("Забронировать")).click();
        $x("//*[contains(text(),'Я соглашаюсь с условиями обработки и использования моих персональных данных')]").should(visible);
    }
    @Test
    public void notDate(){
        open("http://localhost:9999/");
        $x("//input[@type='text']").val("Уфа");
        $x("//input[@name='name']").val("Кирилл Мит");
        $x("//input[@name='phone']").val("+89370659485");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $x("//*[contains(text(),'Успешно!')]").should(appear, ofSeconds(16));
    }
    @Test
    void shouldSendForm() {
        String planningDate = generateDate(5);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").val("Новосибирск");
        $("[placeholder='Дата встречи']").sendKeys(chord(Keys.SHIFT, Keys.HOME), Keys.DELETE, planningDate);
        $("[data-test-id='name'] input").val("Олег");
        $("[data-test-id='phone'] input").val("+79993336666");
        $("[data-test-id=agreement]>.checkbox__box").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification] .notification__title").shouldBe(visible, ofSeconds(16))
                .shouldHave(exactText("Успешно!"));

    }
}