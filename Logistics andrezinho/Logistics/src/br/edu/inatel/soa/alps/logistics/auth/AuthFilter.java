package br.edu.inatel.soa.alps.logistics.auth;

import java.security.Principal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.SecurityContext;

import br.edu.inatel.soa.alps.logistics.dao.UserDAO;
import br.edu.inatel.soa.alps.logistics.model.User;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class AuthFilter implements ContainerRequestFilter {

	private UserDAO userDAO = new UserDAO();
	
	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) throws WebApplicationException {
		String method = containerRequest.getMethod();
		String path = containerRequest.getPath(true);

		if(method.equals("GET") && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))){
			return containerRequest;
		}
		
		String auth = containerRequest.getHeaderValue("authorization");
		
		if(auth != null){
			String[] lap = BasicAuth.decode(auth);
			if(lap != null && lap.length == 2){
				User user = userDAO.search(lap[0], lap[1]);
				containerRequest.setSecurityContext(new Authorizer(user));
			}
		}
		
		return containerRequest;
	}
	
	public class Authorizer implements SecurityContext {
	    private User user;
	    private Principal principal;

	    public Authorizer(final User user) {
	        this.user = user;
	        this.principal = new Principal() {
	            public String getName() {
	                return user == null ? "" : user.getUserName();
	            }
	        };
	    }

	    public Principal getUserPrincipal() {
	        return this.principal;
	    }

	    public boolean isUserInRole(String role) {
	        return user != null && (role.equalsIgnoreCase(user.getRole()));
	    }

	    public boolean isSecure() {
	        return false;
	    }

	    public String getAuthenticationScheme() {
	        return SecurityContext.BASIC_AUTH;
	    }
	}
}
