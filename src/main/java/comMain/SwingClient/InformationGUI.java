package comMain.SwingClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import comMain.entities.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import javax.swing.*;
import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public abstract class InformationGUI {



    private static RestTemplate restTemplate;

    public static DefaultListModel<BookEntity> getAllBooks() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/book";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<BookEntity>> responseType = new ParameterizedTypeReference<ArrayList<BookEntity>>() {
        };
        ArrayList<BookEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        DefaultListModel<BookEntity> booksListModel = new DefaultListModel<>();
        for (BookEntity book : response) {
            booksListModel.addElement(book);
        }

        return booksListModel;
    }

    public static DefaultListModel<ReadersEntity> getAllReaders() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/readers";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<ReadersEntity>> responseType = new ParameterizedTypeReference<ArrayList<ReadersEntity>>() {
        };
        ArrayList<ReadersEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        DefaultListModel<ReadersEntity> readersListModel = new DefaultListModel<>();
        for (ReadersEntity reader : response) {
            readersListModel.addElement(reader);
        }

        return readersListModel;
    }


    public static List<Object[]> getAllReserves() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/reserve/getAllReservations";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<Object[]>> responseType = new ParameterizedTypeReference<ArrayList<Object[]>>() {};
        ArrayList<Object[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();

        return response;
    }

    public static JComboBox<String> getAllCategories() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/categories";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<CategoriesEntity>> responseType = new ParameterizedTypeReference<ArrayList<CategoriesEntity>>() {
        };
        ArrayList<CategoriesEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> categoriesList = new JComboBox<>();
        for (CategoriesEntity category : response) {
            categoriesList.addItem(category.getCategoryName());
        }

        return categoriesList;
    }

    public static JComboBox<String> getAllLanguages() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/language";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<LanguageEntity>> responseType = new ParameterizedTypeReference<ArrayList<LanguageEntity>>() {
        };
        ArrayList<LanguageEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> languagesList = new JComboBox<>();
        for (LanguageEntity language : response) {
            languagesList.addItem(language.getLanguage());
        }

        return languagesList;
    }

    public static JComboBox<String> getAllAudiences() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/audience";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<AudienceEntity>> responseType = new ParameterizedTypeReference<ArrayList<AudienceEntity>>() {
        };
        ArrayList<AudienceEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> audiencesList = new JComboBox<>();
        for (AudienceEntity audience : response) {
            audiencesList.addItem(audience.getTeamName());
        }

        return audiencesList;
    }


    public static JComboBox<String> getAllAuthors() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/author";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<AuthorEntity>> responseType = new ParameterizedTypeReference<ArrayList<AuthorEntity>>() {
        };
        ArrayList<AuthorEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> authorsList = new JComboBox<>();
        for (AuthorEntity author : response) {
            authorsList.addItem(getName(author.getFirstName(), author.getLastName()));
        }
        return authorsList;
    }


    public static String getName(int FisrNameID, int LastNameID) {
        String url = "http://localhost:8080/firstName/" + FisrNameID;
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        FirstNameEntity firstName = restTemplate.getForObject(uri, FirstNameEntity.class);

        url = "http://localhost:8080/lastName/" + LastNameID;
        uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        LastNameEntity lastName = restTemplate.getForObject(uri, LastNameEntity.class);
        return (firstName.getFirstName() + ", " + lastName.getLastName());
    }


    public static JComboBox<String> getAllSeries() {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/seriesBook";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<SeriesBookEntity>> responseType = new ParameterizedTypeReference<ArrayList<SeriesBookEntity>>() {
        };
        ArrayList<SeriesBookEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();


        JComboBox<String> authorsList = new JComboBox<>();
        for (SeriesBookEntity series : response) {
            authorsList.addItem(series.getSeriesTitle());
        }

        return authorsList;
    }

    public static byte[] getBarcode(String ID) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/barcode/" + ID;
        URI uri = UriComponentsBuilder.fromUriString(url)
                .build().toUri();
        ParameterizedTypeReference<byte[]> responseType = new ParameterizedTypeReference<byte[]>() {
        };
        byte[] response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();

        return response;
    }





    public static void setBookToSeries(String bookSeries, String ISBN, int bookIndexInSeries) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/seriesVSBook/setBookToSeries";
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("BookSeries", bookSeries);
        parameters.add("ISBN", ISBN);
        parameters.add("BookIndexInSeries", bookIndexInSeries);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
        });
    }

    public static void setCategoryToBook(JComboBox<String> categoriesList, String ISBN) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bookVSCategory/setCategoryToBook";

        for (int i = 0; i < categoriesList.getItemCount(); i++) {
            String category = categoriesList.getItemAt(i);

            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("Category", category);
            parameters.add("ISBN", ISBN);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
            });
        }
    }


    public static void setAuthorToBook(JComboBox<String> authorsList, String ISBN) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bookVSAuthor/setAuthorToBook";


        for (int i = 0; i < authorsList.getItemCount(); i++) {
            String author = authorsList.getItemAt(i);
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("AuthorFN", author.substring(0, author.indexOf(',')));
            parameters.add("AuthorLN", author.substring(author.indexOf(',') + 2));
            parameters.add("ISBN", ISBN);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
            });
        }
    }

    public static void setAudienceToBook(JComboBox<String> audienceList, String ISBN) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/audienceVSBook/setAudienceToBook";


        for (int i = 0; i < audienceList.getItemCount(); i++) {
            String audience = audienceList.getItemAt(i);
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("team", audience);
            parameters.add("ISBN", ISBN);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
            });
        }
    }





    public static List<RequestsEntity> getUnCheckedRequests() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/requests/getUnCheckedRequests";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<RequestsEntity> myTableList = objectMapper.readValue(response, new TypeReference<List<RequestsEntity>>(){});


        return myTableList;
    }

    public static List<RequestsEntity> getCheckedRequests() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/requests/getCheckedRequests";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        String response = restTemplate.getForObject(uri, String.class);

// Use Jackson library to deserialize the JSON string into a list of MyTable objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<RequestsEntity> myTableList = objectMapper.readValue(response, new TypeReference<List<RequestsEntity>>(){});


        return myTableList;
    }

    public static String getMailDetails(RequestsEntity request){

        String url = "http://localhost:8080/readers/" + request.getReaderId();
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        ReadersEntity reader = restTemplate.getForObject(uri, ReadersEntity.class);


        return ("Name: " + getName(reader.getFirstName(),reader.getLastName()) + ", send date: " + request.getSendDate() + ", topic: " + request.getTopic());

    }


    public static void requestChecked(int RequestID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/requests/requestChecked";


        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("requestID", RequestID);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {
        });
    }

    public static void responseEmail(RequestsEntity request, String answer){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/emails/reaction";


        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("requestID", request.getId());
        parameters.add("answer", answer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});

    }


    public static String getGenderByID(int genderID){

        String url = "http://localhost:8080/gender/" + genderID;
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();

        GenderEntity gender = restTemplate.getForObject(uri, GenderEntity.class);


        return gender.getGender();

    }




}
