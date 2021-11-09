package com.codingdojo.controllers;



import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.models.User;
import com.codingdojo.services.UserService;


@Controller
public class UserController {

    private final UserService userService;
  
    
    public UserController ( UserService userService ) {
        this.userService = userService;
       
    }
    

    
    //render login page
    @RequestMapping( value="/login", method=RequestMethod.GET )
    public String login() {
        return "redirect:/registration";
    }
    
    //Create User
	@RequestMapping( value = "/registration", method = RequestMethod.POST )
	public String registerUser( 
								@RequestParam(value="first_name") String first_name,
								@RequestParam(value="last_name") String last_name,
								@RequestParam(value="email") String email,
								@RequestParam(value="address") String address,
								@RequestParam(value="state") String state,
								@RequestParam(value="city") String city,
								@RequestParam(value="zipcode") int zipcode,
								@RequestParam(value="password") String password,
								@RequestParam(value="passwordConfirmation") String passwordConfirmation,
								RedirectAttributes redirectAttributes,
								HttpSession session) {
		
		if( first_name.equals("") || last_name.equals("") || email.equals("") || password.equals("") || passwordConfirmation.equals("") ) {
			redirectAttributes.addFlashAttribute("errorMessage", "Ups! You need to fill all the spaces" );
			return "redirect:/";
		}
		
		//get email from form and validate
		User currentUser = userService.findByEmail( email );
		
		
		if( currentUser != null ) {
			redirectAttributes.addFlashAttribute("errorMessage", "That user email already exists!");
			return "redirect:/";
		}
		else {
			if( ! password.equals( passwordConfirmation ) ) {
				redirectAttributes.addFlashAttribute("errorMessage", "Password and the confirmation do not match");
				return "redirect:/";	
			}
			
			//if validation is correct create newUser
			else {
				User newUser = new User( first_name, last_name, email, address, state, city, zipcode, password );
				//save newUser
				userService.registerUser( newUser );
				System.out.println(  first_name + " " + last_name + " " + email + " " + password );
				//session.setAttribute( "userId", newUser.getId() );
				session.setAttribute( "firstName", first_name );
				session.setAttribute( "lastName", last_name );
				session.setAttribute("userId", newUser.getId());
				return "redirect:/home";
			}
		}
	}
    
	//Validate login user
	@RequestMapping( value = "/loginValidation", method = RequestMethod.POST )
	public String login( @RequestParam(value="email") String email,
						 @RequestParam(value="password") String password,
						 RedirectAttributes redirectAttributes,
						 HttpSession session) {
		
        boolean authentication = userService.authenticateUser(email, password);
        
        if( email.equals("") || password.equals("") ) {
			redirectAttributes.addFlashAttribute("error", "Ups! You need to fill all the spaces" );
			return "redirect:/";
		}
        else {
        	if( authentication ) {
        		User currentUser = userService.findByEmail(email);
        		session.setAttribute( "userId", currentUser.getId() );
        		return "redirect:/home";
        	}
        	else {
        		redirectAttributes.addFlashAttribute("error2", "Invalid Credentials. Try Again!" );
        		System.out.println( "error2 Invalid Credentials. Try Again!" );
    			return "redirect:/";
        	}
        }
	}

	
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}//
