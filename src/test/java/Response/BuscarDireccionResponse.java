package Response;

public class BuscarDireccionResponse{
	private Data data;
	private boolean success;
	private int status;
	private String email;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	public String data(String data){
		return data;
	}
}
