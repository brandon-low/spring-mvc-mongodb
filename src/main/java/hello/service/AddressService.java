package hello.service;

import java.util.List;

import hello.entity.Address;

//import com.osintegrators.example.Address;

public interface AddressService {

	void createAddress(Address add);

	void deleteAddress(String id);

	List<Address> getAllAddresses();

	Address getAddressById(String id);

	void updateAddress(Address address);

}
