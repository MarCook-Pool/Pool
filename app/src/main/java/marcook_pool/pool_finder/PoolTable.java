package marcook_pool.pool_finder;

/**
 * Created by ryan on 18/09/16.
 */
import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class PoolTable {
    int ID;
    String photoURL;
    String establishment;
    String location;
    String description;
    int rating; // out of 50 - divide by 10 when using

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
}
