package contentsite;

import java.util.HashSet;
import java.util.Set;

public class ContentService {

    private Set<User> allUsers = new HashSet<>();
    private Set<Content> allContent = new HashSet<>();

    void registerUser(String name, String password) {
        validateUserName(name);
        allUsers.add(new User(name, password));
    }

    private void validateUserName(String name) {
        if (allUsers.stream().anyMatch(user -> name.equals(user.getUserName()))) {
            throw new IllegalArgumentException("Username is already taken: " + name);
        }
    }

    void addContent(Content content) {
        validateContent(content);
        allContent.add(content);
    }

    private void validateContent(Content content) {
        if (allContent.stream().anyMatch(c -> c.equals(content))) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
    }

    public void logIn(String username, String password) {
        int hash = (username + password).hashCode();
        if (allUsers.stream().noneMatch(user -> username.equals(user.getUserName()))) {
            throw new IllegalArgumentException("Username is wrong!");
        }
        allUsers.stream()
                .filter(user -> user.getPassword() == hash)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Password is Invalid!"))
                .setLogIn(true);
    }

    public void clickOnContent(User user, Content content) {
        if (!findUser(user).isLogIn()) {
            throw new IllegalStateException("Log in to watch this content!");
        }
        if (content.isPremiumContent() && !findUser(user).isPremiumMember()) {
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }
        content.click(user);
    }

    private User findUser(User user) {
        return allUsers.stream().filter(user::equals).findFirst().orElseThrow(() -> new IllegalStateException("No such user!"));
    }

    public Set<User> getAllUsers() {
        return Set.copyOf(allUsers);
    }

    public Set<Content> getAllContent() {
        return Set.copyOf(allContent);
    }
}
