package com.devops.demo.exception;

/**
 * @author DatVM2
 */
public class ErrorField {
    private String code;
    private String field;
    private String msg;
    private Object data;

    /**
     * @param field
     * @param msg
     */
    public ErrorField(String field, String msg) {
        super();
        this.field = field;
        this.msg = msg;
    }

    /**
     * @param code
     * @param field
     * @param msg
     */
    public ErrorField(String code, String field, String msg) {
        super();
        this.code = code;
        this.field = field;
        this.msg = msg;
    }

    /**
     * @param code
     * @param field
     * @param msg
     * @param data
     */
    public ErrorField(String code, String field, String msg, Object data) {
        super();
        this.code = code;
        this.field = field;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    public enum CODE_TYPE {
        UNIQUE("unique");
        private String value;

        CODE_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
