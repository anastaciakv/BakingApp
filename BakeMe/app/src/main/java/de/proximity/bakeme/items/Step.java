package de.proximity.bakeme.items;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel
public class Step extends RealmObject {
    @PrimaryKey
    public int id;
    public String shortDescription;
    public String description;
    public String videoURL;
    public String thumbnailURL;

    public boolean hasVideo() {
        return videoURL != null && !videoURL.isEmpty();
    }
}
