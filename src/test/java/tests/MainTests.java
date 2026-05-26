package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.linkText;

public class MainTests extends TestBase {


    @CsvFileSource(resources = "/testdata/Modules.csv")
    @ParameterizedTest(name = "Проверка страницы модуля {0}, его адреса {1} и описания {2}")
    void checkingModulesWithCsvFileSource(String moduleName, String moduleUrl, String moduleText) {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> open(baseUrl));
        step("Кликаем на ссылку Возможности", () -> $("a[href='#submenu:product']").click());
        step("Кликаем по ссылке модуля " + moduleName, () -> $(linkText(moduleName)).click());
        step("Проверяем наличие части адреса в URL: " + moduleUrl, () -> assertTrue(WebDriverRunner.url().contains(moduleUrl)));
        step("Проверяем наличие текста на странице " + moduleText, () -> {
            $(withText(moduleText)).should(Condition.exist);
        });

    }

}
