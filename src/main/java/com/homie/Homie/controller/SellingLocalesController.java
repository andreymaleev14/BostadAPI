package com.homie.Homie.controller;

import com.homie.Homie.models.Locale;
import com.homie.Homie.services.SellingLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api")
public class SellingLocalesController {

    @Autowired
    SellingLocaleService sellingLocaleService;

    @GetMapping("/locales")
    public List<Locale> getLocales() throws ExecutionException, InterruptedException {
        return sellingLocaleService.getAllLocales();
    }

    @GetMapping("/locale/{fieldId}")
    public Locale getLocale(@PathVariable String fieldId) throws ExecutionException, InterruptedException {
        return sellingLocaleService.getLocale(fieldId);
    }

    @PostMapping("locale")
    public void insertLocale(@RequestBody Locale locale) throws ExecutionException, InterruptedException {
        sellingLocaleService.insertLocale(locale);
    }

    @PutMapping("locale/{fieldId}")
    public void updateLocale(@RequestBody Locale locale, @PathVariable String fieldId) throws ExecutionException, InterruptedException {
        sellingLocaleService.updateLocale(locale, fieldId);
    }

    @DeleteMapping("locale/{fieldId}")
    public void deleteLocale(@PathVariable String fieldId) {
        sellingLocaleService.deleteLocale(fieldId);
    }
}