# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

Are common across several US/UC;
are not related to US/UC, namely: Audit, Reporting and Security.
- _User authentication via password, holding seven (7) alphanumeric characters, including three (3) capital letters and two (2) digits._

### Localization

- _Portugal, as DGS is a state-funded Portuguese healthcare system responsible for vaccinating the portuguese population._
- _The application will be available on both English and Portuguese._


###Security

- _All those who use the application must authenticate themselves using a password that holds seven alphanumeric characters, including three capital letters and two digits._
- _An administrator will be responsible to configure and manage all the core information including registering centers, SNS users and so on._
- _Different actors will have different access levels, as for example, the receptionist will only have access to the information regarding the vaccination scheduling while the nurse will have access to all the information regarding the vaccination of the user and will administer the vaccine and then register the new information on the database._
- _Only the nurses are able to access all information relative to user health data_

## Usability
_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- _As the application will be used by several non IT related personal, it must have a simple design to avoid human misinterpretation and consequently errors._
- _Different actors will have different options on the GUI._


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

- _There are no other requirements regarding CPU usage, memory consumption or other hardware related requirements._

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, instability, scalability and more._

- _The application must be developed in a way so that it can support future pandemic events and consequently future mass vaccination processes._
- _As the application could be commercialized by DGS, it should be adaptable enough to be used by other companies and/or organizations and/or healthcare systems._
- _All the images/figures produced during the software development process should be recorded in
SVG format._
- _The unit tests should be implemented using the JUnit 5
framework._

## +

### Design Constraints

Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- _The application must be developed using the Java language, using either the IntelliJ IDE or NetBeans_
- _The application's graphical user interface (GUI) must be developed through JavaFX11_
- _The application code must adopt recognized coding standards_


### Implementation Constraints

Specifies or constraints the code or construction of a system
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- _The application will be developed in Java language._
- _The developers must use either IntelliJ IDE or NetBeans._
- _Javadoc will be used to generate useful documentation._
- _The developing team must adopt recognized coding standards._
- _The team must implement unit tests for all methods, except for methods that implement Input/Output operations. The tests should be implemented using  JUnit 5._
- _All the images/figures produced during the software development process should be recorded in
  SVG format._
- _The application should use object serialization to ensure data persistence between two runs of the
  application._

### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

- _The application graphical user interface will be available on both portuguese and english languages, as requested by the client._

### Physical Constraints

- _Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._