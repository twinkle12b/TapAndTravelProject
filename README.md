#TapAndPayApplication
##Overview
This is a simple Java Maven project designed to simulate a simplified version of a system, where passengers tap their credit cards on card readers when boarding and alighting from buses. The fare is calculated based on the stops where they tap on and off.

##Prerequisites
Before you begin, ensure you have the following installed on your machine:

##Java Development Kit (JDK) 11 or higher
IntelliJ IDEA
Apache Maven (if not bundled with IntelliJ IDEA)
Getting Started
Step 1: Clone the Repository
First, clone the repository to your local machine using Git:
bash
Copy code
git clone  https://github.com/twinkle12b/TapAndTravelProject.git or download the source code zip file
Step 2: Open the Project in IntelliJ IDEA
Open IntelliJ IDEA.
Click on File -> Open.
Navigate to the directory where you cloned the project and select the pom.xml file.
Click Open as Project.
IntelliJ IDEA will automatically recognize the project as a Maven project and import all the necessary dependencies.

Step 3: Build the Project
To build the project, follow these steps:

Open the Maven tool window by clicking on View -> Tool Windows -> Maven.
In the Maven tool window, expand the project's Lifecycle node.
Double-click on clean to clean the project.
Double-click on install to compile the project and install the package into the local repository.
Step 4: Run the Application
To run the application, follow these steps:

Open the src/main/resources/csv/Trips.csv file.
Empty the contents of the file from the previous execution.
Save the File.
Open the src/main/java/org/travel2code/TapAndTravelApp.java file.
Right-click on the TapAndTravelApp class.
Select Run 'TapAndTravelApp.main()'.
You should see Response generated in the trips.csv for the corresponding input from taps.csv file in src/main/resources/csv/Taps.csv.

Project Structure
The project has the following structure:
pom.xml: The Maven Project Object Model file containing project configuration and dependencies.
src/main/java: The main source directory.
src/test/java: The test source directory.

Dependencies
The project uses the following dependencies:
OpenCSV 5.9 for CSV to POJO and POJO to CSV conversion.
JUnit 4.13.2, Mockito 5.4.0, ASSERTJ 3.4.1 (for testing)
These dependencies are specified in the pom.xml file.


Contact
If you have any questions or suggestions, please feel free to contact me at twinkle.bahl@gmail.com.
