package com.unica.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping(path = "/v1/person")
    PersonV1 personV1(){
        return new PersonV1("Phase TUMURERE");
    }

    @GetMapping(path = "/v2/person")
    PersonV2 personV2(){
        return new PersonV2(new Name("Phase", "TUMURERE"));
    }

    @GetMapping(value = "/person/param", params = "version=1") //person/param?version=1
    PersonV1 paramV1(){
        return new PersonV1("Phase TUMURERE");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    PersonV2 paramV2(){
        return new PersonV2(new Name("Phase", "TUMURERE"));
    }

    //Headers Versioning
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    PersonV1 headerV1(){
        return new PersonV1("Phase TUMURERE");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    PersonV2 headerV2(){
        return new PersonV2(new Name("Phase", "TUMURERE"));
    }

//    Producers Versioning

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json") //Headers Versioning
    PersonV1 producesV1(){
        return new PersonV1("Phase TUMURERE");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    PersonV2 producesV2(){
        return new PersonV2(new Name("Phase", "TUMURERE"));
    }
}
