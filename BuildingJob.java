import java.util.Scanner;

public class BuildingJob {
  WorkCrew workCrew;
  WorkCrew[] workCrews;
  String address;
  int stage;
  boolean status;
  boolean completed;
  double overallCost;
  double paymentReceived = 0;
  int paymentForStage = 0;

  public BuildingJob(String address, double overallCost) {
    this.address = address;
    this.stage = 0;
    this.overallCost = overallCost;
    this.paymentReceived = 0;
    this.status = false;
    this.completed = false;
  }

  public static void showSubMenu(Scanner input) {
    int subMenuNum = -1;
    BuildingJob selectedBuildingJob = null;
    do {
      if (selectedBuildingJob == null) {
        System.out.println("Select Building from following options:");
        BuildingJob.listBuildingJobs();
        int selectedBuilding = input.nextInt();
        selectedBuildingJob = CMS.buildingJobs[selectedBuilding - 1];
      } else {
        System.out.println("Building Job Sub-Menu");
        System.out.println("1. Get Details");
        System.out.println("2. Assign Crew");
        System.out.println("3. Receive Payment");
        System.out.println("4. Update Stage");
        System.out.println("5. Go back to Main Menu");
        if (selectedBuildingJob.running()) {
          System.out.println("6. Complete Stage " + selectedBuildingJob.stage);
        }
        System.out.println("0. Exit");

        System.out.println();

        System.out.println("Please select an option from the menu:");
        subMenuNum = input.nextInt();
        System.out.println();

        while (subMenuNum < 0 || subMenuNum > 6) {
          System.out.println("Invalid option. \nPlease select an option from the menu:");
          subMenuNum = input.nextInt();
        }
        switch (subMenuNum) {
        case 1:
          selectedBuildingJob.getDetails();
          break;
        case 2:
          selectedBuildingJob.assignCrew(input);
          break;
        case 3:
          selectedBuildingJob.receivePayment(input);
          break;
        case 4:
          selectedBuildingJob.updateStage(input);
          break;
        case 5:
          selectedBuildingJob = null;
          CMS.showMenu();
          break;
        case 6:
          selectedBuildingJob.completeStage();
        case 0:
          System.out.println("Exiting program...");
          break;
        default:
          System.out.println("Invalid option. \nPlease select an option from the menu:");
          subMenuNum = input.nextInt();
        }
      }
    } while ( subMenuNum != 0 );
  }

  public boolean running() {
    return (
    this.stage == this.paymentForStage && this.workCrew != null && this.status);
  }

  public void getDetails() {
    System.out.println("Address: " + this.address);
    System.out.println("Stage: " + this.getStageString());
    System.out.println("Overall Cost: " + this.overallCost);
    System.out.println("Payment Received: " + this.paymentReceived);
    System.out.println("Work Crews:");
    if (this.workCrews != null) {
      WorkCrew.listWorkCrews(this.workCrews);
    } else {
      System.out.println("No crews assigned.");
    }
    System.out.println();
  }

  public void assignCrew(Scanner input) {
    System.out.println("Select Work Crew from following options:");
    WorkCrew.listWorkCrews(this.stage + 1);
    int selectedWorkCrew = input.nextInt();
    this.setWorkCrew(CMS.workCrews[selectedWorkCrew - 1]);
    System.out.println("Work Crew assigned.");
    System.out.println();
  }

  public void completeStage() {
    System.out.println("Stage " + this.stage + " completed.");
    this.status = false;
    if (this.stage == 3) {
      this.completed = true;
    }
    System.out.println();
  }

  public void receivePayment(Scanner input) {
    System.out.println("Enter payment amount:");
    double payment = input.nextDouble();
    if (this.stage == 0) {
      if (payment >= this.overallCost * 0.15) {
        this.paymentReceived += payment;
        this.paymentForStage = 1;
        this.status = true;
        this.stage = 1;
        System.out.println("Payment received.");
      } else {
        System.out.println("Payment not enough for stage 1.");
      }
    } else if (this.stage == 1) {
      if (payment >= this.overallCost * 0.25) {
        this.paymentReceived += payment;
        this.paymentForStage = 2;
        this.status = true;
        this.stage = 2;
        System.out.println("Payment received.");
      } else {
        System.out.println("Payment not enough for stage 2.");
      }
    } else if (this.stage == 2) {
      if (payment >= this.overallCost * 0.25) {
        this.paymentReceived += payment;
        this.paymentForStage = 3;
        this.status = true;
        this.stage = 3;
        System.out.println("Payment received.");
      } else {
        System.out.println("Payment not enough for stage 3.");
      }
    } else if (this.stage == 3) {
      if (payment >= this.overallCost * 0.35) {
        this.paymentReceived += payment;
        this.paymentForStage = 4;
        this.status = true;
        System.out.println("Payment received.");
      } else {
        System.out.println("Payment not enough for completion.");
      }
    }
  }

