package com.settings.webdriver;

import com.selenium.pom.utils.JacksonUtils;

public class WebdriverSettings {

    public WebdriverSettingDto getSettings() {
        return JacksonUtils
                .deserializeJsonFileToJsonObject("settings/webdriversettings", WebdriverSettingDto.class);
    }

}
