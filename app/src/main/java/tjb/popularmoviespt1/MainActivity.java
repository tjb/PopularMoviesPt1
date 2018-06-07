package tjb.popularmoviespt1;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tjb.popularmoviespt1.adapter.MovieAdapter;
import tjb.popularmoviespt1.model.Movie;
import tjb.popularmoviespt1.utils.JSONParser;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;

    @BindView(R.id.recview_movies)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // initialize recycler view
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        // fetch db info
        GetMovieDataAsyncTask getMovieDataAsyncTask = new GetMovieDataAsyncTask(this);
        getMovieDataAsyncTask.execute("popular");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // Set to true on initial load
        menu.findItem(R.id.popular_action).setChecked(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        GetMovieDataAsyncTask getMovieDataAsyncTask = new GetMovieDataAsyncTask(this);

        if (itemId == R.id.popular_action) {
            item.setChecked(true);
            getMovieDataAsyncTask.execute("popular");
        }

        if (itemId == R.id.top_rated) {
            item.setChecked(true);
            getMovieDataAsyncTask.execute("top_rated");
        }

        return super.onOptionsItemSelected(item);
    }

    public class GetMovieDataAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        private Context asyncContext;

        public GetMovieDataAsyncTask(Context context) {
            asyncContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            movieAdapter.updateData(movies);

        }

        private ArrayList<Movie> run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            OkHttpClient client = new OkHttpClient();


            try {
                Response response = client.newCall(request).execute();
                if (response.body() != null) {
                    return new JSONParser().parseString((response.body().string()));
                }
            } catch (Exception e) {
                throw new IOException(e);
            }
            return null;
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            String type = params[0];
            try {
                return run("https://api.themoviedb.org/3/movie/" + type + "?api_key=" + asyncContext.getString(R.string.api_key));
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
            return null;

        }
    }
}
