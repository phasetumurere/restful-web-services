package com.unica.rest.webservices.restful_web_services.hello_word;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWordController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world") // Map get request to this url
    public String helloWorld(){
        return "Hello Word";
    }

    @GetMapping(path = "/hello-world-bean") // Map get request to this url
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/{name}") // Map get request to this url
    public HelloWorldBean helloWorldBeanPathParameter(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World %s", name));
    }
    @GetMapping(path = "/hello-world-internationalized") // Map get request to this url
    public String helloWorldInternationalized(@RequestHeader (name = "Accept-Language",
            required = false)Locale locale) {
//        return "Hello World";
        return messageSource.getMessage("good.morning.message",null,"Default Message",
//                locale);
                LocaleContextHolder.getLocale());
    }
}
