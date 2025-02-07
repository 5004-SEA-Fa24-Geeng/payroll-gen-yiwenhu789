# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
classDiagram
    class PayrollGenerator {
        - DEFAULT_EMPLOYEE_FILE : String
        - DEFAULT_PAYROLL_FILE : String
        - DEFAULT_TIME_CARD_FILE : String
        + main(String[] args) : void
    }

    class FileUtil {
        + readFileToList(String file) : List<String>
        + writeFile(String outFile, List<String> lines)
    }

    class Builder {
        + buildEmployeeFromCSV(String csv) : IEmployee
        + buildTimeCardFromCSV(String csv) : ITimeCard
    }

    class IEmployee {
        <<interface>>
        + getName() : String
        + getID() : String
        + getPayRate() : double
        + getEmployeeType() : String
        + getYTDEarnings() : double
        + getYTDTaxesPaid() : double
        + getPretaxDeductions() : double
        + runPayroll(double hoursWorked) : IPayStub
        + toCSV() : String
    }

    class HourlyEmployee {
        - name : String
        - id : String
        - payRate : double
        - ytdEarnings : double
        - ytdTaxesPaid : double
        - pretaxDeductions : double
        + getEmployeeType() : String
        + runPayroll(double hoursWorked) : IPayStub
        + toCSV() : String
    }

    class SalaryEmployee {
        - name : String
        - id : String
        - payRate : double
        - ytdEarnings : double
        - ytdTaxesPaid : double
        - pretaxDeductions : double
        + getEmployeeType() : String
        + runPayroll(double hoursWorked) : IPayStub
        + toCSV() : String
    }

    class IPayStub {
        <<interface>>
        + getPay() : double
        + getTaxesPaid() : double
        + toCSV() : String
    }

    class ITimeCard {
        <<interface>>
        + getEmployeeID() : String
        + getHoursWorked() : double
    }

    PayrollGenerator --> FileUtil : uses
    PayrollGenerator --> Builder : uses
    PayrollGenerator --> IEmployee : interacts with
    PayrollGenerator --> ITimeCard : interacts with
    PayrollGenerator --> IPayStub : generates
    Builder --> IEmployee : builds
    Builder --> ITimeCard : builds
    IEmployee <|-- HourlyEmployee
    IEmployee <|-- SalaryEmployee
```



## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. Test that the `TimeCard` class properly returns `employeeId` from `getEmployeeId()`
4. Test that the `TimeCard` class properly returns `hoursWorked` from `getHoursWorked()`


## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.
```mermaid
classDiagram
    class PayrollGenerator {
        - DEFAULT_EMPLOYEE_FILE : String
        - DEFAULT_PAYROLL_FILE : String
        - DEFAULT_TIME_CARD_FILE : String
        + main(String[] args) : void
    }

    class FileUtil {
        + readFileToList(String file) : List<String>
        + writeFile(String outFile, List<String> lines)
    }

    class Builder {
        + buildEmployeeFromCSV(String csv) : IEmployee
        + buildTimeCardFromCSV(String csv) : ITimeCard
    }

    class IEmployee {
        <<interface>>
        + getName() : String
        + getID() : String
        + getPayRate() : double
        + getEmployeeType() : String
        + getYTDEarnings() : double
        + getYTDTaxesPaid() : double
        + getPretaxDeductions() : double
        + runPayroll(double hoursWorked) : IPayStub
        + toCSV() : String
    }

    class HourlyEmployee {
        - name : String
        - id : String
        - payRate : double
        - ytdEarnings : double
        - ytdTaxesPaid : double
        - pretaxDeductions : double
        + getEmployeeType() : String
        + runPayroll(double hoursWorked) : IPayStub
        + toCSV() : String
        - round(double value) : double
    }

    class SalaryEmployee {
        - name : String
        - id : String
        - payRate : double
        - ytdEarnings : double
        - ytdTaxesPaid : double
        - pretaxDeductions : double
        + getEmployeeType() : String
        + runPayroll(double hoursWorked) : IPayStub
        + toCSV() : String
        - round(double value) : double
    }

    class IPayStub {
        <<interface>>
        + getPay() : double
        + getTaxesPaid() : double
        + toCSV() : String
    }

    class PayStub {
        - employeeName : String
        - netPay : double
        - taxesPaid : double
        - ytdEarnings : double
        - ytdTaxesPaid : double
        + getPay() : double
        + getTaxesPaid() : double
        + toCSV() : String
        - formatDouble(double value) : String
    }

    class ITimeCard {
        <<interface>>
        + getEmployeeID() : String
        + getHoursWorked() : double
    }

    class TimeCard {
        - employeeID : String
        - hoursWorked : double
        + getEmployeeID() : String
        + getHoursWorked() : double
        + toCSV() : String
    }

    PayrollGenerator --> FileUtil : uses
    PayrollGenerator --> Builder : uses
    PayrollGenerator --> IEmployee : interacts with
    PayrollGenerator --> ITimeCard : interacts with
    PayrollGenerator --> IPayStub : generates
    Builder --> IEmployee : builds
    Builder --> ITimeCard : builds
    IEmployee <|-- HourlyEmployee
    IEmployee <|-- SalaryEmployee
    IPayStub <|-- PayStub
    ITimeCard <|-- TimeCard

```




## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two.

The biggest challenge is the implementation of the calculation part for the runPayroll() which takes most of the time to debug. I did not realize I had overlooked overtime different payrate until running the given test. Another major fix was updating year-to-date earnings using net pay instead of gross pay to ensure accuracy. So next time, I should devide such calculation process and come up with tests earilier.
