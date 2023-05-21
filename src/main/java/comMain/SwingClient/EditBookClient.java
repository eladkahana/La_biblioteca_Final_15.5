package comMain.SwingClient;

import comMain.entities.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public abstract class EditBookClient {

    private static RestTemplate restTemplate;

    public static ArrayList<String> getAuthorsByBookID(int BookID) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bookVSAuthor/getAllAuthorsByBook";
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("bookID",  BookID).build().toUri();
        ParameterizedTypeReference<ArrayList<BookVsAuthorEntity>> responseType = new ParameterizedTypeReference<ArrayList<BookVsAuthorEntity>>() {
        };
        ArrayList<BookVsAuthorEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        ArrayList<String> authorsList = new ArrayList<>();
        for (BookVsAuthorEntity authorID : response) {

             url = "http://localhost:8080/author/" + authorID.getAuthorId();
             uri = UriComponentsBuilder.fromUriString(url).build().toUri();
            ParameterizedTypeReference<AuthorEntity> newResponseType = new ParameterizedTypeReference<AuthorEntity>() {
            };

            AuthorEntity author = restTemplate.exchange(uri, HttpMethod.GET, null, newResponseType).getBody();


            authorsList.add(InformationGUI.getName(author.getFirstName(),author.getLastName()));
        }
        return authorsList;

    }



    public static ArrayList<String> getAudiencesByBookID(int BookID) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/audienceVSBook/getAllAudiencesByBook";
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("bookID",  BookID).build().toUri();
        ParameterizedTypeReference<ArrayList<AudienceVsBookEntity>> responseType = new ParameterizedTypeReference<ArrayList<AudienceVsBookEntity>>() {
        };
        ArrayList<AudienceVsBookEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        ArrayList<String> audienceList = new ArrayList<>();
        for (AudienceVsBookEntity audienceID : response) {

            url = "http://localhost:8080/audience/" + audienceID.getGroupId();
            uri = UriComponentsBuilder.fromUriString(url).build().toUri();
            ParameterizedTypeReference<AudienceEntity> newResponseType = new ParameterizedTypeReference<AudienceEntity>() {
            };

            AudienceEntity audience = restTemplate.exchange(uri, HttpMethod.GET, null, newResponseType).getBody();
            audienceList.add(audience.getTeamName());

        }
        return audienceList;

    }


    public static ArrayList<String> getCategoriesByBookID(int BookID) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bookVSCategory/getAllCategoriesByBook";
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("bookID",  BookID).build().toUri();
        ParameterizedTypeReference<ArrayList<BookVsCategoryEntity>> responseType = new ParameterizedTypeReference<ArrayList<BookVsCategoryEntity>>() {
        };
        ArrayList<BookVsCategoryEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        ArrayList<String> categoriesList = new ArrayList<>();
        for (BookVsCategoryEntity categoryID : response) {

            url = "http://localhost:8080/categories/" + categoryID.getCategoryId();
            uri = UriComponentsBuilder.fromUriString(url).build().toUri();
            ParameterizedTypeReference<CategoriesEntity> newResponseType = new ParameterizedTypeReference<CategoriesEntity>() {
            };

            CategoriesEntity audience = restTemplate.exchange(uri, HttpMethod.GET, null, newResponseType).getBody();
            categoriesList.add(audience.getCategoryName());

        }
        return categoriesList;

    }



    public static Object[] getSeriesByBookID(int BookID) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/seriesVSBook/getSeriesByBook";
        URI uri = UriComponentsBuilder.fromUriString(url).queryParam("bookID",  BookID).build().toUri();
        ParameterizedTypeReference<List<Object[]>> responseType = new ParameterizedTypeReference<List<Object[]>>() {

        };
        List<Object[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();


        if(response.size() != 0) {
            return response.get(0);
        }
        else{
            return null;
        }
    }


    public static String getShelfByBookID(BookEntity book) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/shelfmark/" + book.getShelfmarkId();
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ShelfmarkEntity> newResponseType = new ParameterizedTypeReference<ShelfmarkEntity>() {
        };

        ShelfmarkEntity shelf = restTemplate.exchange(uri, HttpMethod.GET, null, newResponseType).getBody();


        return shelf.getMark();
    }


    public static String getPublisherByBookID(BookEntity book) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/publisher/" + book.getPublisherId();
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<PublisherEntity> newResponseType = new ParameterizedTypeReference<PublisherEntity>() {
        };

        PublisherEntity publisher = restTemplate.exchange(uri, HttpMethod.GET, null, newResponseType).getBody();


        return publisher.getPublisherName();
    }



    public static void EditBook(String ISBN,
                                String title,
                                String edition,
                                String shelfmark,
                                int numberOfPages,
                                int publishYear,
                                byte[] coverImage,
                                String language,
                                String publisher,
                                String note,
                                int ID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/book/EditBook";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("ISBN", ISBN);
        parameters.add("title", title);
        parameters.add("edition", edition);
        parameters.add("shelfmark", shelfmark);
        parameters.add("numberOfPages", numberOfPages);
        parameters.add("publishYear", publishYear);
        parameters.add("coverImage", coverImage);
        parameters.add("language", language);
        parameters.add("publisher", publisher);
        parameters.add("note", note);
        parameters.add("ID", ID);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<Object[]>>() {});
    }



    public static void deleteAuthorsFromBook(int bookID) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bookVSAuthor/deleteAuthorFromBook";
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("bookID", bookID)
                .build()
                .toUri();

        restTemplate.delete(uri);
    }



    public static void deleteAudiencesfromBook(int bookID) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/audienceVSBook/deleteAudienceFromBook";
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("bookID", bookID)
                .build()
                .toUri();

        restTemplate.delete(uri);

    }

    public static void deleteCategoriesfromBook(int bookID) {
        restTemplate = new RestTemplate();
        String url = "http://localhost:8080/bookVSCategory/deleteCategoryFromBook";
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("bookID", bookID)
                .build()
                .toUri();

        restTemplate.delete(uri);
    }

    public static void deleteSeriesfromBook(int bookID) {
         restTemplate = new RestTemplate();
        String url = "http://localhost:8080/seriesVSBook/deleteBookFromSeries";
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("bookID", bookID)
                .build()
                .toUri();

        restTemplate.delete(uri);
    }



}
