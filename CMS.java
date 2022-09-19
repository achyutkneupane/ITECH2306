import java.util.Scanner;

class CMS {

  static int maximumWorkCrews = 20;
  static int numberOfWorkCrews = 0;
  static WorkCrew[] workCrews = new WorkCrew[maximumWorkCrews];

  static int maximumBuildings = 10;
  static int numberOfBuildings = 0;
  static BuildingJob[] buildingJobs = new BuildingJob[maximumBuildings];

  public static void showMenu() {

    Scanner input = new Scanner(System. in );
    int menuNumEntered = 0;

    do {
      System.out.println("Welcome to the House-Building Contract Management Program");
      System.out.println("1. Add new crew");
      System.out.println("2. Add New Building Job");
      System.out.println("3. List Building Jobs (still building)");
      System.out.println("4. List Work Crews (based on stage)");
      System.out.println("5. Building Job Sub-Menu");
      System.out.println("6. Exit");
      System.out.println();

      System.out.println("Please select an option from the menu:");
      menuNumEntered = input.nextInt();
      System.out.println();

      while (menuNumEntered < 1 || menuNumEntered > 6) {
        System.out.println("Invalid option. \nPlease select an option from the menu:");
        menuNumEntered = input.nextInt();
      }

      switch (menuNumEntered) {
      case 1:
        if (numberOfWorkCrews < maximumWorkCrews) {
          WorkCrew workCrew = WorkCrew.addNewCrew(input);
          CMS.workCrews[numberOfWorkCrews] = workCrew;
          numberOfWorkCrews++;
        } else {
          System.out.println("Maximum number of crews reached.");
        }
        break;
      case 2:
        if (numberOfBuildings < maximumBuildings) {
          BuildingJob buildingJob = BuildingJob.addNewBuildingJob(input);
          CMS.buildingJobs[numberOfBuildings] = buildingJob;
          numberOfBuildings++;
        } else {
          System.out.println("Maximum number of buildings reached.");
        }
        break;
      case 3:
        if (numberOfBuildings > 0) {
          BuildingJob.listBuildingJobs();
        } else {
          System.out.println("No buildings to list.\n");
        }
        break;
      case 4:
        if (numberOfWorkCrews > 0) {
          System.out.println("Enter the stage for the work crew to be listed:");
          int stage = input.nextInt();
          WorkCrew.listWorkCrews(stage);
        } else {
          System.out.println("No crews to list.\n");
        }
        break;
      case 5:
        if (numberOfBuildings > 0) {
          BuildingJob.showSubMenu(input);
        } else {
          System.out.println("No buildings to show.\n");
        }
        break;
      case 6:
        System.out.println("Exiting program...");
        break;
      default:
        System.out.println("Invalid option. \nPlease select an option from the menu:");
        menuNumEntered = input.nextInt();
      }

    } while ( menuNumEntered != 6 );
    input.close();
  }

  public static void main(String[] args) {
    populate();
    showMenu();
  }

  public static void populate() {
    // add workcrew to workCrews array
    WorkCrew workCrew1 = new WorkCrew("Achyut", "1111", 1);
    CMS.workCrews[0] = workCrew1;
    numberOfWorkCrews++;
    WorkCrew workCrew2 = new WorkCrew("Roshan", "2222", 2);
    CMS.workCrews[1] = workCrew2;
    numberOfWorkCrews++;
    WorkCrew workCrew3 = new WorkCrew("Sakar", "3333", 3);
    CMS.workCrews[2] = workCrew3;
    numberOfWorkCrews++;

    // add buildingJob to buildingJobs array
    BuildingJob buildingJob1 = new BuildingJob("Dharan", 5000.00);
    CMS.buildingJobs[0] = buildingJob1;
    numberOfBuildings++;
    BuildingJob buildingJob2 = new BuildingJob("Kathmandu", 10000.00);
    CMS.buildingJobs[1] = buildingJob2;
    numberOfBuildings++;

    System.out.println("Crews: " + CMS.numberOfWorkCrews);
    System.out.println("Buildings: " + CMS.numberOfBuildings);
  }
}