package com.bozo.bozopetclinic.formatters;

import com.bozo.bozopetclinic.model.PetType;
import com.bozo.bozopetclinic.service.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
@AllArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        return petTypeService.findAll()
                .stream()
                .filter(petType -> petType.getName().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new ParseException("type not found: " + text, 0));
    }

}
