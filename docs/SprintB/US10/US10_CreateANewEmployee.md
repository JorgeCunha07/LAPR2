# US 10 - As an administrator, I want to register an Employee.

## 1. Requirements Engineering


### 1.1. User Story Description


As an administrator, I want to register an Employee.
Acceptance Criteria: Each user must have a single role defined in the system. 
The "auth" component available on the repository must be reused (without modifications).




### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>An Administrator is responsible for properly configuring and managing the core information (e.g.:
type of vaccines, vaccines, vaccination centers, employees) required for this application to be
operated daily by SNS users, nurses, receptionists, etc.

>The DGS has Administrators who administer the application.

>Any Administrator uses the application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in
the vaccination process.




**From the client clarifications:**
> **Question :** 
Besides a password and a user name, what other (if any) information should the Admin use to register a new employee? Are any of them optional?

> **Answer :** 
Every Employee has only one role (Coordinator, Receptionist, Nurse).  
Employee attributes: Id (automatic), Name, address, phone number, e-mail and Citizen Card number.
All attributes are mandatory.

> **Question :**  
> What is the correct format for the employee's phone number and cc? Should we consider that these follow the portuguese format?
> **Answer :**
Consider that these two attributes follow the portuguese format;


> **Question :** Is the password generated automatically, or is it specified by the user operating the system?"
 > **Answer:**
The password should be generated automatically.

> **Question :** 
> you have already specified that the password for a user should be automatically generated. Is it the same case for an employee?
> **Answer :**
Yes. I already answered this question.

> **Question :** 
> How will the system user know their password, via email or a different way?"

> **Answer :**
I will not answer this question during Sprint B. I will not answer questions that require knowledge that will be introduced later, in the next sprints.

### 1.3. Acceptance Criteria

* **AC1:** Each user must have a single role defined in the system.
* **AC2:** The "auth" component available on the repository must be reused (without modifications).
* **AC2:** Each User only one Role.
* **AC2:** Must be on portuguese Format the Phone Number and Citizen Card Number.


### 1.4. Found out Dependencies

* There no dependency to "US10 I want to register an Employee".


### 1.5 Input and Output Data

**Automatic Data:**

* 
      * Id (automatic)


**Input Data:**

* Typed data:
      
* 

      * Name
      * Address
      * Phone number
      * Email 
      * Citizen Card number
      
	
* Selected data: 
* 
      * Role


**Output Data:**
*
      *User Role List



* Success of the operation.

### 1.6. System Sequence Diagram (SSD)

![US10_SSD](US10_SSD.svg)



### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

*One Domain Model

![US10_MD](US10_MD.svg)


### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID                                                                                    | Question: Which class is responsible for...                           | Answer                     | Justification (with patterns)                                                                                             |
|:--------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------|:---------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| Step 1 The Administrator starts to register a new employee                                        | What class interacts with the user?                                   | RegisterEmployeeUI         | Pure Fabrication, because it is not justified to assign this responsibility to any class that exists in the Domain Model. |
|                                                                                                   | Which class coordinates the US?                                       | RegisterEmployeeController | Controller                                                                                                                |
|                                                                                                   | What class creates employee?                                          | Company                    | Creator                                                                                                                   |
| Step 2 The system request data(name,address,phoneNumber,email,citizenCardNumber)                  | 							                                                               |                            |                                                                                                                           |
| Step 3 The Administrator types requested data                                                     | 	Where are the entered data stored?                                   | Employee                   | Information Expert (IE)                                                                                                   |
| Step 4 The system shows Roles and ask to select one                                               | 	                                                                     |                            |                                                                                                                           |
| Step 5 The Administrator selects a role                                                           | 	Where are the entered data stored?                                   | UserRole                   | Information Expert (IE)                                                                                                   |
| Step 6 The system validates and show all data and requests confirmation                           | What class validates the data of the New employee (local validation)? | Employee                   | IE: Employee has his own data                                                                                             |
|                                                                                                   | What class validates Employee data (global validation)?               | EmployeeStore              | IE: EmployeeStore has his own data                                                                                        |
| Step 7 The Administrator confirms the data                                                        | 	Checking if the user role exist in the system		                      | Company                    | IE.Records all user Roles                                                                                                 |
|                                                                                                   | Saves the user related to the created employee information.           | Company                    | IE. In the MD, the Company contains a list of Users                                                                       | 
|                                                                                                   | What class does the specified / created Employee keep?                | EmployeStore               | IE. In the MD, the Company contains a list of Employees                                                                   |              
| Step 8 The system records the data and informs the administrator of the success of the operation. | What class notifies?                                                  | RegisterEmployeeUI         | IE.Is responsable for the user interactions                                                                               |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Company
 * Employee
 * UserRole
 * EmployeeStore

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterEmployeeUI
* RegisterEmployeeController

Other software classes of external systems/components:

* AuthFacade

## 3.2. Sequence Diagram (SD)

![US10_SD](US10_SD.svg)

## 3.3. Class Diagram (CD)


![US10_CD](US10_CD.svg)

# 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values. 


	

**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. 



*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class CreateANewEmployeeController
          {
          rolesList=Company.getUserRoles();

          Company.createEmployee(dtoEmp);

          storeEmployee=Company.getEmployeeStore;

          pass = generatePassword();

          addUserWithRole(EmployeeDTO.getName(),EmployeeDTO.getEmail(),pass,UserRoleDTO.getroleId());
          
          Company.saveEmployee(employeeCreation);

		  }


## Class Company
          { 
          rolesList=AuthFacade.getUserRoles();

          employeeCreation=EmployeeStore.createEmployee(dtoEmp);

          name=EmployeeDTO.getName();

          email=EmployeeDTO.getEmail();

          pass=generatePassword(); 

          roleId=UserRoleDTO.getroleId();

          addUserWithRole(name,email,pass,roleId();

          saveEmployee(EmployeeCreation);
		
       }

# 6. Integration and Demo 

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain. 

Is there any way to avoid this to happen?





