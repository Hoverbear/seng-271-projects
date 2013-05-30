import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.Console;
import java.io.PrintStream;
import java.io.OutputStream;

class StudyRight {
  public static void main(String[] args) {
    // Create "Study-Right"
    School school = new School("Study-Right University", 214, "math", "exam");
    // Since Java doesn't have Javascript objects, we'll use a hash map that provides a similar functionality.
    school.rooms.put("math", new Room("math", 17, new String[] {"algebra", "calculus", "modeling"}, school));
    school.rooms.put("calculus", new Room("calculus", 20, new String[] {"stochastic", "modeling", "math"}, school));
    school.rooms.put("stochastic", new Room("stochastic", 23, new String[] {"calculus", "modeling"}, school));
    school.rooms.put("modeling", new Room("modeling", 29, new String[] {"stochastic", "calculus", "math", "modern arts", "exam"}, school));
    school.rooms.put("modern arts", new Room("modern arts", 17, new String[] {"modeling", "philosophy"}, school));
    school.rooms.put("philosophy", new Room("philosophy", 32, new String[] {"algebra", "modern arts"}, school));
    school.rooms.put("algebra", new Room("algebra", 35, new String[] {"math", "philosophy"}, school));
    // (Note different class)
    school.rooms.put("exam", new ExamRoom("exam", new String[] {"modeling"}, school));
    
    // Initialize the student and get them admitted to the university.
    Console console = System.console();
    Student student = new Student(console.readLine("Student Name: "));
    String choice = console.readLine("Do you want to attend Study-Right University? [Y/n]: ");
    if (choice.equals("n")) {
      System.out.println("Ok, goodbye then!");
      System.exit(1);
    }
    else {
      school.admitStudent(student);
    }
    
    choice = console.readLine("Do you want to plan ahead? [y/N]: ");
    if (choice.equals("y")) {
      student.planAhead(school);
      System.exit(0);
    }
    else {
      // Student chooses their own path.
      while(student.currentRoom != "exam") {
        if (student.credit >= school.requiredCredits || student.motivation <= 0) {
          // The student is no longer eligible for the school due to credits or motivation.
          student.vacation(school);
        }
        else {
          // Choose the next classroom.
          String next = console.readLine(student.credit + "/" + school.requiredCredits + " Credits. Choose your next class from " + Arrays.toString(school.rooms.get(student.currentRoom).connectors) + " (Or pick 'plan'): ");
          if (next.equals("plan")) {
            student.planAhead(school);
          }
          school.attendRoom(student, next);
        }
      }
      // Student is at exam room.
    }
  }
}

class School {
  Map<String, Room> rooms;
  int requiredCredits;
  String entrance;
  String exam;
  String name;
  ArrayList<Student> students = new ArrayList<Student>();
  
  // With a map of rooms.
  public School(String name, int requiredCredits, String entrance, String exam, Map<String, Room> rooms) {
    this.name = name;
    this.rooms = rooms;
    this.requiredCredits = requiredCredits;
    this.entrance = entrance;
    this.exam = exam;
  }
  
  // Add your own room map later.
  public School(String name, int requiredCredits, String entrance, String exam) {
    this.name = name;
    this.requiredCredits = requiredCredits;
    this.entrance = entrance;
    this.exam = exam;
    this.rooms = new HashMap<String, Room>();
  }
  
  public void admitStudent(Student student) {
    student.currentRoom = entrance;
    student.path.add(entrance);
    student.credit = rooms.get(entrance).credits;
    student.motivation = requiredCredits - rooms.get(entrance).credits;
    student.school = this;
    students.add(student);
    System.out.println("Welcome to " + name + "! This school requires " + requiredCredits + " credits to graduate.");
    return;
  }
  
  public void removeStudent(Student student) {
    students.remove(student);
    return;
  }
  
  public void graduateStudent(Student student) {
    System.out.println("Congratulations on graduating from " + name + "! Your diploma is in the mail because you forgot to pay the fees for your convocation.");
    students.remove(student);
    return;
  }
  
