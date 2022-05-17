package zal.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("writer")
@Data
public class Writer {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String nickname;
}
