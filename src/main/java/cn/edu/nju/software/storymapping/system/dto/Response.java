package cn.edu.nju.software.storymapping.system.dto;


public class Response {
	public static class ResponseStatus {
        public static final Integer SUCCESS = 0;
        public static final Integer FAIL = 1;
        public static final Integer REDIRECT = 2;
        public static final Integer NOAUTH = 3;
    }

    private Integer status;
    private String message;
    private Object result;

    public static Response createDefaultResponse() {
        Response res = new Response();
        res.setStatus(ResponseStatus.FAIL);
        res.setMessage("default.as.fail");
        res.setResult("default.as.fail");
        return res;
    }
    
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }
    
    public Response fail(String message) {
        this.status = ResponseStatus.FAIL;
        this.message = message;
        this.result = null;
        return this;
    }
       
    public Response redirect(String message) {
        this.status = ResponseStatus.REDIRECT;
        this.message = message;
        this.result = null;
        return this;
    }
    
    public Response noauth(String message) {
        this.status = ResponseStatus.NOAUTH;
        this.message = message;
        this.result = null;
        return this;
    }
    
    public Response success(Object result) {
        this.status = ResponseStatus.SUCCESS;
        this.message = "success";
        this.result = result;
        return this;
    }

}
