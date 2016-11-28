package marcook_pool.pool_finder.ui;

/**
 * Created by ryan on 18/09/16.
 */
import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class PoolTable {
    public int ID;
    public String photoURL;
    public String establishment;
    public String location;
    public String description;
    public float rating;

    public PoolTable() {

    }

    public PoolTable(int ID, String photoURL, String establishment, String location, String description, int rating)
    {
        this.ID = ID;
        this.photoURL = photoURL;
        this.establishment = establishment;
        this.location = location;
        this.description = description;
        this.rating = rating;
    }

    public String getEstablishment() {
        return establishment;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public float getReview() {
        return rating;
    }
}
