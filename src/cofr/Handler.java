package cofr;

public abstract class Handler implements HandlerI {
	protected String name;
	protected HandlerI next;

	public Handler(){
		name = this.getClass().getName();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setNext(HandlerI next) {
		this.next = next;
	}

	protected abstract boolean canHandle(Request request);

	@Override
	public Response handleRequest(Request request) {
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
