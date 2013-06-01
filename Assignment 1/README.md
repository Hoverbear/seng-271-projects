# Assignment 1 - SENG 271 Spring 2013
This contains the project files for Assignment 1. Please see the spec given in `writeup.pdf`, it contains all diagrams with descriptions, as well as fully annotated notes for each function, property, and class.


## Running it
To run this assignment (Assuming you're in the directory of this file) use:

    javac StudyRight.java
    java StudyRight

Enjoy an interactive walk through Study Right University! Or, if you're just looking for a quick peice of paper, plan ahead to get a degree the easy way!


## Trying it the easy way

    Hoverbear@Lovelace ⮀ Assignment 1 ⮀ ⭠ master ⚡ ⮀ java StudyRight 
    Student Name: Karlie
    Do you want to attend Study-Right University? [Y/n]: y
    Welcome to Study-Right University! This school requires 214 credits to graduate.
    Do you want to plan ahead? [y/N]: y
    It's wise to take some time to plan... You sit down at your desk and work out some paths that might work for you...
    After a overnight marathon of sweat, blood, tears, and lots of typing you come up with 3 plans which will lead you to graduation.
    Which of the 3 plans do you want to see? [1]: 2
    Your chosen plan is [math, algebra, philosophy, modern arts, philosophy, algebra, math, modeling, exam] do you wish to accept it?: [Y/n]: y
    Congratulations on graduating from Study-Right University! Your diploma is in the mail because you forgot to pay the fees for your convocation.

## Trying it the awesome way
Here note how attending a room you're already in (or an otherwise invalid room!) does not yeild you extra credits.

    Hoverbear@Lovelace ⮀ Assignment 1 ⮀ ⭠ master ⚡ ⮀ java StudyRight
    Student Name: Karlie
    Do you want to attend Study-Right University? [Y/n]: y
    Welcome to Study-Right University! This school requires 214 credits to graduate.
    Do you want to plan ahead? [y/N]: n
    17/214 Credits. Choose your next class from [algebra, calculus, modeling] : math
    17/214 Credits. Choose your next class from [algebra, calculus, modeling] : algebra 
    52/214 Credits. Choose your next class from [math, philosophy] : philosophy
    84/214 Credits. Choose your next class from [algebra, modern arts] : modern arts
    101/214 Credits. Choose your next class from [modeling, philosophy] : philosophy
    133/214 Credits. Choose your next class from [algebra, modern arts] : algebra
    168/214 Credits. Choose your next class from [math, philosophy] : math
    185/214 Credits. Choose your next class from [algebra, calculus, modeling] : modeling
    214/214 Credits. Choose your next class from [stochastic, calculus, math, modern arts, exam] : exam
    Congratulations on graduating from Study-Right University! Your diploma is in the mail because you forgot to pay the fees for your convocation.

Also note how the student is forced on vacation if they run over on credits.

    Hoverbear@Lovelace ⮀ Assignment 1 ⮀ ⭠ master ⚡ ⮀ java StudyRight
    Student Name: Karlie
    Do you want to attend Study-Right University? [Y/n]: y
    Welcome to Study-Right University! This school requires 214 credits to graduate.
    Do you want to plan ahead? [y/N]: n
    17/214 Credits. Choose your next class from [algebra, calculus, modeling] : algebra
    52/214 Credits. Choose your next class from [math, philosophy] : calculus
    52/214 Credits. Choose your next class from [math, philosophy] : modeling
    52/214 Credits. Choose your next class from [math, philosophy] : math
    69/214 Credits. Choose your next class from [algebra, calculus, modeling] : algebra
    104/214 Credits. Choose your next class from [math, philosophy] : modeling
    104/214 Credits. Choose your next class from [math, philosophy] : philosophy
    136/214 Credits. Choose your next class from [algebra, modern arts] : modern arts
    153/214 Credits. Choose your next class from [modeling, philosophy] : modeling
    182/214 Credits. Choose your next class from [stochastic, calculus, math, modern arts, exam] : stochastic
    205/214 Credits. Choose your next class from [calculus, modeling] : modeling
    You are out of motivation, over in credits, or otherwise need to take a vacation. Enjoy your free trip to Iceland! (Your path, credits, motivation are reset)
    17/214 Credits. Choose your next class from [algebra, calculus, modeling] : 
    
The user can also enter fake rooms, and it will simply prompt for a new input.

    Hoverbear@Lovelace ⮀ Assignment 1 ⮀ ⭠ master ⚡ ⮀ java StudyRight
    Student Name: Karlie
    Do you want to attend Study-Right University? [Y/n]: y
    Welcome to Study-Right University! This school requires 214 credits to graduate.
    Do you want to plan ahead? [y/N]: n
    17/214 Credits. Choose your next class from [algebra, calculus, modeling] : potato
    17/214 Credits. Choose your next class from [algebra, calculus, modeling] : 
    
Awesome.