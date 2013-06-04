package com.esprit.km.managedBean;

import java.io.Serializable;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.event.RowEditEvent;

import com.esprit.km.domain.User;
import com.esprit.km.service.UserService;

@ManagedBean
@SessionScoped
public class UserCtrl implements Serializable {


	private static final long serialVersionUID = 1L;
	
	UserService userDAO;
	private User user;
	private List<User> users;
	private User selectedUser;
	private List<User> filteredUsers;
	
	
	
	
	public UserCtrl() {
		super();
		
		user = new User();
		
		try {
			userDAO = (UserService) new InitialContext().lookup("java:global/KM-portlet/UserService!com.esprit.km.service.UserService");
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<User> getUsers() {
		users = userDAO.getAll();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String ajouter() {
		
		/* try {
			userDAO = (UserService) new InitialContext().lookup("java:global/KM-portlet/UserService!com.esprit.km.service.UserService");
		} catch (NamingException e1) {
			e1.printStackTrace();
		} */
		
		userDAO.addUser(user);
		
		return "Add-success";
		
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	/*public List<User> lister(){
		
		 
		return userDAO.getAll();
	}
*/
	
	public void onEdit(RowEditEvent event){
		
		User nutzer = (User) event.getObject();                 

	    userDAO.editUser(nutzer);

	    
	}
	
	//public void onCancel(RowEditEvent event){}
}
