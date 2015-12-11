package br.com.ruianderson.util.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ruianderson.modelo.Login;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter(filterName = "FiltroLogin", urlPatterns = {"/participante/*","/evento/*",
		"/restrito/*","/mobile/homep.xhtml","/mobile/filap.xhtml", "/mobile/buscamusica.xhtml",
		"/mobile/noticiaprincipal.xhtml","/mobile/noticia1.xhtml","/mobile/noticia2.xhtml",
		"/mobile/alterarsenha.xhtml","/mobile/noticia3.xhtml"})
public class Filtro implements Filter {

    /**
     * Default constructor. 
     */
    public Filtro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		 HttpServletRequest req = (HttpServletRequest) request;
	     HttpServletResponse res = (HttpServletResponse) response;
	        
	        Login usrfil  = (Login) req.getSession().getAttribute("usuariologado");
	        
	        if(usrfil != null){
	            
	            chain.doFilter(request, response);
	            
	        }else{
	            
	            //res.sendRedirect("../index.xhtml");
	        	res.sendRedirect("../mobile/loginp2.xhtml");
	        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
