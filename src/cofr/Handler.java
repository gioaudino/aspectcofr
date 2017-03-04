package cofr;

public abstract class Handler {
	public static final String STATUS_OK = "OK";
	public static final String STATUS_ERROR = "ERROR";
	public static final String STATUS_NOT_HANDLED = "NOT_HANDLED";
	public static final String STATUS_MUST_HANDLE = "MUST_HANDLE";

	protected String name;
	protected Handler next;

	public Handler(){
		name = this.getClass().getName();
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNext(Handler next) {
		this.next = next;
	}

	protected abstract boolean canHandle(Request request);

	public Response handleRequest(Request request) {
		System.out.println("+++++");
		if (this.canHandle(request)) {
			return this.doHandle(request);
		}
		if (this.next == null) {
			return this.mustHandle(request);
		}
		return next.handleRequest(request);
	}

	protected abstract Response doHandle(Request request);

	protected abstract Response mustHandle(Request request);
}
