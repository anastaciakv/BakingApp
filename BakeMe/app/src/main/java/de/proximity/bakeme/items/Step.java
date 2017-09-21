package de.proximity.bakeme.items;

import org.parceler.Parcel;

@Parcel
public class Step {
    public int id;
    public String shortDescription;
    public String description;
    public String videoURL;

    public Step(int id, String shortDescription, String description) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public String thumbnailURL;

    public Step() {
    }

    public boolean hasVideo() {
        return videoURL != null && !videoURL.isEmpty();
    }
}
