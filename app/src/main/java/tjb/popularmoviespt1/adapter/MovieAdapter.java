package tjb.popularmoviespt1.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tjb.popularmoviespt1.MovieDetailActivity;
import tjb.popularmoviespt1.R;
import tjb.popularmoviespt1.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;

    private ArrayList<Movie> movies;

    public MovieAdapter() {
        //constructor
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the movie poster view item
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position, List<Object> payloads) {
        Movie movie = movies.get(position);
        holder.setPosition(position);
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
            Picasso.with(holder.imageView.getContext())
                    .load(image.toString())
                    .into(holder.imageView);
        } catch (MalformedURLException e) {
            Log.e("ERROR", e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }


    // https://stackoverflow.com/questions/36967247/passing-data-to-custom-adapter-from-asynctask
    public void updateData(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }






    // This is the actual view holder that will populate the items
    // With the proper information
    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie) ImageView imageView;

        private int position;
        // When onCreateViewHolder creates a MovieViewHolder
        // the view passed in is the inflated movie item view
        public MovieViewHolder(View inflatedItemView) {
            super(inflatedItemView);
            context = inflatedItemView.getContext();
            ButterKnife.bind(this, inflatedItemView);
            // Cool trick i found on stackoverflow
            // https://stackoverflow.com/questions/38179023/recyclerview-onclicklistener
            inflatedItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("MOVIE_INFO", movies.get(position));
                    context.startActivity(intent);
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }

    }
}
