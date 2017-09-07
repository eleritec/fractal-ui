package net.eleritec.fractalui.validation;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 11:00:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class ValidationMessage {
    private String key;
    private String message;
    private ValidationType type;
    private int result;

    public ValidationMessage() {

    }

    public ValidationMessage(String key) {
        this(key, (String)null);
    }

    public ValidationMessage(String key, ValidationType type) {
        this(key, null, type);
    }

    public ValidationMessage(String key, String message) {
        this(key, message, null);
    }

    public ValidationMessage(String key, String message, ValidationType type) {
        this.key = key;
        this.message = message;
        setType(type);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ValidationType getType() {
        return type;
    }

    public void setType(ValidationType type) {
        this.type = type==null? ValidationType.ERROR: type;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isError() {
        return type==ValidationType.ERROR;
    }
}
