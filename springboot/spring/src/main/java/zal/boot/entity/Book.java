package zal.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("book")
@Data
public class Book {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer writerId;
    private String writer;
    private Integer tagId;
    private String tag;
    private String imgUrl;

    private List<Chapter> chapters;

}
