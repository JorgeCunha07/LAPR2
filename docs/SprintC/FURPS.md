# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

Are common across several US/UC;
are not related to US/UC, namely: Audit, Reporting and Security.
- _User authentication via password, holding seven (7) alphanumeric characters, including three (3) capital letters and two (2) digits._

- ### Audit
  - "The computational complexity analysis (of the brute-force algorithm and any sorting algorithms implemented within this application), must be accompanied by the observation of the execution time of the algorithms for inputs of variable size, in order to observe the asymptotic behavior".
- ### Reporting
  - "The Center Coordinator wants to monitor the vaccination process, to see statistics and charts, to evaluate the performance of the vaccination process, **generate reports** and analyze data from other centers, including data from law systems."
- ### Localization
  - The application supports the Portuguese and the English languages.
- ### Security
  - "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits."
  - "Only the nurses are allowed to access all user's health data."


## Usability
_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- ### Error Prevention
  - The application will use a method to catch errors, and treat them.
- ### Interface Aesthetics and Design
  - Console Application
  - "The application graphical interface is to be developed in JavaFX 11"
- ### Help and Documentation
  - "The user manual must be delivered with the application."
  - The application will have a user manual that helps the user.
  - All images should be recorded in SVG format
  -As the application will be used by several non IT related personal, it must have a simple design to avoid human misinterpretation and consequently errors._
 -Different actors will have different options on the GUI._


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._
(fill in here )

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

- _There are no other requirements regarding CPU usage, memory consumption or other hardware related requirements._
  (fill in here )
## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, instability, scalability and more._

- Adaptability:

_As the application could be commercialized by DGS, it should be adaptable enough to be used by other companies and/or organizations and/or healthcare systems._
_"As Coronavirus might not be the last pandemic in our lifetime, the application should be designed to easily support managing other future pandemic events requiring a massive vaccination of the population. 
The software application should also be conceived having in mind that it can be further commercialized to other companies and/or organizations and/or healthcare systems besides DGS."
- 
- Configurability :

_All the images/figures produced during the software development process should be recorded in
  SVG format._

Testability:
- _The team must implement unit tests for all methods, except for methods that implement Input/Output operations._


## 

### Design Constraints

Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- _The application's graphical user interface (GUI) must be developed through JavaFX11_
- _The application code must adopt recognized coding standards_
- 
- ## Mandatory standards/patterns:
- GRASP
- SOLID
- Modularity
- Tell, Don't Ask (Principle)
- Responsibility-Driven Design (RDD)
- OO software analysis and design practices
  
- ## Development tools:
  - PlantUML
  - Visual Paradigm
  - IntelliJ IDE or NetBeans
  - JaCoCo puglin
  - JUnit Framework


### Implementation Constraints

Specifies or constraints the code or construction of a system
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- #### Mandatory standards/patterns:
  - CamelCase -> The developing team must adopt recognized coding standards
  - The application should use object serialization to ensure data persistence between two runs of the application.
  - Javadoc will be used to generate useful documentation.
  - 
- #### Implementation languages:
  - The application will be developed in Java language.
  - 
- #### Java Plugins:
  - JaCoCo Plugin
  - The unit tests should be implemented using the JUnit 5Framework



### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._
(fill in here )

### Physical Constraints

- _Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._
- (fill in here )