package com.homie.Homie.controller;

import com.homie.Homie.models.Locale;
import com.homie.Homie.models.RentingOutLocale;
import com.homie.Homie.services.RentingOutLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class RentingOutLocalesController {

    @Autowired
    RentingOutLocaleService RentingOutLocaleService;

    @GetMapping("/renting-out-locales")
    public List<Locale> getLocales() throws ExecutionException, InterruptedException {
        return RentingOutLocaleService.getAllRentingOutLocales();
    }

    @GetMapping("/renting-out-locale/{fieldId}")
    public Locale getLocale(@PathVariable String fieldId) throws ExecutionException, InterruptedException {
        return RentingOutLocaleService.getRentingOutLocale(fieldId);
    }

    @PostMapping("renting-out-locale")
    public ResponseEntity<Object> insertLocale(@RequestBody RentingOutLocale locale) throws ExecutionException, InterruptedException {
        if (locale.getToMonthlyCost() == null || locale.getFromMonthlyCost() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        RentingOutLocaleService.insertRentingOutLocale(locale);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("renting-out-locale/{fieldId}")
    public void updateLocale(@RequestBody RentingOutLocale locale, @PathVariable String fieldId) throws ExecutionException, InterruptedException {
        RentingOutLocaleService.updateRentingOutLocale(locale, fieldId);
    }

    @DeleteMapping("renting-out-locale/{fieldId}")
    public void deleteLocale(@PathVariable String fieldId) {
        RentingOutLocaleService.deleteRentingOutLocale(fieldId);
    }
}
