/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastpay.fastpay.backing;

/**
 *
 * @author Iben Labaran
 */
import com.fastpay.fastpay.models.FastpayUser;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseBacking {
    
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
    protected Map getRequestMap() {
        return getContext().getExternalContext().getRequestMap();
    }
    
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getContext().getExternalContext().getRequest();
    }    
    
    protected HttpSession getSession() {
        return (HttpSession) getContext().getExternalContext().getSession(false);
    }
  
    protected Object evaluateEL(String elExpression, Class beanClazz) {
        return getContext().getApplication().evaluateExpressionGet(getContext(), elExpression, beanClazz);
    }
    
    protected void updateValueExpression(String expression, Object value) {
        
        ELContext elContext = getContext().getELContext();
        ValueExpression targetExpression = getContext().getApplication().getExpressionFactory().
                                           createValueExpression(elContext, expression, Object.class);
        
        targetExpression.setValue(elContext, value);
    }
    
    	protected FastpayUser getUser() {
		HttpSession session = (HttpSession) getContext().getExternalContext().getSession(false);
		return (FastpayUser)session.getAttribute("user");
	}

	
    public static final String SYSTEM_ERROR = "System error ...";
}
