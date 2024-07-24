package com.homie.Homie.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.homie.Homie.models.Locale;
import com.homie.Homie.models.RentingOutLocale;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class RentingOutLocaleService {
    private final String SCHEMA = "renting_out_locales";

    public List<Locale> getAllRentingOutLocales() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = firestore.collection(SCHEMA).get();
        QuerySnapshot querySnapshot = query.get();
        return querySnapshot.getDocuments().stream().map(existingLocale -> {
            RentingOutLocale locale = new RentingOutLocale();
            locale.setId(Optional.ofNullable(existingLocale.get("id")).orElse("No id").toString());
            locale.setAddress(Optional.ofNullable(existingLocale.get("address")).orElse("No address").toString());
            locale.setDescription(Optional.ofNullable(existingLocale.get("description")).orElse("No description").toString());
            locale.setSquareMeters(Integer.valueOf(Optional.ofNullable(existingLocale.get("square_meters")).orElse("0").toString()));
            locale.setType(Optional.ofNullable(existingLocale.get("type")).orElse("No type").toString());
            locale.setFromMonthlyCost(Integer.valueOf(Optional.ofNullable(existingLocale.get("from_monthly_cost")).orElse("0").toString()));
            locale.setToMonthlyCost(Integer.valueOf(Optional.ofNullable(existingLocale.get("to_monthly_cost")).orElse("0").toString()));

            return locale;
        }).collect(Collectors.toList());
    }

    public Locale getRentingOutLocale(String document) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference docRef = firestore.collection(SCHEMA).document(document);
        ApiFuture<DocumentSnapshot> query = docRef.get();

        DocumentSnapshot existingLocale = query.get();
        RentingOutLocale locale = new RentingOutLocale();
        locale.setId(Optional.ofNullable(existingLocale.get("id")).orElse("No id").toString());
        locale.setAddress(Optional.ofNullable(existingLocale.get("address")).orElse("No address").toString());
        locale.setDescription(Optional.ofNullable(existingLocale.get("description")).orElse("No description").toString());
        locale.setSquareMeters(Integer.valueOf(Optional.ofNullable(existingLocale.get("square_meters")).orElse("0").toString()));
        locale.setType(Optional.ofNullable(existingLocale.get("type")).orElse("No type").toString());
        locale.setFromMonthlyCost(Integer.valueOf(Optional.ofNullable(existingLocale.get("from_monthly_cost")).orElse("0").toString()));
        locale.setToMonthlyCost(Integer.valueOf(Optional.ofNullable(existingLocale.get("to_monthly_cost")).orElse("0").toString()));

        return locale;
    }

    public void insertRentingOutLocale(RentingOutLocale payload) {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference docRef = firestore.collection(SCHEMA).document(UUID.randomUUID().toString());
        Map<String, Object> data = getLocaleDbPayload(payload, null);

        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public WriteResult updateRentingOutLocale(RentingOutLocale payload, String document) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference docRef = firestore.collection(SCHEMA).document(document);
        ApiFuture<DocumentSnapshot> query = docRef.get();
        RentingOutLocale existingLocale = query.get().toObject(RentingOutLocale.class);


        Map<String, Object> updates = getLocaleDbPayload(payload, existingLocale);

        return docRef.update(updates).get();
    }

    public void deleteRentingOutLocale(String document){
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection(SCHEMA).document(document).delete();
    }

    private static Map<String, Object> getLocaleDbPayload(RentingOutLocale payload, RentingOutLocale existingLocale) {
        Map<String,Object> data = new HashMap<>();
        if (existingLocale != null) {
            data.put("id", existingLocale.getId());
        } else {
            existingLocale = new RentingOutLocale();
            data.put("id", UUID.randomUUID().toString());
        }

        data.put("address", Optional.of(payload.getAddress()).orElse(existingLocale.getAddress()));
        data.put("description", Optional.ofNullable(payload.getDescription()).orElse(existingLocale.getDescription()));
        data.put("square_meters", Optional.ofNullable(payload.getSquareMeters()).orElse(existingLocale.getSquareMeters()));
        data.put("type", Optional.ofNullable(payload.getType()).orElse(existingLocale.getType()));
        data.put("from_monthly_cost", Optional.ofNullable(payload.getFromMonthlyCost()).orElse(existingLocale.getFromMonthlyCost()));
        data.put("to_monthly_cost", Optional.ofNullable(payload.getToMonthlyCost()).orElse(existingLocale.getToMonthlyCost()));

        return data;
    }
}
