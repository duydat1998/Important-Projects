/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_User.tbl_UserDTO;

/**
 *
 * @author Nguyen Duy Dat
 */
public class AuthenticationFilter implements Filter {
    private List<String> guest;
    private List<String> user;
    private List<String> staff;
    private List<String> manager;
    private final String authenPage="authenticationPage.html";
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthenticationFilter() {
        guest= new ArrayList<>();
        String[] guestWorkspace={"login.html","login","login.action","invalid.html","",
            "try","try.action","authenticationPage.html","logout","logout.action"};
        guest.addAll(Arrays.asList(guestWorkspace));
        
        user= new ArrayList<>();
        String[] userWorkspace={"logout","logout.action","userPage.jsp","authenticationPage.html"
                ,"searchUser","searchUser.action","addToCart","login.html"
                ,"addToCart.action","","staffPage","staffPage.action","userPage","userPage.action"
                ,"addMore","addMore.action","viewCart","viewCart.action"
                ,"checkOut","checkOut.action","removeItem","removeItem.action","viewCart.jsp","try","try.action"};
        user.addAll(Arrays.asList(userWorkspace));
        
        staff= new ArrayList<>();
        String[] staffWorkspace={"logout","logout.action","staffPage.jsp","authenticationPage.html","","login.html"
        ,"searchStaff","searchStaff.action","deleteRecord","deleteRecord.action","updateStaff","updateStaff.action","insert","insert.action"
        ,"insertPage","insertPage.action","staffInsertPage.jsp","staffPage","staffPage.action","try","try.action","styles.css","utils.js"};
        staff.addAll(Arrays.asList(staffWorkspace));
        
        manager= new ArrayList<>();
        String[] managerWorkSpace={"logout","logout.action","authenticationPage.html","","login.html","try","try.action"
            ,"managerPage.jsp"};
        manager.addAll(Arrays.asList(managerWorkSpace));
        
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenticationFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        
        String uri=req.getRequestURI();
        int index1=uri.lastIndexOf("/");
        int index2=uri.lastIndexOf(";");
        String resource;
        if(index2 >0){
            resource=uri.substring(index1+1, index2);
        } else{
            resource=uri.substring(index1+1);
        }
        System.out.println("Resource: "+resource);
        HttpSession session=req.getSession(false);
        System.out.println("before-sesion not null: "+(session != null));
        if(session==null || session.getAttribute("USER")==null){
            System.out.println("guest");
            if(guest.contains(resource)){
                chain.doFilter(request, response);
            } else{
                res.sendRedirect(authenPage);
            }
        } else{
            tbl_UserDTO dto= (tbl_UserDTO) session.getAttribute("USER");
            if(dto.getRole() == 0 && user.contains(resource)){
                System.out.println("user");
                chain.doFilter(request, response);
            } else if(dto.getRole() == 2 && staff.contains(resource)){
                System.out.println("staff");
                chain.doFilter(request, response);
            } else if(dto.getRole() == 1 && manager.contains(resource)){
                System.out.println("manager");
                chain.doFilter(request, response);
            } else{
                res.sendRedirect(authenPage);
            }
        }
        System.out.println("after-sesion not null: "+(session != null));
        
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthenticationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenticationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenticationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
