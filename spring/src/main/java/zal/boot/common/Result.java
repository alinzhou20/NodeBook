package zal.boot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*封装的一个用于前后端交互的结果类
* 似乎不一定要实现序列化
* 泛型和lombok之间有冲突*/
@Data //得到所有属性的get 和 set
@NoArgsConstructor //得到无参构造器
@AllArgsConstructor //得到所有属性的的构造器
public class Result{
    private String code; //用于标识前端请求的情况 200 为正常
    private String msg;
    private Object data; //最好使用泛型，用于存储实际的数据

    /*成功*/
    public static Result success(){
        Result res = new Result();
        res.setCode("0");
        res.setMsg("成功");
        return res;
    }

    public static Result success(Object data){
       return new Result("0","成功",data);
    }

    /*失败*/
    public static Result error() {
        return new Result("1", "系统错误", null);
    }
}
