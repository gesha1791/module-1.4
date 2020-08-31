package chaplinskiy.crud.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private Long id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Post(){
    }

    public Post(String content, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(content, post.content) &&
                Objects.equals(created, post.created) &&
                Objects.equals(updated, post.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, created, updated);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
