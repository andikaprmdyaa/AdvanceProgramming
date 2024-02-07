#First Advance Programming Tutorial

##By Andika Pramudya Wardana

###Reflection 1

In the code that i implement, I've applied principles like using meaningful variable names, simplifying code structure, and avoiding null pointers. However, there are areas for improvement, such as implementing error handling, validating input, and writing unit tests to ensure the code's reliability and robustness. 

###Reflection 2

1. After writing the unit test, I usually feel a sense of accomplishment and reassurance knowing that my code is being thoroughly checked for correctness. It gives me confidence that my program behaves as expected under different scenarios.

    Regarding the number of unit tests, there isn't a specific rule, but generally, I aim to cover all possible use cases and edge cases in a class. However, it's essential to prioritize writing tests for critical and complex parts of the codebase.

    To ensure that our unit tests are sufficient to verify our program, we can use code coverage tools. Code coverage measures the percentage of code that is executed by our tests. While 100% code coverage is a good goal to strive for, it doesn't necessarily mean our code is bug-free. It simply means that all lines of code have been executed at least once during testing.

    Having 100% code coverage doesn't guarantee the absence of bugs or errors because:
        1. It doesn't guarantee that all possible inputs and edge cases have been tested.
        2. It doesn't consider the quality or effectiveness of the tests. Tests may not catch all possible issues even if they cover all lines of code.
        3. It doesn't account for logical errors or incorrect assumptions in the code.

    Therefore, while code coverage is a helpful metric for assessing the thoroughness of our tests, it should be used in conjunction with other testing techniques, such as boundary testing, equivalence partitioning, and exploratory testing, to ensure the reliability and robustness of our software.

2. I believe that creating a new functional test suite to verify the number of items in the product list is a logical step in ensuring comprehensive testing coverage. However, duplicating setup procedures and instance variables across multiple test suites can lead to code redundancy and decreased maintainability. This duplication may indeed reduce the overall code quality by increasing the risk of inconsistencies and making future modifications more challenging. To improve the cleanliness of the code, I would suggest refactoring common setup procedures and instance variables into shared utility methods or a base test class that all functional test suites can inherit from. This approach would promote code reuse, reduce duplication, and enhance maintainability while ensuring consistency across test suites. Additionally, employing meaningful variable names, adhering to coding standards, and providing clear comments can further enhance the readability and cleanliness of the codebase.
