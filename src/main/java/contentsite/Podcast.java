package contentsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Podcast implements Content {

    private String title;
    private List<String> speakers;
    private List<User> users = new ArrayList<>();

    public Podcast(String title, List<String> speakers) {
        this.title = title;
        this.speakers = speakers;
    }

    @Override
    public boolean isPremiumContent() {
        return false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(users);
    }

    @Override
    public void click(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return title.equals(podcast.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public List<String> getSpeakers() {
        return speakers;
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }
}
