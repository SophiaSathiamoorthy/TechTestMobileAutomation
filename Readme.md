# TechTest Mobile Automation

## Pre-requisites

-   Appium 1.15.1
- [Java 11](https://installvirtual.com/install-openjdk-11-mac-using-brew/)
-   Maven 3.5.4
-  Eclipse/IntelliJ IDE with Cucumber plugin installed
-  Android Studio
-  Git

For iOS
- Xcode


## Setup
With macOS Catalina is now using Zsh as the default shell. To change the default shell
to bash by:
1. Open terminal
2. Type `chsh -s /bin/bash`
3. Reopen the terminal and you'll be using bash instead of Zsh.

### Environment variables (macOS)
Once you have changed the default shell to bash and installed all the [pre-requisites](#pre-requisites), its time to set
the environment variables. In the terminal:
1. Navigate to the home directory i.e. /Users/username
2. Make a file called `.bash_profile`.
3. Open the file `open -n .bash_profile`
4. Add paths for ANDROID_HOME, M2_HOME and JAVA_HOME. Don't forget to add them to
PATH.
5. Save and close the window and run `source .bash_profile`

Check to ensure Java and Maven have been set up correctly (you may have to restart
your terminal/mac for it to take effect.)


### IDE
1. Clone the repository from https://github.com/SophiaSathiamoorthy/TechTestMobileAutomation into intellij and
open the project in your preferred IDE.
#### Intellij
If you're using Intellij IDE, then you will need to ensure that the **Project SDK** is set
to Java 11.

## Run
In order to run the code ensure that

For Android
 - Android emulator is running
 - Appium server is running

For iOS
- iOS emulator is running
- Appium server is running

Depending on which OS (emulator)/device you are running, the values in TestNG.xml will need to be
updated accordingly.

### Command line
Once the emulator and appium server are running, from the command line navigate to the project and type the following
command to run the code:

    mvn clean install

### Test NG
If you prefer to run the tests from within the IDE, you can run them by Right-clicking on the TestNG.xml
file and selecting 'Run'.

Test NG allows you to set up the runner for single and parallel testing. In order to run the test(s)
in parallel and on multiple devices you will need the following:

#### Android
- **_udid_**:  if you don't include this capability, the driver will attempt to use the first device in the list returned by
ADB. This could result in multiple sessions targeting the same device, which is not a desirable situation. Thus it's
essential to use the udid capability, even if you're using emulators for testing (in which case the emulator looks like
emulator-55xx).
- **_systemPort_**: to communicate to the UiAutomator2 process, Appium utilizes an HTTP connection which opens up a port
on the host system as well as on the device. The port on the host system must be reserved for a single session, which
means that if you're running multiple sessions on the same host, you'll need to specify different ports here (for
example 8200 for one test thread and 8201 for another).
- **_deviceName_**: f you specify a unique combination of device name and platform version, Appium will be able to find
a unique simulator that matches your requirement.
- **_platformVersion_**: Simulator OS version

#### iOS
As well as all the above desired capabilities, you will also need the following to run test(s) on multiple iOS devices:

- **_wdaLocalPort_**: just as with UiAutomator2, the iOS XCUITest driver uses a specific port to communicate with WebDriverAgent
running on the iOS device.

#### Framework Design Approach
Behaviour driven developemnt(BDD) approach using Appium-cucumber was used to write the automation test scripts.
Page Object Model is the design pattern used to create Object Repository for web UI elements.With the help of PageFactory class, I use annotations @FindBy to find WebElement.
For UI Tests : Appium-cucumber-Maven with PageFactory Design Pattern.

#### Reports
Extent reports with the test results can be viewed on the browser .Its found in the test-output folder.

