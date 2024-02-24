# First Advance Programming Tutorial

## By Andika Pramudya Wardana

### Reflection 1

In the code that i implement, I've applied principles like using meaningful variable names, simplifying code structure, and avoiding null pointers. However, there are areas for improvement, such as implementing error handling, validating input, and writing unit tests to ensure the code's reliability and robustness. 

### Reflection 2

1. After writing the unit test, I usually feel a sense of accomplishment and reassurance knowing that my code is being thoroughly checked for correctness. It gives me confidence that my program behaves as expected under different scenarios.

    Regarding the number of unit tests, there isn't a specific rule, but generally, I aim to cover all possible use cases and edge cases in a class. However, it's essential to prioritize writing tests for critical and complex parts of the codebase.

    To ensure that our unit tests are sufficient to verify our program, we can use code coverage tools. Code coverage measures the percentage of code that is executed by our tests. While 100% code coverage is a good goal to strive for, it doesn't necessarily mean our code is bug-free. It simply means that all lines of code have been executed at least once during testing.

    ### Having 100% code coverage doesn't guarantee the absence of bugs or errors because:
        1. It doesn't guarantee that all possible inputs and edge cases have been tested.
        2. It doesn't consider the quality or effectiveness of the tests. Tests may not catch all possible issues even if they cover all lines of code.
        3. It doesn't account for logical errors or incorrect assumptions in the code.

    Therefore, while code coverage is a helpful metric for assessing the thoroughness of our tests, it should be used in conjunction with other testing techniques, such as boundary testing, equivalence partitioning, and exploratory testing, to ensure the reliability and robustness of our software.

2. I believe that creating a new functional test suite to verify the number of items in the product list is a logical step in ensuring comprehensive testing coverage. However, duplicating setup procedures and instance variables across multiple test suites can lead to code redundancy and decreased maintainability. This duplication may indeed reduce the overall code quality by increasing the risk of inconsistencies and making future modifications more challenging. To improve the cleanliness of the code, I would suggest refactoring common setup procedures and instance variables into shared utility methods or a base test class that all functional test suites can inherit from. This approach would promote code reuse, reduce duplication, and enhance maintainability while ensuring consistency across test suites. Additionally, employing meaningful variable names, adhering to coding standards, and providing clear comments can further enhance the readability and cleanliness of the codebase.

### Reflection 3
1) Explain what principles you apply to your project!
   1. The Single Responsibility Principle (SRP) states that a class should focus on a single responsibility to be easier to maintain. I have applied this to my code so that no class deviates from its focus. Additionally, I have created a findById method that was previously missing so that the edit and delete functions no longer need to loop through, but simply use the findById function.
   2. The Open/Closed Principle (OCP) states that classes, modules, functions, etc., should be open for extension but closed for modification. I have applied this by using CarService (interface) and CarServiceImpl, so that necessary changes can be made in CarServiceImpl without modifying the interface. The same applies to ProductService and ProductServiceImpl
   3. The Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program. This has been applied by separating the CarController class from the ProductController. Previously, if CarController extended ProductController, I removed it because the functions within them were not replaceable since Product and Car are different.
   4. The Interface Segregation Principle (ISP) states that interfaces should be specific to the client's needs. This is applied to CarService and ProductService, which are separated interfaces focusing on their respective objects
   5. The Dependency Inversion Principle (DIP) states that high-level modules or classes should not depend on low-level modules or classes. I have implemented this in the CarController, which initially depended on CarServiceImpl, a concrete class, and changed it to depend on CarService, an interface. This was done to loosen the coupling between modules

2) Explain the advantages of applying SOLID principles to your project with examples.
   - The code becomes easier to read and maintain because of SRP; each class focuses on its own purpose, resulting in small, simple methods that do complex tasks
   - The code becomes more flexible and can be extended without changing too much source code.
   - Testing also becomes easier because each function has a clear and focused purpose

3) Explain the disadvantages of not applying SOLID principles to your project with examples.
   - It requires more effort to understand the code as a whole, and usually only the code author truly understands the code's intentions.
   - It's less flexible because changes to one function can affect the entire program
   - Testing and bug hunting become more difficult because there's a lot of unstructured and messy code
