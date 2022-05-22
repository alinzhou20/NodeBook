package utils;

public class Result {
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

    public static Result success(String msg,Object data){
        Result res = new Result();
        res.setCode("0");
        res.setMsg(msg);
        res.setData(data);
        return res;
    }
    /*失败*/
    public static Result error(String msg) {
        return new Result("1", msg, null);
    }


    public Result() {
    }
    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
