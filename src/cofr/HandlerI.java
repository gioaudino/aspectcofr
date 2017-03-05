package cofr;

public interface HandlerI {

	String STATUS_OK = "OK";
	String STATUS_ERROR = "ERROR";
	String STATUS_NOT_HANDLED = "NOT_HANDLED";
	String STATUS_MUST_HANDLE = "MUST_HANDLE";

	String getName();

	void setName(String name);

	void setNext(HandlerI next);

	Response handleRequest(Request request);

}