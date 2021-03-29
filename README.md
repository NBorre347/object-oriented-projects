This repository stores my homework assignments from the course Object Oriented Software Development, a course built around creating complex Java structures
utilizing various different design patterns such as the builder and state patterns. Homework largely consisted of being given a code skeleton and being tasked
with filling in empty functions or add classes when needed. More details on each individual assignments below.

Note that testing code labeled with "Instructor" or "Ins" at the end of their name are instructor supplied tests, while tests without such components are tests
created in part or completely by me.

HW1: Given a set of java classes: Record, VideoObj, and InventorySet. Tasked with setting up functions for VideoObj and InventorySet in the specific way mandated
by inline comments on the source files.

HW2: Create JUnit assertions to test given code for proper functionality. Additionally create standard java assert statements to properly test invarients within
the linked list structure.

HW3: given a series of skeleton functions with intentionally improper/incomplete implementation. Goal was to refactor function bodies to properly work, and create
custom tests using JUnit to ensure that these functions worked.

HW4: Using existing code from HW1, and given code in the shop.command package. Tasked with creating a factory method implementation of HW1, where commands are
made using a command class given by an interface, and properly allowing private classes to run outside of their package. Additionally create JUnit tests to
ensure proper functionality.

HW5: Modify code from HW4 to include the ability to undo and redo commands. Additionally create JUnit tests to ensure proper functionality.

HW6: Modify code from HW5 with the shop.ui package and the Control.java file given with incomplete implementation. Given only a functioning UI code segment, modify
Control.java to properly implement different form and menu objects to properly run all commands.  Additionally create JUnit tests to ensure proper functionality.

HW7: Modify code from HW6 to hide all elements of Shop.ui except for factory classes and interfaces. Code must function identically to HW6 but with only factories
being used to instantiate objects. No additional code supplied by instructor.

HW8: Modify code from HW7 to instead utilize a state based design pattern, and eliminate usage of factory methods entirely. Not additional code supplied by instructor.