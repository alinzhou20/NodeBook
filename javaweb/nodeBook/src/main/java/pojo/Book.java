package pojo;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String name;
    private Integer writerId;
    private String writer;
    private Integer tagId;
    private String tag;
    private String imgUrl;

    public Book(Integer id, String name, String writer, String tag, String imgUrl) {
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.tag = tag;
        this.imgUrl = imgUrl;
    }

    public Book(){}
}
