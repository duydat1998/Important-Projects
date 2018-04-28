/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sample.tbl_order.tbl_orderSearchError;

/**
 *
 * @author Nguyen Duy Dat
 */
public class SearchFilter implements Filter {

    private static final boolean debug = true;
    private final String searchPage = "search.jsp";

    //private final String searchServlet = "SearchServlet";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public SearchFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SearchFilter:DoBeforeProcessing");
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
            log("SearchFilter:DoAfterProcessing");
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

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;

            String url = null;
            
            //if the request is sent from search.jsp, the value of "btAction" is search
            // else the value of "btAction" is null, and all necessary inputs to search are in session scope
            String action = req.getParameter("btAction");
            if (action != null) {
                //if user check "Deliver" button, the value of "chkDeliver" is "true"
                String isDeliverString = request.getParameter("chkDeliver");
                boolean isDeliver = false;
                if (isDeliverString != null) {
                    if (isDeliverString.equals("true")) {
                        isDeliver = true;
                    }
                }
                
                //add isDeliver to session scope for later requests
                HttpSession session = req.getSession();
                session.setAttribute("IS_DELIVER", isDeliver);
                
                String fromDateString = req.getParameter("txtFromDate");
                String toDateString = req.getParameter("txtToDate");

                Date fromUtilDate = null;
                Date toUtilDate = null;

                url = searchPage;

                boolean bError = false;

                tbl_orderSearchError errors = new tbl_orderSearchError();

                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    if (fromDateString.trim().length() == 0) {
                        errors.setRequieredFromDate("From Date is required");
                        bError = true;
                    } else if (fromDateString.contains("-")) {
                        fromUtilDate = sdf1.parse(fromDateString);
                    } else if (fromDateString.contains("/")) {
                        fromUtilDate = sdf2.parse(fromDateString);
                    } else {
                        bError = true;
                        errors.setInvalidFromDate("Search From Date must in format 'day-month-year' or 'day/month/year'");
                    }
                } catch (ParseException ex) {
                    log("SearchFilter_ParseException_fromDate: " + ex.getMessage());
                    bError = true;
                    errors.setInvalidFromDate("Search From Date must in format 'day-month-year' or 'day/month/year'");
                }

                try {
                    if (toDateString.trim().length() == 0) {
                        errors.setRequieredToDate("To Date is required");
                        bError = true;
                    } else if (toDateString.contains("-")) {
                        toUtilDate = sdf1.parse(toDateString);
                    } else if (toDateString.contains("/")) {
                        toUtilDate = sdf2.parse(toDateString);
                    } else {
                        bError = true;
                        errors.setInvalidToDate("Search To Date must in format 'day-month-year' or 'day/month/year'");
                    }
                } catch (ParseException ex) {
                    log("SearchFilter_ParseException_fromDate: " + ex.getMessage());
                    bError = true;
                    errors.setInvalidToDate("Search To Date must in format 'day-month-year' or 'day/month/year'");
                }

                if (!bError) {
                    if (fromUtilDate.after(toUtilDate)) {
                        errors.setInvalidTimeInterval("Invalid time Interval!");
                        req.setAttribute("DATE_FORMAT_ERROR", errors);
                    } else {
                        java.sql.Date fDate = new java.sql.Date(fromUtilDate.getTime());
                        java.sql.Date tDate = new java.sql.Date(toUtilDate.getTime());
                        //add fDate to session scope for later requests
                        session.setAttribute("FROM_DATE", fDate);
                        //add tDate to session scope for later requests
                        session.setAttribute("TO_DATE", tDate);
                        url = null;
                    }
                } else {
                    //when there is any error, back to search.jsp and notify to user
                    req.setAttribute("DATE_FORMAT_ERROR", errors);
                }
            }

            if (url != null) {
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(req, response);
            } else {
                //move to the next filter in the chain
                chain.doFilter(req, response);
            }
        } catch (Throwable t) {
            log("SearchFilter_Throwable: " + t.getMessage());
        }
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
                log("SearchFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SearchFilter()");
        }
        StringBuffer sb = new StringBuffer("SearchFilter(");
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
