package Network;

public class ResourceNotAvalaibleException extends Exception {

    private String notFoundResource;

    public ResourceNotAvalaibleException(String message,String notFoundResource){
        super(message);
        this.notFoundResource = notFoundResource;
    }

    public String getNotFounResource(){
        return notFoundResource;
    }
}
