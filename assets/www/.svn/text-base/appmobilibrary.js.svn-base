
/*
 * Temporary Scope to contain the plugin.
 *  - More information here:
 *     https://github.com/apache/incubator-cordova-ios/blob/master/guides/Cordova%20Plugin%20Upgrade%20Guide.md
 */
(function(){

	/* Get local ref to global PhoneGap/Cordova/cordova object for exec function.
		- This increases the compatibility of the plugin. */
	var cordovaRef = window.PhoneGap || window.Cordova || window.cordova; // old to new fallbacks

 	/*
  	 * This class exposes the AppMobiLibrary functionality to JavaScript
  	*/
	AppMobiLibrary = function() {
	}
 
	/**
 	 * Intializes the AppMobiLibrary. Your application needs registered at apphub.appmobi.com
	 */
	AppMobiLibrary.prototype.initialize = function(app, rel) {
	    cordovaRef.exec(null, null, "AppMobiLibrary", "initialize", [app, rel]);
	};

	cordovaRef.addConstructor(function() {
		if(!window.plugins) {
			window.plugins = {};
		}
		if(!window.plugins.amlibrary) {
    		window.plugins.amlibrary = new AppMobiLibrary();
		}
	});
	
})(); /* End of Temporary Scope. */
