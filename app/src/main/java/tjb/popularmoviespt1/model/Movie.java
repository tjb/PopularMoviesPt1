package tjb.popularmoviespt1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private Integer id;
    private String title;
    private Long voteAverage;
    private Long voteCount;
    private String posterPath;
    private String overview;
    private String releaseDate;

    public Movie() {
    }


    public Movie(Parcel parcel) {
        id = parcel.readInt();
        title = parcel.readString();
        voteAverage = parcel.readLong();
        voteCount = parcel.readLong();
        posterPath = parcel.readString();
        overview = parcel.readString();
        releaseDate = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeLong(voteAverage);
        parcel.writeLong(voteCount);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
    }


    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };


    public Integer getId() {
        return id;
    }

    public Movie setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getVoteAverage() {
        return voteAverage;
    }

    public Movie setVoteAverage(Long voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public Movie setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Movie setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public Movie setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Movie setPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }
}