  public void attendRoom(Student student, String room) {
    // Is this room a valid choice?
    boolean isStu = isStudent(student);
    boolean isVal = isValidRoom(student.currentRoom, room);
    if (isStu && isVal) {
      rooms.get(room).attend(student);
    }
    return;
  }
  
  private boolean isStudent(Student student) {
    return students.contains(student);
  }
  
  private boolean isValidRoom(String origin, String destination) {
    return Arrays.asList(rooms.get(origin).connectors).contains(destination);
  }
}

class Room {
  String name;
  int credits;
  String[] connectors;
  School school;
  
  public Room(String name, int credits, String[] connectors, School school) {
    this.name = name;
    this.credits = credits;
    this.connectors = connectors;
    this.school = school;
  }
  
  public String toString() {
    return name + "-" + credits;
  }
  
  public void attend(Student student) {
    student.credit += credits;
    student.motivation -= credits;
    student.path.add(name);
    student.currentRoom = name;
    return;
  }
}

class ExamRoom extends Room {
  public ExamRoom(String name, String[] connectors, School school) {
    super(name, 0, connectors, school);
  }
  
  public void attend(Student student) {
    if (student.credit == school.requiredCredits) {
      school.graduateStudent(student);
    }
    else {
      student.vacation(school);
    }
    return;
  }
}

class Student {
  String name;
  int credit;
  int motivation;
  String currentRoom = "";
  School school;
  ArrayList<String> path = new ArrayList<String>();
  ArrayList<ArrayList<String>> plans = new ArrayList<ArrayList<String>>(); // Only used if the student plans ahead.
  
  public Student(String name) {
    this.name = name;
  }
  
  // Used mainly for planning ahead. But you can add your own pre-built path here.
  public Student(String name, ArrayList<String> path, String currentRoom, int credit, int motivation, School school) {
    this.name = name;
    this.path = path;
    this.currentRoom = currentRoom;
    this.credit = credit;
    this.motivation = motivation;
    this.school = school;
  }
  
  public void vacation(School school) {
    credit = school.rooms.get(school.entrance).credits;
    motivation = school.requiredCredits;
    path = new ArrayList<String>();
    currentRoom = school.entrance;
    path.add(school.entrance);
    System.out.println("You are out of motivation, over in credits, or otherwise need to take a vacation. Enjoy your free trip to Iceland! (Your path, credits, motivation are reset)");
    return;
  }
  
  public ArrayList<ArrayList<String>> planAhead(School school) {
    System.out.println("It's wise to take some time to plan... You sit down at your desk and work out some paths that might work for you...");
    // We're going to suppress our stdout for a little while... This is done so the user doesn't get spammed while we test the waters.
    PrintStream original = System.out;
    PrintStream silent = new PrintStream(new OutputStream() {
        public void write(int b) {
            //DO NOTHING
        }
    });
    //System.setOut(silent);
    // Test all possible paths
    Student tester =  new Student("test");
    school.admitStudent(tester);
    tester.explore();
    school.removeStudent(tester);
    // Reset out stdout.
    System.setOut(original);
    return plans;
  }
  
  private void explore() {
    System.out.println(this + " :: " + currentRoom + " :: " + credit + " :: " + path);
    if (credit == school.requiredCredits && currentRoom.equals(school.exam)) {
      plans.add(path);
      school.removeStudent(this);
      return;
    }
    else if (currentRoom.equals(school.exam)) {
      school.removeStudent(this);
      return;
    }
    else {
      for (int i = 0; i < school.rooms.get(currentRoom).connectors.length; i++) {
        String newRoom = school.rooms.get(currentRoom).connectors[i];
        Student tester = new Student("tester", path, currentRoom, credit, motivation, school);
        school.students.add(tester);
        school.attendRoom(tester, newRoom);
        if (credit > school.requiredCredits) {
          school.removeStudent(tester);
          return;
        }
        tester.explore();
        school.removeStudent(tester);
      }
    }
    return;
  }
}