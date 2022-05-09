package zal.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("chapter")
@Data
public class Chapter {
    @TableId(value = "chapter_id",type = IdType.AUTO)
    private Integer id;
    private Integer bookId;
    private Integer order;
    private String title;
    private String content;

}
