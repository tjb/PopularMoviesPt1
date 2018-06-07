package tjb.popularmoviespt1;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import tjb.popularmoviespt1.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_image)
    ImageView posterImageView;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.average_rating)
    TextView averageRating;

    @BindView(R.id.overview)
    TextView overview;

    @BindView(R.id.release_date)
    TextView releaseDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);
        ButterKnife.bind(this);
        Movie movie = getIntent().getParcelableExtra("MOVIE_INFO");
        setInformation(movie);
    }

    private void setInformation(Movie movie) {
        title.setText(movie.getTitle());
        averageRating.setText(String.format("Rating: " + movie.getVoteAverage().toString() + "/10 (" + movie.getVoteCount().toString() + ")", "US_EN"));
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());

        String size = "w185";

        try {
            URL image = new URL(new Uri.Builder()
                    .scheme("https")
                    .authority("image.tmdb.org")
                    .appendPath("t")
                    .appendPath("p")
                    .appendPath(size)
                    .appendPath(movie.getPosterPath().replace("/", ""))
                    .build()
                    .toString());
            Picasso.with(posterImageView.getContext())
                    .load(image.toString())
                    .into(posterImageView);
        } catch (MalformedURLException e) {
            Log.e("ERROR", e.getMessage());
        }
    }
}
