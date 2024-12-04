# DI and Polymorphism Example

## Student and Student Types

We have one interface called `Student`.  
There is a class implementing the interface called `GraduateStudent` that overrides the method `complain`.
Next there is a child of `GraduateStudent` that overrides the method `complain` and prints its own message called `Phd`

## Key Points

1. Each child overrides the `complain` method and implements its own message.
2. The `SchoolApplication` class creates the `ComplaintCenter`
3. The `SchoolApplication`creates all the students to add to the `List<Student>`
4. The `SchoolApplication` is the one creating, i.e. calling `= new`, the `ComplaintCenter` only knows about `Student`
5. When the `ComplaintCenter` loops through all the `Student` objects in the list, the compiler knows which `complain` method to invoke.
6. If later we create a `PartTimeStudent` class, we can add it via the `SchoolApplication` class and the `ComplaintCenter` 
does <b>NOT</b> need to change its interface  