  public void updateStage(Scanner input) {
    System.out.println("Enter stage number:");
    int stage = input.nextInt();
    if (stage == this.stage + 1) {
      if (this.paymentForStage == stage) {
        this.stage = stage;
        System.out.println("Stage updated.");
      } else {
        System.out.println("Payment not enough for stage " + stage + ".");
      }
    } else {
      System.out.println("Invalid stage number.");
    }
    System.out.println();
  }

  public static BuildingJob addNewBuildingJob(Scanner input) {
    System.out.println("Enter the address of the building:");
    String address = input.next();
    System.out.println("Enter the overall cost of the building:");
    double overallCost = input.nextDouble();
    BuildingJob buildingJob = new BuildingJob(address, overallCost);
    return buildingJob;
  }

  public static void listBuildingJobs() {
    for (int i = 0; i < CMS.numberOfBuildings; i++) {
      BuildingJob building = CMS.buildingJobs[i];
      if (!building.completed) {
        System.out.println((i + 1) + ". " + building.address + "\t\t\t" + "[" + building.getStageString() + "]");
      }
    }
    System.out.println();
  }

  public void setStage(int stage) {
    this.stage = stage;
  }

  public void receivePayment(double payment) {
    this.paymentReceived += payment;
  }

  public void setPaymentForStage(int paymentForStage) {
    this.paymentForStage = paymentForStage;
  }

  public void setWorkCrew(WorkCrew workCrew) {
    this.workCrew = workCrew;
    this.appendWorkCrews(workCrew);
  }

  public void appendWorkCrews(WorkCrew workCrew) {
    if (this.workCrews == null) {
      this.workCrews = new WorkCrew[1];
      this.workCrews[0] = workCrew;
    } else {
      WorkCrew[] newWorkCrews = new WorkCrew[this.workCrews.length + 1];
      for (int i = 0; i < this.workCrews.length; i++) {
        newWorkCrews[i] = this.workCrews[i];
      }
      newWorkCrews[this.workCrews.length] = workCrew;
      this.workCrews = newWorkCrews;
    }
  }

  public String getStageString() {
    String stageString = "";
    if (this.stage == 0 && this.paymentForStage == 0 && this.workCrew == null && !this.status) {
      stageString = "Not Yet Started";
    } else if (this.stage == 0 && this.paymentForStage == 0 && this.workCrew != null && !this.status) {
      stageString = "Before Stage 1";
    } else if (this.stage == 0 && this.paymentForStage == 1 && this.workCrew != null && !this.status) {
      stageString = "Ready for Stage 1";
    } else if (this.stage == 1 && this.paymentForStage == 1 && this.workCrew != null && this.status) {
      stageString = "Stage 1";
    } else if (this.stage == 1 && this.paymentForStage == 1 && this.workCrew == null && !this.status) {
      stageString = "Completed Stage 1";
    } else if (this.stage == 1 && this.paymentForStage == 1 && this.workCrew != null && !this.status) {
      stageString = "Before Stage 2";
    } else if (this.stage == 1 && this.paymentForStage == 2 && this.workCrew != null && !this.status) {
      stageString = "Ready for Stage 2";
    } else if (this.stage == 2 && this.paymentForStage == 2 && this.workCrew != null && this.status) {
      stageString = "Stage 2";
    } else if (this.stage == 2 && this.paymentForStage == 2 && this.workCrew == null && !this.status) {
      stageString = "Completed Stage 2";
    } else if (this.stage == 2 && this.paymentForStage == 2 && this.workCrew != null && !this.status) {
      stageString = "Before Stage 3";
    } else if (this.stage == 2 && this.paymentForStage == 3 && this.workCrew != null && !this.status) {
      stageString = "Ready for Stage 3";
    } else if (this.stage == 3 && this.paymentForStage == 3 && this.workCrew != null && this.status) {
      stageString = "Stage 3";
    } else if (this.stage == 3 && this.paymentForStage == 3 && this.workCrew == null && !this.status) {
      stageString = "Completed Stage 3";
    } else {
      stageString = "Completed";
    }
    return stageString;
  }
}