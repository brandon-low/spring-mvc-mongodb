package hello.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
public class Address {

	
	@Id
	private String _id;

	// internationalised in the messages
	@Size(min=5, max=20, message="{address.name.size}")
	private String name;
	
	private String address;
	private String phone;
	
	@NotEmpty(message = "{address.email.required}")
	@Email(message ="Invalid email address")
	private String email;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Address [_id=" + _id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
				+ email + "]";
	}

}
