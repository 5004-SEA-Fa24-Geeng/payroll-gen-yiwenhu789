# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for?
   
    Comma-Separated Values which usually used to store tabular data.

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

    It provides abstraction as many class that implements IEmployee can all be stored into the list.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

    This is called "has-a" relationship.

4. Can you provide an example of a has-a relationship in your code (if one exists)?
    
    The PayStub has a IEmployee.

5. Can you provide an example of an is-a relationship in your code (if one exists)?

    The HourlyEmployee is a IEmployee.

6. What is the difference between an interface and an abstract class?

    Interface is unimplemented while abstract class is partially implemented.

7. What is the advantage of using an interface over an abstract class?

   A class can implement multiple interfaces but can inherit only one abstract class.

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 

    It is not valid, because int is a primitive type not an object. Instead, we should use `List<Integer> numbers = new ArrayList<>();`

9. Which class/method is described as the "driver" for your application? 

    It is the main method inside the PayrollGenerator class.

10. How do you create a temporary folder for JUnit Testing? 

    We can use the `@TempDir` annotation in JUnit 5 or `TemporaryFolder` rule in JUnit 4.

## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

I may want to add a new column that describes the gender of each employees, so that we can visualize the relations with the pay and their gender more clearly. Such visualization allows employers easily get any differences in benefits, bonuses, and pre-tax deductions so that they can make changes in advance to ensure fair compensation rather than relying on post-pay survey.