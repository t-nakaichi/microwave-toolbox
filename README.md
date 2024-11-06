The SNAP Microwave Toolbox
======================

[![Build Status](https://travis-ci.org/senbox-org/s1tbx.svg?branch=master)](https://travis-ci.org/senbox-org/s1tbx) 
[![Coverity Scan Status](https://scan.coverity.com/projects/7248/badge.svg)](https://scan.coverity.com/projects/senbox-org-s1tbx)

The project page of SNAP and the sentinel toolboxes can be found at http://step.esa.int. There you will find a tutorial about the usage of the application, a forum where you can ask questions and lots of other interesting things.

Building Microwave Toolbox from the source
------------------------------

1. Download and install the required build tools
	* Install JAVA JDK 11 and set JAVA_HOME accordingly. 
	* Install Maven and set MAVEN_HOME accordingly. 
	* Install git
2. Add `$JAVA_HOME/bin` and `$MAVEN_HOME/bin` to your PATH.

3. Clone the Microwave Toolbox source code and related repositories into SNAP/

    ```
    git clone https://github.com/senbox-org/microwave-toolbox.git
    git clone https://github.com/senbox-org/snap-desktop.git
    git clone https://github.com/senbox-org/snap-engine.git
    git clone https://github.com/senbox-org/snap-installer.git
    ```
	
4. CD into SNAP/snap-engine:

   `mvn clean install`

5. CD into SNAP/snap-desktop:

   `mvn clean install`

6. CD into SNAP/microwave-toolbox

   `mvn clean install`
   
7. If unit tests are failing, you can use the following to skip the tests
   
   `mvn clean install -Dmaven.test.skip=true`

Setting up IntelliJ IDEA
------------------------

1. Create an empty project with the `SNAP/` directory as project directory

2. Import the pom.xml files of snap-engine, snap-desktop and microwave-toolbox as modules. Ensure **not** to enable
the option *'Create module groups for multi-module Maven projects'*. Everything can be default values.

3. Set the used SDK for the main project. A JDK 11 or later is needed.

4. Use the following configuration to run SNAP in the IDE:
	* **Main class:** `org.esa.snap.nbexec.Launcher`
	* **VM parameters:** `-Dsun.awt.nopixfmt=true -Dsun.java2d.noddraw=true -Dsun.java2d.dpiaware=false`
	All VM parameters are optional
    * **Program arguments:** 
    `--clusters "E:\build\SNAP\s1tbx\s1tbx-kit\target\netbeans_clusters\s1tbx";"E:\build\SNAP\s1tbx\s1tbx-kit\target\netbeans_clusters\rstb" --patches "E:\build\SNAP\snap-engine\$\target\classes";"E:\build\SNAP\s1tbx\$\target\classes";"E:\build\SNAP\s1tbx\rstb\$\target\classes"`
    
	* **Working directory:** `SNAP/snap-desktop/snap-application/target/snap/`
	* **Use classpath of module:** `snap-main`

Contributing
------------

    Fork it on github ( https://github.com/senbox-org/s1tbx/fork )
    Clone it locally (git clone https://github.com/senbox-org/microwave-toolbox.git)
    Create your feature branch (git checkout -b my-new-feature)
    Commit your changes (git commit -am 'Add some feature')
    Push to the branch (git push origin my-new-feature)
    Create a new Pull Request on github
    
    
Enjoy!
