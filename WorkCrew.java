import java.util.Scanner;

public class WorkCrew {

  String name;
  String phone;
  int stage;

  public WorkCrew(String name, String phone, int stage) {
    this.name = name;
    this.phone = phone;
    this.stage = stage;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public int getStage() {
    return stage;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setStage(int stage) {
    this.stage = stage;
  }

  public static WorkCrew addNewCrew(Scanner input) {
    System.out.println("Enter the name of the crew:");
    String name = input.next();
    System.out.println("Enter the phone number of the crew:");
    String phone = input.next();
    System.out.println("Enter the able-to-work stage of the crew:");
    int stage = input.nextInt();
    WorkCrew workCrew = new WorkCrew(name, phone, stage);
    return workCrew;
  }

  public static void listWorkCrews(int stage) {
    for (int i = 0; i < CMS.numberOfWorkCrews; i++) {
      WorkCrew crew = CMS.workCrews[i];
      if (crew.stage == stage) {
        System.out.println((i + 1) + ". " + crew.name + "\t[Contact: " + crew.phone + "]\n");
      }
    }
  }

  public static void listWorkCrews(WorkCrew[] workCrews) {
    for (int i = 0; i < workCrews.length; i++) {
      if (workCrews[i] != null) {
        System.out.println("Crew " + (i + 1) + ": " + workCrews[i].name + "\t[Contact: " + workCrews[i].phone + "]");
      }
    }
  }
}