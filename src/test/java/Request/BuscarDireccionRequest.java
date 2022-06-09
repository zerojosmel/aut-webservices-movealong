package Request;

public class BuscarDireccionRequest{
	private String account;
	private String coin;
	private  String email;

	public void setAccount(String account){
		this.account = account;
	}

	public String getAccount(){
		return account;
	}

	public void setCoin(String coin){
		this.coin = coin;
	}

	public String getCoin(){
		return coin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}
