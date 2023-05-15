package comMain.client;

import comMain.entities.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.net.URI;
import java.util.ArrayList;

public class InformationGUI {

    public static DefaultListModel<BookEntity> getAllBooks(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/book";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<BookEntity>> responseType = new ParameterizedTypeReference<ArrayList<BookEntity>>() {};
        ArrayList<BookEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        DefaultListModel<BookEntity> booksListModel = new DefaultListModel<>();
        for (BookEntity book : response) {
            booksListModel.addElement(book);
        }

        return booksListModel;
    }

    public static DefaultListModel<ReadersEntity> getAllReaders(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/readers";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<ReadersEntity>> responseType = new ParameterizedTypeReference<ArrayList<ReadersEntity>>() {};
        ArrayList<ReadersEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        DefaultListModel<ReadersEntity> readersListModel = new DefaultListModel<>();
        for (ReadersEntity reader : response) {
            readersListModel.addElement(reader);
        }

        return readersListModel;
    }


    public static DefaultListModel<ReserveEntity> getAllReserves(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/reserve";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<ReserveEntity>> responseType = new ParameterizedTypeReference<ArrayList<ReserveEntity>>() {};
        ArrayList<ReserveEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        DefaultListModel<ReserveEntity> reservesListModel = new DefaultListModel<>();
        for (ReserveEntity reserve : response) {
            reservesListModel.addElement(reserve);
        }

        return reservesListModel;
    }

    public static JComboBox<String> getAllCategories(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/categories";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<CategoriesEntity>> responseType = new ParameterizedTypeReference<ArrayList<CategoriesEntity>>() {};
        ArrayList<CategoriesEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> categoriesList = new JComboBox<>();
        for (CategoriesEntity category : response) {
            categoriesList.addItem(category.getCategoryName());
        }

        return categoriesList;
    }

    public static JComboBox<String> getAllLanguages(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/language";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<LanguageEntity>> responseType = new ParameterizedTypeReference<ArrayList<LanguageEntity>>() {};
        ArrayList<LanguageEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> languagesList = new JComboBox<>();
        for (LanguageEntity language : response) {
            languagesList.addItem(language.getLanguage());
        }

        return languagesList;
    }

    public static JComboBox<String> getAllAudiences(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/audience";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<AudienceEntity>> responseType = new ParameterizedTypeReference<ArrayList<AudienceEntity>>() {};
        ArrayList<AudienceEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> audiencesList = new JComboBox<>();
        for (AudienceEntity audience : response) {
            audiencesList.addItem(audience.getTeamName());
        }

        return audiencesList;
    }


    public static JComboBox<String> getAllAuthors() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/author";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<AuthorEntity>> responseType = new ParameterizedTypeReference<ArrayList<AuthorEntity>>() {
        };
        ArrayList<AuthorEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        JComboBox<String> authorsList = new JComboBox<>();
        for (AuthorEntity author : response) {
            url = "http://localhost:8080/firstName/" + author.getFirstName();
            uri = UriComponentsBuilder.fromUriString(url).build().toUri();

            FirstNameEntity firstName = restTemplate.getForObject(uri, FirstNameEntity.class);

            url = "http://localhost:8080/lastName/" + author.getLastName();
            uri = UriComponentsBuilder.fromUriString(url).build().toUri();

            LastNameEntity lastName = restTemplate.getForObject(uri, LastNameEntity.class);
            authorsList.addItem(firstName.getFirstName() + " " + lastName.getLastName());
        }
        return authorsList;
    }




    public static JComboBox<String> getAllSeries(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/seriesBook";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<SeriesBookEntity>> responseType = new ParameterizedTypeReference<ArrayList<SeriesBookEntity>>() {};
        ArrayList<SeriesBookEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();


        JComboBox<String> authorsList = new JComboBox<>();
        for (SeriesBookEntity series : response) {
            authorsList.addItem(series.getSeriesTitle());
        }

        return authorsList;
    }




}
