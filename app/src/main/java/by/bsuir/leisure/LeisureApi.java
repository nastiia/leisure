package by.bsuir.leisure;

import java.util.List;

import by.bsuir.leisure.entities.Category;
import by.bsuir.leisure.entities.Event;
import by.bsuir.leisure.entities.User;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nastia on 28.05.2016.
 */
public class LeisureApi {
    private static LeisureApi sInstance;

    public static LeisureApi getInstance() {
        if (sInstance == null) {
            sInstance = new LeisureApi();
        }
        return sInstance;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.102:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    CustomGitHubService service = retrofit.create(CustomGitHubService.class);

    public CustomGitHubService getService() {
        return service;
    }

    public interface CustomGitHubService {
        @GET("user/login")
        Call<User> login(@Query("externalId") String externalId);

        @GET("category/list")
        Call<List<Category>> getCategories();

        @GET("event/getEventsByCategoryId")
        Call<List<Event>> getEventsByCategoryId(@Query("categoryId") Long categoryId);

        @GET("event/get")
        Call<Event> getEvent(@Query("eventId") Long eventId);
    }
}
