package com.homie.Homie.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.homie.Homie.models.Locale;
import com.homie.Homie.models.SellingLocale;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class SellingLocaleService {
    public List<Locale> getAllLocales() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = firestore.collection("locales").get();
        QuerySnapshot querySnapshot = query.get();
        return querySnapshot.getDocuments().stream().map(doc -> {
            Locale locale = new SellingLocale();
            locale.setId(Optional.ofNullable(doc.get("id")).orElse("No id").toString());
            locale.setAddress(Optional.ofNullable(doc.get("address")).orElse("No address").toString());
            locale.setDescription(Optional.ofNullable(doc.get("description")).orElse("No description").toString());
            locale.setSquareMeters(Integer.valueOf(Optional.ofNullable(doc.get("square_meters")).orElse("No address").toString()));
            locale.setType(Optional.ofNullable(doc.get("type")).orElse("No type").toString());

            return locale;
        }).collect(Collectors.toList());
    }

    public Locale getLocale(String document) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference docRef = firestore.collection("locales").document(document);
        ApiFuture<DocumentSnapshot> query = docRef.get();

        DocumentSnapshot existingLocale = query.get();
        Locale locale = new SellingLocale();
        locale.setId(Optional.ofNullable(existingLocale.get("id")).orElse("No id").toString());
        locale.setAddress(Optional.ofNullable(existingLocale.get("address")).orElse("No address").toString());
        locale.setDescription(Optional.ofNullable(existingLocale.get("description")).orElse("No description").toString());
        locale.setSquareMeters(Integer.valueOf(Optional.ofNullable(existingLocale.get("square_meters")).orElse("0").toString()));
        locale.setType(Optional.ofNullable(existingLocale.get("type")).orElse("No type").toString());

        return locale;
    }

    public Map<String, Object> insertLocale(Locale payload) {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference docRef = firestore.collection("locales").document(UUID.randomUUID().toString());
        Map<String, Object> data = getLocaleDbPayload(payload, null);

        ApiFuture<WriteResult> result = docRef.set(data);
        return data;
    }

    public WriteResult updateLocale(Locale payload, String document) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference docRef = firestore.collection("locales").document(document);
        ApiFuture<DocumentSnapshot> query = docRef.get();
        Locale existingLocale = query.get().toObject(Locale.class);


        Map<String, Object> updates = getLocaleDbPayload(payload, existingLocale);

        return docRef.update(updates).get();
    }

    public void deleteLocale(String document){
        Firestore firestore = FirestoreClient.getFirestore();
        firestore.collection("locales").document(document).delete();
    }

    private static Map<String, Object> getLocaleDbPayload(Locale payload, Locale existingLocale) {
        Map<String,Object> data = new HashMap<>();
        if (existingLocale != null) {
            data.put("id", existingLocale.getId());
        } else {
            existingLocale = new SellingLocale();
            data.put("id", UUID.randomUUID().toString());
        }

        data.put("address", Optional.of(payload.getAddress()).orElse(existingLocale.getAddress()));
        data.put("description", Optional.ofNullable(payload.getDescription()).orElse(existingLocale.getDescription()));
        data.put("square_meters", Optional.ofNullable(payload.getSquareMeters()).orElse(existingLocale.getSquareMeters()));
        data.put("type", Optional.ofNullable(payload.getType()).orElse(existingLocale.getType()));

        return data;
    }

}
