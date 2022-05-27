package pojo;

import lombok.Data;

@Data
public class Chapter {
    private Integer id;
    private Integer bookId;
    private Integer list;
    private String title;
    private String content;
    private String price;

    public Chapter() {}
    public Chapter(Integer id, Integer list, String title) {
        this.id = id;
        this.list = list;
        this.title = title;
    }
}
