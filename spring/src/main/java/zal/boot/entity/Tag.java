package zal.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("book_tag")
@Data
public class Tag {
    @TableId(value = "tag_id",type = IdType.AUTO)
    private Integer id;
    private String name;
}
