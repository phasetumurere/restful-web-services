package com.unica.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
    FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
    @GetMapping(path = "/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
//        Field1, and field2
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue beanList(){
//        field2 and field3
        List<SomeBean> list = Arrays.asList(new SomeBean("field1", "Field2", "Field3"),
                new SomeBean("field21", "field22", "field23"));
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }
}
