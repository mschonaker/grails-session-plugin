class SessionGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Your name"
    def authorEmail = ""
    def title = "Plugin summary/headline"
    def description = '''\\
Brief description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/session"

    def doWithWebDescriptor = { xml ->
	    def listeners = xml.'listener'
		
	    listeners[listeners.size()-1] + {
	    	'listener' {
	    		log.info "Adding session context listener"
	    		'listener-class'("net.processone.grailssession.SessionListener")  
	    	}  
	    }
	    
		listeners[listeners.size()-1] + {
		  'listener' {
		       log.info "Adding activation context listener"
		       'listener-class'("net.processone.grailssession.ActivationListener")  
		  }  
		}
    }

    def doWithSpring = {
    	//Adding an Spring bean to map 'net.processone.grailssession.publisher' to EventPublisher class 
   		'net.processone.grailssession.publisher'(net.processone.grailssession.EventPublisher) {
   			
   		}

    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
