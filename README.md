# WkJspToPdf
Converts HTML or JSPs to PDFs

Supports Tables with Paging (Repeating Headers

Multi Language and Font support

Supports images and graphics

Runs Javascript and Ajax calls if needed and won't render until complete.

Setup:

Install WKHTMLTOPDF on host machine

You will need to make sure the the command 'wkhtmltopdf' is available from the command line. So add that to your path if needed after installing. 

Load Maven Project into IDE

mvn clean install on root (Requires Java 8)

Using Tomcat 8 or higher, load the war into your server and you are ready to go!

I know that there is bower configuration and some nodeJS stuff but you shouldn't need to use any of that to get started. 

If you are setting it up in intellij follow these steps: 

1. Open the project as a Maven Project

	a. Windows: Press CTRL+ALT+SHIFT+S or Mac: Command + ;

	  i. Once the project settings window is open select the correct JDK

	b. You can now do a clean install on the project from the Maven Projects tab on the right side of the screen.

	  i. If the Maven Projects tab is not there click the bottom left corner to open the menus.

2. Download Tomcat 8 or higher (if you don't already have it) and put it in a folder where you can access it later.

3. Create a new local tomcat configuration

	a. Click "Edit Configuration" in the dropdown on the top right

	b. Click the green plus sign '+'


	c. Expand the configuration options and find Tomcat and click tomcat -> local
	d. Click "Configure" on the main configuration page

	e. Select the unzipped tomcat 8 folder you created as tomcat home and base

	f. Click the 'Deployment' tab

	  i. Click the green plus sign '+' and add WAR:EXPLODED

	  ii. Click okay

	g. Make sure the port is correct (I use 8080 and 1099) but you can use any free ports

	h. Click okay and close the configuration

4. From the top right dropdown select your new configuration and click the green play button (Or Debugger button if you preffer)

5. Go to http://localhost:8080/pdf


The site should be working now. Thank You

