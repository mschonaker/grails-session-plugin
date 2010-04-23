package net.processone.grailssession;

import org.codehaus.groovy.grails.commons.ArtefactHandlerAdapter;

public class SessionListenerArtefactHandler extends ArtefactHandlerAdapter {

    public static final String TYPE = "SessionListener";

    public SessionListenerArtefactHandler() {
        super(TYPE, GrailsSessionListenerClass.class, DefaultGrailsSessionListenerClass.class, null);
    }

    public boolean isArtefactClass(Class clazz) {
        if(clazz == null || !clazz.getName().endsWith("SessionListener")) return false;
        
        return true;
    }
}

