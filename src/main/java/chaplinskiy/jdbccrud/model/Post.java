package chaplinskiy.jdbccrud.model;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String content;
    private LocalDateTime create;
    private LocalDateTime updated;

    public Post() {

    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", create=" + create +
                ", updated=" + updated +
                '}';
    }

    public Post(Long id, String content, LocalDateTime create, LocalDateTime updated) {
        this.id = id;
        this.content = content;
        this.create = create;
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

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
