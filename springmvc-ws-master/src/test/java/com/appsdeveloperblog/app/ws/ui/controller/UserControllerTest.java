package com.appsdeveloperblog.app.ws.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.appsdeveloperblog.app.ws.service.impl.UserServiceImpl;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.contoller.UserController;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserServiceImpl userService;
	
	UserDto userDto;
	
	final String USER_ID = "bfhry47fhdjd7463gdh";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userDto = new UserDto();
        userDto.setFirstName("Long");
        userDto.setLastName("Vo");
        userDto.setEmail("test@test.com");
        userDto.setEmailVerificationStatus(Boolean.FALSE);
        userDto.setEmailVerificationToken(null);
        userDto.setUserId(USER_ID);
        userDto.setAddresses(getAddressesDto());
        userDto.setEncryptedPassword("123456");
		
	}

	@Test
	final void testGetUser() {
	    when(userService.getUserByUserId(anyString())).thenReturn(userDto);	
	    
	    UserRest userRest = userController.getUser(USER_ID);
	    
	    assertNotNull(userRest);
	    assertEquals(USER_ID, userRest.getUserId());
	    assertEquals(userDto.getFirstName(), userRest.getFirstName());
	    assertEquals(userDto.getLastName(), userRest.getLastName());
	    assertTrue(userDto.getAddresses().size() == userRest.getAddresses().size());
	}
	
	
	private List<AddressDTO> getAddressesDto() {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setType("shipping");
		addressDto.setCity("Hue");
		addressDto.setCountry("Viet Nam");
		addressDto.setPostalCode("500000");
		addressDto.setStreetName("28 Ly Thuong Kiet");

		AddressDTO billingAddressDto = new AddressDTO();
		billingAddressDto.setType("billling");
		billingAddressDto.setCity("Hue");
		billingAddressDto.setCountry("Viet Nam");
		billingAddressDto.setPostalCode("500000");
		billingAddressDto.setStreetName("41 Nguyen Hue");

		List<AddressDTO> addresses = new ArrayList<>();
		addresses.add(addressDto);
		addresses.add(billingAddressDto);

		return addresses;

	}

}
