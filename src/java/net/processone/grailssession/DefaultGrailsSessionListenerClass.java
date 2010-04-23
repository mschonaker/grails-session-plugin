package net.processone.grailssession;

import groovy.lang.Closure;
import org.codehaus.groovy.grails.commons.AbstractInjectableGrailsClass;
import org.codehaus.groovy.grails.commons.GrailsClassUtils;

import java.util.HashMap;
import java.util.Map;

import grails.util.GrailsUtil;

public class DefaultGrailsSessionListenerClass extends
		AbstractInjectableGrailsClass implements GrailsSessionListenerClass {

	public static final String SL = "SessionListener";
	 
    public DefaultGrailsSessionListenerClass(Class clazz) {
        super(clazz, SL);
    }
}
