package tests;


import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {


    @Test
    @Tag("android")
    void successfulSearchTest() {
        step("Ввод поискового запроса", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка результатов поиска", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/search_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("android")
    void checkErrorTest() {
        step("Ввод поискового запроса", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });

        step("Переход на страницу Appium", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_container")).first().click();
        });

        step("Проверка страницы", () -> {
        $(AppiumBy.id("org.wikipedia.alpha:id/view_wiki_error_button")).shouldBe(visible);
        $(AppiumBy.id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(text("An error occurred"));
        });
    }

    @Test
    @Tag("ios")
    void sampleTest() {
        step("Переход на форму ввода текста", () ->
                $(AppiumBy.accessibilityId("Text Button")).click());
        step("Ввод текста и его отправка", () -> {
            $(AppiumBy.accessibilityId("Text Input")).sendKeys("Тест");
            $(AppiumBy.accessibilityId("Text Input")).pressEnter();
        });
        step("Проверка отображения введенного текста", () ->
                $(AppiumBy.accessibilityId("Text Output")).shouldHave(text("Тест")));
    }
}
