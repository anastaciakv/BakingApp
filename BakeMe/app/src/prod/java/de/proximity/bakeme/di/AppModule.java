package de.proximity.bakeme.di;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.proximity.bakeme.BuildConfig;
import de.proximity.bakeme.data.ApiClient;
import de.proximity.bakeme.data.RecipeTask;
import de.proximity.bakeme.data.RecipeTaskRetrofit;
import de.proximity.bakeme.ui.recipedetails.RecipeDetailsViewModel;
import de.proximity.bakeme.ui.recipelist.RecipeListViewModel;
import de.proximity.bakeme.widget.WidgetConfigViewModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module()
public class AppModule {
    @Singleton
    @Provides
    ApiClient provideApiClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG)
            enableLogging(httpClient);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient.build())
                .build();

        return retrofit.create(ApiClient.class);
    }

    @Singleton
    @Provides
    RecipeListViewModel provideRecipeListViewModel(RecipeTask task) {
        return new RecipeListViewModel(task);
    }

    @Singleton
    @Provides
    WidgetConfigViewModel provideWidgetConfigViewModel(RecipeTask task) {
        return new WidgetConfigViewModel(task);
    }

    @Singleton
    @Provides
    RecipeDetailsViewModel provideRecipeDetailsViewModel() {
        return new RecipeDetailsViewModel();
    }

    @Singleton
    @Provides
    RecipeTask provideRecipeTask(ApiClient client) {
        return new RecipeTaskRetrofit(client);
    }

    private void enableLogging(OkHttpClient.Builder httpClient) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
    }
}
